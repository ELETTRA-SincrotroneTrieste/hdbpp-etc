/**
 * 
 */
package fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseManagement.TdbDataExport;

import java.sql.Timestamp;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.ErrSeverity;
import fr.esrf.TangoApi.AttributeInfo;
import fr.esrf.TangoApi.DeviceData;
import fr.esrf.TangoApi.DeviceProxy;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.GlobalConst;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.GetConf;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.AttributesManagement.AdtAptAttributes.IAdtAptAttributes;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseManagement.DbConnection.IDBConnection;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseUtils.IDbUtils;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.ModesManagement.IGenericMode;
import fr.soleil.hdbtdbArchivingApi.ArchivingManagerApi.ArchivingManagerApiRef;
import fr.soleil.hdbtdbArchivingApi.ArchivingManagerApi.TangoAccess;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Mode.Mode;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ArchivingException;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.AttributeLightMode;

/**
 * @author AYADI
 *
 */
public abstract class  TdbDataExport implements ITdbDataExport{
	private static final String m_ARCHIVINGEXPORT = "ExportData2Db";
	public IDBConnection dbConn;
	public TdbDataExport(IDBConnection conn){
		this.dbConn = conn;
	};

	abstract public  void  exportToDB_Data(String remoteDir , String fileName , String tableName, int writable) throws ArchivingException;
	abstract public void forceDatabaseToImportFile(String tableName) throws ArchivingException;
	/**
	 * Export for a given attribute, its file stored data to the database
	 * if the selected end date is after the actual date minus export period
	 * and the precision value, usually 45 min(30min as the export period and 15min as a precission value).
	 * @param attributeName The name of the given attribute
	 * @param endDate the end Date of extracting data.
	 */
	public void ExportData2Tdb(String attributeName, String endDate, IGenericMode m, IAdtAptAttributes attr, IDbUtils dbUtils) throws ArchivingException
	{ 
		
		Mode mode = null;
		if ( attr.isRegisteredADT(attributeName) )
		 mode = m.getCurrentArchivingMode(attributeName);
		if(Timestamp.valueOf(endDate).after(new Timestamp(System.currentTimeMillis()- mode.getTdbSpec().getExportPeriod() - (GetConf.readLongInDB("TdbArchiver", "ExportPeriodPrecision")*1000)))){
			System.out.println("Export data from file to data base");
			String archiverName = "";
			if ( dbConn == null )
				throw new ArchivingException(GlobalConst.UNCONNECTECTED_ADB);

			try
			{
				if ( m.isArchived(attributeName ) )		        
				{
					//get the Archiver that take in charge the archiving
					archiverName = m.getDeviceInCharge(attributeName);
					if ( archiverName == "" || !TangoAccess.deviceLivingTest(archiverName) )
					{
						//throw new ArchivingException(GlobalConst.ARC_UNREACH_EXCEPTION);
						throw new ArchivingException(GlobalConst.ARC_UNREACH_EXCEPTION,GlobalConst.ARC_UNREACH_EXCEPTION,ErrSeverity.ERR,GlobalConst.ARC_UNREACH_EXCEPTION,GlobalConst.ARC_UNREACH_EXCEPTION );
						//ArchivingException(String message , String reason , ErrSeverity archSeverity , String desc , String origin)
					}

					String tableName;
					try
					{
						//System.out.println ( "CLA/ArchivingManagerApi/ExportData2Tdb/attributeName A0|" + attributeName + "|" );
						tableName = dbUtils.getTableName ( attributeName, attr);
						//System.out.println ( "CLA/ArchivingManagerApi/ExportData2Tdb/attributeName A1|" + attributeName + "|tableName|" + tableName + "|" );
					}
					catch ( Throwable t )
					{
						t.printStackTrace ();
						return;
					}


					
					AttributeLightMode attributeLightMode = new AttributeLightMode(attributeName);
					AttributeInfo attributeInfo = ArchivingManagerApiRef.getAttributeInfo(attributeName,true);
					attributeLightMode.setDevice_in_charge(archiverName);
					attributeLightMode.setData_type(attributeInfo.data_type);
					attributeLightMode.setData_format(attributeInfo.data_format.value());
					attributeLightMode.setWritable(attributeInfo.writable.value());
					attributeLightMode.setMode(mode);

					DeviceProxy archiverProxy = new DeviceProxy(archiverName);
					String[] attributeLightModeArray = attributeLightMode.toArray();
					DeviceData device_data = new DeviceData();
					device_data.insert(attributeLightModeArray);

					//System.out.println ( "ArchivingManagerApi/ExportData2Tdb/BEFORE archiverProxy.command_inout" );
					//DeviceData argout = 
					//launch ExportData2DbCmd for the MySQL DataBase
					archiverProxy.command_inout(m_ARCHIVINGEXPORT , device_data);

					forceDatabaseToImportFile ( tableName );
					//System.out.println ( "ArchivingManagerApi/ExportData2Tdb/BEFORE forceDatabaseToImportFile" );
					/*((OracleTdbDataExport)((TDBDataBaseManager)m_DataBase).getTdbExport()).*/
					//System.out.println ( "ArchivingManagerApi/ExportData2Tdb/AFTER forceDatabaseToImportFile" );
				}
				else
				{
				}

				//System.out.println ( "CLA/ArchivingManagerApi/ExportData2Tdb/attributeName Z0" );
			}
			catch ( DevFailed devFailed )
			{
				String message = GlobalConst.ERROR_ARCHIVINGSTOP + " : " +
				"Problems talking to the archiver " + archiverName + " for the attribute " + attributeName;
				String reason = GlobalConst.TANGO_COMM_EXCEPTION;
				String desc = "Failed while executing TdvDataExport.ExportData2Tdb() method...";
				throw new ArchivingException(message , reason , ErrSeverity.WARN , desc , "" , devFailed);
			}
			catch ( ArchivingException e )
			{		 
				e.printStackTrace();
				throw e;
			}
		}
		//System.out.println ( "CLA/ArchivingManagerApi/ExportData2Tdb/attributeName Z1" );
	}

	
}
