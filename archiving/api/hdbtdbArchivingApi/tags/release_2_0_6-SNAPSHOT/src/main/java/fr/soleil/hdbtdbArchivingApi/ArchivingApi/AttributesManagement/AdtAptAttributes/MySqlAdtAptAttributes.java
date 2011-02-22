/**
 * 
 */
package fr.soleil.hdbtdbArchivingApi.ArchivingApi.AttributesManagement.AdtAptAttributes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import fr.esrf.Tango.ErrSeverity;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.GlobalConst;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.ConfigConst;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.AttributesManagement.AttributeExtractor.DataGetters.DataGetters;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseManagement.DbCommands.ConnectionCommands;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseManagement.DbConnection.IDBConnection;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseUtils.IDbUtils;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ArchivingException;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.AttributeHeavy;

/**
 * @author AYADI
 *
 */
public class MySqlAdtAptAttributes extends AdtAptAttributes {

	/**
	 * 
	 */
	public MySqlAdtAptAttributes(IDBConnection c) {
		// TODO Auto-generated constructor stub
		super(c);
	}


	/**
	 * This method registers a given attribute into the hdb database
	 * This method does not take care of id parameter of the given attribute as this parameter is managed in the database side (autoincrement).
	 *
	 * @param attributeHeavy the attribute to register
	 * @throws ArchivingException
	 */
	
	private void registerInADT(AttributeHeavy attributeHeavy) throws ArchivingException
	{
		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String tableName = dbConn.getSchema() + "." + ConfigConst.TABS[ 0 ];

		// Create and execute the SQL query string
		// Build the query string
		String insert_query;
		insert_query = "INSERT INTO " + tableName + " (";
		for ( int i = 1 ; i < ConfigConst.TAB_DEF.length - 1 ; i++ )
		{
			insert_query = insert_query + ConfigConst.TAB_DEF[ i ] + ", ";
		}
		insert_query = insert_query +
		ConfigConst.TAB_DEF[ ConfigConst.TAB_DEF.length - 1 ] + ")";
		insert_query = insert_query + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) ";
		try
		{
			conn = dbConn.getConnection();
			preparedStatement = conn.prepareStatement(insert_query);
			dbConn.setLastStatement(preparedStatement);
			//preparedStatement.setInt(1, attributeHeavy.getAttribute_id());
			preparedStatement.setTimestamp(1 , attributeHeavy.getRegistration_time());
			preparedStatement.setString(2 , attributeHeavy.getAttribute_complete_name());
			preparedStatement.setString(3 , attributeHeavy.getAttribute_device_name());
			preparedStatement.setString(4 , attributeHeavy.getDomain());
			preparedStatement.setString(5 , attributeHeavy.getFamily());
			preparedStatement.setString(6 , attributeHeavy.getMember());
			preparedStatement.setString(7 , attributeHeavy.getAttribute_name());
			preparedStatement.setInt(8 , attributeHeavy.getData_type());
			preparedStatement.setInt(9 , attributeHeavy.getData_format());
			preparedStatement.setInt(10 , attributeHeavy.getWritable());
			preparedStatement.setInt(11 , attributeHeavy.getMax_dim_x());
			preparedStatement.setInt(12 , attributeHeavy.getMax_dim_y());
			preparedStatement.setInt(13 , attributeHeavy.getLevel());

			//System.out.println ( "CLA/attributeHeavy.getCtrl_sys()/"+attributeHeavy.getCtrl_sys()+"/" );
			preparedStatement.setString(14 , attributeHeavy.getCtrl_sys());
			preparedStatement.setInt(15 , attributeHeavy.getArchivable());
			preparedStatement.setInt(16 , attributeHeavy.getSubstitute());

			preparedStatement.executeUpdate();
		}
		catch ( SQLException e )
		{
			String message = "";
			if ( e.getMessage().equalsIgnoreCase(GlobalConst.COMM_FAILURE_ORACLE) || e.getMessage().indexOf(GlobalConst.COMM_FAILURE_MYSQL) != -1 )
				message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : " + GlobalConst.ADB_CONNECTION_FAILURE;
			else
				message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : " + GlobalConst.STATEMENT_FAILURE;

			String reason = GlobalConst.QUERY_FAILURE;
			String desc = "Failed while executing MySqlAdtAptAttributes.registerInADT() method...";
			throw new ArchivingException(message , reason , ErrSeverity.WARN , desc , this.getClass().getName() , e);
		}
		finally
		{
			try {
				if(preparedStatement!=null)ConnectionCommands.close(preparedStatement);
				if(conn!=null)dbConn.closeConnection(conn);
			} catch (SQLException e) {
				dbConn.closeConnection(conn);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	/**
	 * This method registers a given attribute into the hdb database
	 * It inserts a record in the "Attribute Properties Table" <I>(mySQL only)</I>
	 * This method does not take care of id parameter of the given attribute as this parameter is managed in the database side (autoincrement).
	 *
	 * @param attributeHeavy the attribute to register
	 * @throws ArchivingException
	 */
	private void registerInAPT(int id , AttributeHeavy attributeHeavy) throws ArchivingException
	{

		Connection conn = null;
		PreparedStatement preparedStatement = null;
		String tableName = dbConn.getSchema() + "." + ConfigConst.TABS[ 1 ];

		// Create and execute the SQL query string
		// Build the query string
		String insert_query;
		insert_query = "INSERT INTO " + tableName + " (";// +
		for ( int i = 0 ; i < ConfigConst.TAB_PROP.length - 1 ; i++ )
		{
			insert_query = insert_query + ConfigConst.TAB_PROP[ i ] + ", ";
		}
		insert_query = insert_query +
		ConfigConst.TAB_PROP[ ConfigConst.TAB_PROP.length - 1 ] + ")";
		insert_query = insert_query + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?) ";

		try
		{
			conn = dbConn.getConnection();
			preparedStatement = conn.prepareStatement(insert_query);
			dbConn.setLastStatement(preparedStatement);
			preparedStatement.setInt(1 , id);
			preparedStatement.setTimestamp(2 , attributeHeavy.getRegistration_time());

			String description = attributeHeavy.getDescription();
			int maxDescriptionLength = 255;
			if ( description != null && description.length() > maxDescriptionLength )
			{
				description = description.substring ( 0 , maxDescriptionLength );
			}
			preparedStatement.setString(3 , description);

			preparedStatement.setString(4 , attributeHeavy.getLabel());
			preparedStatement.setString(5 , attributeHeavy.getUnit());
			preparedStatement.setString(6 , attributeHeavy.getStandard_unit());
			preparedStatement.setString(7 , attributeHeavy.getDisplay_unit());
			preparedStatement.setString(8 , attributeHeavy.getFormat());
			preparedStatement.setString(9 , attributeHeavy.getMin_value());
			preparedStatement.setString(10 , attributeHeavy.getMax_value());
			preparedStatement.setString(11 , attributeHeavy.getMin_alarm());
			preparedStatement.setString(12 , attributeHeavy.getMax_alarm());

			preparedStatement.executeUpdate();
		}
		catch ( SQLException e )
		{
			String message = "";
			if ( e.getMessage().equalsIgnoreCase(GlobalConst.COMM_FAILURE_ORACLE) || e.getMessage().indexOf(GlobalConst.COMM_FAILURE_MYSQL) != -1 )
				message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : " + GlobalConst.ADB_CONNECTION_FAILURE;
			else
				message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : " + GlobalConst.STATEMENT_FAILURE;

			String reason = GlobalConst.QUERY_FAILURE;
			String desc = "Failed while executing MySqlAdtAptAttributes.registerInAPT() method...";
			throw new ArchivingException(message , reason , ErrSeverity.WARN , desc , this.getClass().getName() , e);
		}
		finally
		{
			try {
				if(preparedStatement!=null)ConnectionCommands.close(preparedStatement);
				if(conn!=null)dbConn.closeConnection(conn);
			} catch (SQLException e) {
				dbConn.closeConnection(conn);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}




	@Override
	protected String getRequest(String att_name) {
		// TODO Auto-generated method stub
		String str = new String("");
		str = "SELECT * FROM " + dbConn.getSchema() + "." + ConfigConst.TABS[ 0 ] +
		" WHERE " + ConfigConst.TAB_DEF[ 2 ] + "='" + att_name.trim() + "'";
		return str; 
	}



	// TODO Auto-generated method stub
	/**
	 * This method registers a given attribute into the hdb database
	 * It inserts a record in the "Attribute Definition Table" <I>(mySQL only)</I>
	 * This methos does not take care of id parameter of the given attribute as this parameter is managed in the database side (autoincrement).
	 *
	 * @param attributeHeavy the attribute to register
	 * @throws ArchivingException
	 */
	@Override
	public void registerAttribute(AttributeHeavy attributeHeavy, IDbUtils dbUtils) throws ArchivingException
	{
		// Register the attribute in the 'Attribute Definition Table'
		registerInADT(attributeHeavy);
		//int id = getAttID(attributeHeavy.getAttribute_complete_name());
		int id = ids.getBufferedAttID(attributeHeavy.getAttribute_complete_name());

		// Register the attribute in the 'Attribute Property Table'
		registerInAPT(id , attributeHeavy);
		

	}

	/**
	 * test if attribute's table exist, otherwise, it creates a new table  
	 * @param attributeName
	 * @throws ArchivingException 
	 */
	public void CreateAttributeTableIfNotExist(String attributeName, DataGetters extractor, IDbUtils dbUtils) throws ArchivingException {
		// TODO Auto-generated method stub
		
		AttributeHeavy attributeHeavy = new AttributeHeavy(attributeName);
		int id = ids.getBufferedAttID(attributeHeavy.getAttribute_complete_name());
		String tableName = dbUtils.getTableName(id);
		// Build the associated table
		if(!isTableExist(tableName)){
			extractor.buildAttributeTab(tableName , attributeHeavy.getData_type() , attributeHeavy.getData_format() , attributeHeavy.getWritable());	
		}
		
		

	}


	private boolean isTableExist(String tableName) {
		// TODO Auto-generated method stub
		//show tables like "att_00021"
		Connection conn;
		Statement stmt;
		ResultSet rset;
		String query = "SHOW TABLES LIKE " + "\""+ tableName+"\"";
		
		try {
			conn = dbConn.getConnection();
			stmt = conn.createStatement();
			dbConn.setLastStatement(stmt);
			rset = stmt.executeQuery(query);
	           while ( rset.next() )
	            {
	                return rset.getString(1).equalsIgnoreCase(tableName);
	                
	            }

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		return false;
	}



}
