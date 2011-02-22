//+============================================================================
//$Source: /cvsroot/tango-cs/tango/jserver/snapshoting/SnapExtractor/SnapExtractor.java,v $

//project :     Tango Device Server

//Description:	java source code for the SnapExtractor class and its commands.
//This class is derived from DeviceImpl class.
//It represents the CORBA servant obbject which
//will be accessed from the network. All commands which
//can be executed on the SnapExtractor are implemented
//in this file.

//$Author: soleilarc $

//$Revision: 1.16 $

//$Log: SnapExtractor.java,v $
//Revision 1.16  2007/10/15 15:14:53  soleilarc
//Author: XP
//Mantis bug ID: 6256
//Comment:
//In the init_device method, put the call to get_device_property method at the beginning.
//In the get_device_property method, the values of the table propnames must be �DbUser� and �DbPassword�, instead of �SnapUser� and �SnapPassword�.

//Revision 1.15  2007/05/11 13:58:55  pierrejoseph
//Attribute addition : release version

//Revision 1.14  2007/03/14 15:47:01  ounsy
//passes its user and password to the APIs

//Revision 1.13  2007/03/02 07:54:57  ounsy
//extend DeviceImpl instead of DeviceImplWithShutdownRunnable

//Revision 1.12  2006/12/06 10:16:02  ounsy
//minor changes

//Revision 1.11  2006/11/20 09:38:54  ounsy
//minor changes

//Revision 1.10  2006/11/13 15:58:06  ounsy
//all java devices now inherit from UnexportOnShutdownDeviceImpl instead of from DeviceImpl

//Revision 1.9  2006/10/31 16:54:15  ounsy
//milliseconds and null values management

//Revision 1.8  2006/04/12 15:47:03  ounsy
//organized imports

//Revision 1.7  2006/04/12 08:40:35  ounsy
//cleaned the code and added a better error logging

//Revision 1.6  2006/03/29 11:35:12  ounsy
//small modification of the remove_dyn_attrs method so that in case the removal of an attribute fails, the others are still removed

//Revision 1.5  2006/03/27 13:58:18  ounsy
//added the commands removeAllDynAttr et removeDynAttrs

//Revision 1.4  2006/03/14 13:05:16  ounsy
//corrected the SNAP/spectrums/RW problem
//about the read and write values having the same length

//Revision 1.3  2006/03/08 16:31:13  ounsy
//added the global POGO class comments for the devices SnapExtractor

//Revision 1.2  2006/03/08 14:36:42  ounsy
//added pogo comments

//Revision 1.1  2006/02/07 13:03:52  ounsy
//moved from the Archiving package

//Revision 1.2  2006/02/06 13:11:20  ounsy
//corrected a bug for WO attributes

//Revision 1.1  2006/01/27 14:39:14  ounsy
//new device for snap extracting


//copyleft :   European Synchrotron Radiation Facility
//BP 220, Grenoble 38043
//FRANCE

//-============================================================================

//This file is generated by POGO
//(Program Obviously used to Generate tango Object)

//(c) - Software Engineering Group - ESRF
//=============================================================================


package SnapExtractor;


import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;

import fr.esrf.Tango.AttrDataFormat;
import fr.esrf.Tango.AttrWriteType;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DevVarLongStringArray;
import fr.esrf.TangoApi.DbDatum;
import fr.esrf.TangoDs.Attr;
import fr.esrf.TangoDs.Attribute;
import fr.esrf.TangoDs.DeviceClass;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.Except;
import fr.esrf.TangoDs.ImageAttr;
import fr.esrf.TangoDs.SpectrumAttr;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.TimedAttrData;
import fr.esrf.TangoDs.Util;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.DbData;
import fr.soleil.snapArchivingApi.SnapExtractorApi.convert.ConverterFactory;
import fr.soleil.snapArchivingApi.SnapExtractorApi.convert.IConverter;
import fr.soleil.snapArchivingApi.SnapExtractorApi.datasources.db.ISnapReader;
import fr.soleil.snapArchivingApi.SnapExtractorApi.datasources.db.SnapReaderFactory;
import fr.soleil.snapArchivingApi.SnapExtractorApi.devicelink.Warnable;
import fr.soleil.snapArchivingApi.SnapExtractorApi.lifecycle.LifeCycleManager;
import fr.soleil.snapArchivingApi.SnapExtractorApi.lifecycle.LifeCycleManagerFactory;
import fr.soleil.snapArchivingApi.SnapExtractorApi.naming.DynamicAttributeNamerFactory;
import fr.soleil.snapArchivingApi.SnapExtractorApi.naming.IDynamicAttributeNamer;
import fr.soleil.snapArchivingApi.SnapExtractorApi.tools.Tools;
import fr.soleil.snapArchivingApi.SnapshotingTools.Tools.SnapAttributeExtract;
import fr.soleil.snapArchivingApi.SnapshotingTools.Tools.SnapshotingException;


/**
 *	Class Description:
 *	This device is in charge of extracting snapshots from the SNAP database.
 *  It can look up the snapshots for a given context, and extract those snapshots.
 *
 * @author	$Author: soleilarc $
 * @version	$Revision: 1.16 $
 */

//--------- Start of States Description ----------
/*
 *	Device States Description:
 *	DevState.ON :	
 *	DevState.OFF :	
 *	DevState.INIT :	
 *	DevState.FAULT :	
 *	DevState.EXTRACT :	
 *	DevState.UNKNOWN :	
 */
//--------- End of States Description ----------


public class SnapExtractor extends DeviceImpl/*WithShutdownRunnable*/ implements TangoConst, Warnable
{
	protected	int	state;
	private String m_version; 

	//--------- Start of attributes data members ----------
	private int id = 0;
	//--------- End of attributes data members ----------


	//--------- Start of properties data members ----------

	/**
	 * User identifier (name) used to connect the database SNAP. <br>
	 * <b>Default value : </b> snap
	 */
	String	snapUser;
	/**
	 * Password used to connect the database SNAP. <br>
	 * <b>Default value : </b> snap
	 */
	String	snapPassword;

//	--------- End of properties data members ----------


	//	Add your own data members here
	protected int formerState = DevState._UNKNOWN;
	//--------------------------------------



//	=========================================================
	/**
	 *	Constructor for simulated Time Device Server.
	 *
	 *	@param	cl	The DeviceClass object
	 *	@param	s	The Device name.
	 *  @param   version The device version
	 */
//	=========================================================
	SnapExtractor(DeviceClass cl, String s, String version) throws DevFailed
	{
		super(cl,s);
		m_version = version;
		init_device();
	}
//	=========================================================
	/**
	 *	Constructor for simulated Time Device Server.
	 *
	 *	@param	cl	The DeviceClass object
	 *	@param	s	The Device name.
	 *	@param	d	Device description.
	 *  @param   version The device version
	 */
//	=========================================================
	SnapExtractor(DeviceClass cl, String s, String d, String version) throws DevFailed
	{
		super(cl,s,d);
		m_version = version;
		init_device();
	}


//	=========================================================
	/**
	 *	Initialize the device.
	 */
//	=========================================================
	public synchronized void init_device() throws DevFailed
	{
		System.out.println("SnapExtractor() create " + device_name);

		//	Initialise variables to default values
		get_device_property();
		LifeCycleManagerFactory.setUser ( this.snapUser);
		LifeCycleManagerFactory.setPassword ( this.snapPassword);
		LifeCycleManager lifeCycleManager = LifeCycleManagerFactory.getImpl( LifeCycleManagerFactory.DEFAULT_LIFE_CYCLE );
		lifeCycleManager.setWatcherToWarn ( this );

		Thread apiThread = lifeCycleManager.getAsThread ();
		apiThread.start ();
		//-------------------------------------------
		set_state(DevState.ON);
	}

	//=========================================================
	/**
	 * Execute command "RemoveDynamicAttribute" on device.
	 * Remove the dynamic attribute specified by its name argin.
	 *
	 * @param	argin	The dynamic attribute's name
	 */
	//=========================================================
	public void remove_dyn_attr(String argin) throws DevFailed
	{
		get_logger().info("Entering remove_dynamic_attribute()");

		// ---Add your Own code to control device here ---
		// Remove prop for the attribute
		String[] obj_to_del = new String[ 3 ];		
		obj_to_del[ 0 ] = device_name;
		obj_to_del[ 1 ] = "attribute";
		obj_to_del[ 2 ] = argin;
		Util tg = Util.instance();
		tg.get_dserver_device().rem_obj_polling(obj_to_del , false);

		// Remove the attribute
		remove_attribute(argin);
		get_logger().info("Exiting remove_dynamic_attribute()");
	}

//	=========================================================
	/**
	 * Execute command "RemoveDynamicAttributes" on device.
	 * Remove the dynamic attributes specified by the names list.
	 *
	 * @param	argin	The dynamic attributes names
	 */
	//=========================================================
	public void remove_dyn_attrs(String [] argin) throws DevFailed
	{
		get_logger().info("Entering remove_dyn_attrs()");

		// ---Add your Own code to control device here ---
		// Remove prop for the attribute
		if ( argin == null )
		{
			return;
		}

		for ( int i = 0 ; i < argin.length ; i ++ )
		{
			try
			{
				String[] obj_to_del = new String[ 3 ];		

				obj_to_del[ 0 ] = device_name;
				obj_to_del[ 1 ] = "attribute";
				obj_to_del[ 2 ] = argin [ i ];

				Util tg = Util.instance();
				tg.get_dserver_device().rem_obj_polling(obj_to_del , false);

				// Remove the attribute
				this.remove_attribute(argin [ i ]);    
			}
			catch ( Throwable t )
			{
				Tools.printIfDevFailed ( t );

				continue;
			}
		}

		get_logger().info("Exiting remove_dyn_attrs()");
	}

//	=========================================================
	/**
	 * Execute command "RemoveAllDynamicAttribute" on device.
	 * Remove all existing dynamic attributes.
	 *
	 * @param	argin	The dynamic attribute's name
	 */
	//=========================================================
	public void remove_all_dyn_attr() throws DevFailed
	{
		get_logger().info("Entering remove_dynamic_attribute()");

		// ---Add your Own code to control device here ---

		int numberOfAttributes = this.dev_attr == null ? 0 : this.dev_attr.get_attr_nb ();
		String [] attributesToRemove = new String [ numberOfAttributes ];
		for ( int i = 0 ; i < numberOfAttributes ; i++ ) 
		{
			Attribute nextAttribute = this.dev_attr.get_attr_by_ind ( i );
			String attributeName = nextAttribute.get_name ();
			//System.out.println ( "CLA/attributeName/"+attributeName);
			attributesToRemove [ i ] = attributeName;
		}

		this.remove_dyn_attrs ( attributesToRemove );		
	}


//	===================================================================
	/**
	 *	Read the device properties from database.
	 */
//	===================================================================			
	public void get_device_property() throws DevFailed
	{
		//	Initialize your default values here.
		//------------------------------------------
//		snapUser = ConfigConst.default_sauser;
//		snapPassword = ConfigConst.default_sapasswd;

		//	Read device properties from database.(Automatic code generation)
		//-------------------------------------------------------------
		if (Util._UseDb==false)
			return;
		String[]	propnames = {
				"DbUser",
				"DbPassword"
		};

		//	Call database and extract values
		//--------------------------------------------
		DbDatum[]	dev_prop = get_db_device().get_property(propnames);
		SnapExtractorClass	ds_class = (SnapExtractorClass)get_device_class();
		int	i = -1;
		//	Extract SnapUser value
		if (dev_prop[++i].is_empty()==false)
			snapUser = dev_prop[i].extractString();
		else
		{
			//	Try to get value from class property
			DbDatum	cl_prop = ds_class.get_class_property(dev_prop[i].name);
			if (cl_prop.is_empty()==false)	snapUser = cl_prop.extractString();
		}

		//	Extract SnapPassword value
		if (dev_prop[++i].is_empty()==false)
			snapPassword = dev_prop[i].extractString();
		else
		{
			//	Try to get value from class property
			DbDatum	cl_prop = ds_class.get_class_property(dev_prop[i].name);
			if (cl_prop.is_empty()==false)	snapPassword = cl_prop.extractString();
		}

		//	End of Automatic code generation
		//-------------------------------------------------------------

	}
//	=========================================================
	/**
	 *	Method always executed before command execution.
	 */
//	=========================================================
	public void always_executed_hook()
	{	
		get_logger().info("In always_executed_hook method()");
	}



//	=========================================================
	/**
	 * Execute command "GetSnap" on device.
	 * Loads a snapshot of given ID: 
	 * if the snapshot exists, creates for each one of the snapshot's attributes
	 * a dynamic attribute for its read part, and, if the attribute isn't read-only,
	 * for its write part.
	 * Returns a table containing, as many times as there are attributes for the specified snapshot: 
	 * <UL>
	 * <LI> the attribute complete name
	 * <LI> the name of the dynamic attribute containing the attribute's read value (if it has one, otherwise blank)
	 * <LI> the name of the dynamic attribute containing the attribute's write value (if it has one, otherwise blank)
	 * </UL>
	 * @param	argin	the snapshot's ID
	 * @return	A table containing [attrRealName, dynAttrNameR,dynAttrNameW]*n
	 * @throws DevFailed
	 */
//	=========================================================
	public synchronized String[] get_snap(int argin) throws DevFailed
	{
		String[]	argout = new String[5];
		get_logger().info("Entering get_snap()");

		// ---Add your Own code to control device here ---
		ISnapReader snapReader = SnapReaderFactory.getCurrentImpl ();

		try
		{
			SnapAttributeExtract [] sae = snapReader.getSnap ( argin );
			if ( sae == null || sae.length == 0 )
			{
				return emptyResult ();
			}
			argout = this.add_attributes ( sae );
		}
		catch ( Throwable t )
		{
			Tools.printIfDevFailed ( t );
			if ( t instanceof DevFailed )
			{
				throw (DevFailed) t;
			}
		}
		//------------------------------------------------

		get_logger().info("Exiting get_snap()");

		return argout;
	}
	//=========================================================
	/**
	 * Execute command "GetSnapValue" on device.
	 * Loads a snapshot of given ID: 
	 * if the snapshot exists, find if the attribute exists for this snapshot
	 * if the attribute exists, Returns an attribute value 
	 * @param	snapID	the snapshot's ID
	 * @param	attr_name	the attribute name
	 * @return	the attribute value
	 * @throws DevFailed
	 */
	//=========================================================
	public synchronized String[] get_snap_value(int snapID, String attr_name) throws DevFailed
	{ 
		get_logger().info("Entering get_snap_value()");
		String[] argout = {"NaN", "NaN"};
		// ---Add your Own code to control device here ---
		ISnapReader snapReader = SnapReaderFactory.getCurrentImpl ();

		try
		{
			SnapAttributeExtract [] sae = snapReader.getSnap ( snapID );
			if ( sae == null || sae.length == 0 )
			{
				return argout; 
			}

			int index = getIndexOfAttribute(sae, attr_name);
			if(index !=-1) {
				argout = this.get_attribute_value ( sae[index] );
			}  else {
				argout = getInvalidAttributeName();
			}
		}
		catch ( Throwable t )
		{
			Tools.printIfDevFailed ( t );
			if ( t instanceof DevFailed )
			{
				throw (DevFailed) t;
			}
		}
		//------------------------------------------------

		get_logger().info("Exiting get_snap_value()");

		return argout;
	}

	//=========================================================
	/**
	 * Execute command "GetSnapID" on device.
	 * Loads the list of snapshot' id  of a given context 
	 * that respect the given criterion.
	 * @param	ctxID	the context's ID
	 * @param	criterion	the search criterion
	 * @return	the list of snapshot ids.
	 * @throws DevFailed 
	 * @throws SnapshotingException 
	 */
	//=========================================================
	public synchronized int[] get_snap_id(int ctxID, String[] criterions) throws DevFailed 
	{ 
		get_logger().info("Entering get_snap_id()");
		
		
		ISnapReader snapReader = SnapReaderFactory.getCurrentImpl ();
		int[] snap_ids = snapReader.getSnapshotsID(ctxID, criterions);
		get_logger().info("Exiting get_snaps_id()");
		return snap_ids;
	}

	/**
	 * 
	 * @return a String[2] for an invalid attribute name callback
	 */
	private String[] getInvalidAttributeName() {
		// TODO Auto-generated method stub
		return new String[]{"Invalid attribute name",""};
	}

	/**
	 * 
	 * @param sae: list of SnapAttributeExtract
	 * @param attr_name: the given attribute name
	 * @return the index of the attribute in the list of SnapAttributeExtract.
	 */
	private int getIndexOfAttribute(SnapAttributeExtract[] sae, String attr_name) {
		// TODO Auto-generated method stub
		int index =  -1;
		for(int i=0;i<sae.length;i++){
			if(sae[i].getAttribute_complete_name().equalsIgnoreCase(attr_name)){
				index = i;
				break;
			}
		}

		return index;
	}

	/**
	 * 
	 * @param snapAttributeExtract
	 * @return read_value and write_value
	 */
	private synchronized String[] get_attribute_value(SnapAttributeExtract snapAttributeExtract) {
		// TODO Auto-generated method stub

		String[] argout = {"NaN", "NaN"};
		try{
			SnapAttributeExtract currentExtract = snapAttributeExtract;

			argout[0] = currentExtract.valueToString(0);
			argout[1] = currentExtract.valueToString(1);

		}
		catch(Exception e){

		}

		return argout;

	}
	/**
	 * Loads the list of snapshots attached to a given context, specified by its ID.
	 * Returns a DevVarLongStringArray object, where the array is as long as there are many snapshots for the context. 
	 * The Long part is the snapshot's ID, the String part is the concatenation of its Date and Comment fields values.
	 * @param argin The context ID
	 * @return  The snapshots: [ID, Date + Comment]*n 
	 * @throws DevFailed
	 */
	public synchronized DevVarLongStringArray get_snaps_for_context(int argin) throws DevFailed
	{
		get_logger().info("Entering get_snaps_for_context()");

		// ---Add your Own code to control device here ---
		ISnapReader snapReader = SnapReaderFactory.getCurrentImpl ();
		DevVarLongStringArray snapshots = snapReader.getSnapshotsForContext ( argin );
		if ( snapshots == null )
		{
			return emptyDevVarLongStringArrayResult ();
		}
		//------------------------------------------------

		get_logger().info("Exiting get_snaps_for_context()");
		return snapshots;
	}


	/**
	 * @return
	 */
	private DevVarLongStringArray emptyDevVarLongStringArrayResult() 
	{
		DevVarLongStringArray ret = new DevVarLongStringArray ();
		int [] lvalue = new int [ 1 ];
		java.lang.String [] svalue = new java.lang.String [ 1 ]; 

		lvalue [ 0 ] = -1;
		svalue [ 0 ] = "NO SNAPSHOT FOR THIS CONTEXT ID";

		ret.lvalue = lvalue;
		ret.svalue = svalue;

		return ret;
	}
	/**
	 * @return
	 */
	private String[] emptyResult() 
	{
		String[] argout = new String[3];

		argout [ 0 ] = "NO SUCH SNAPSHOT";
		argout [ 1 ] = "";
		argout [ 2 ] = "";

		return argout;
	}
//	=========================================================
	/**
	 *	main part for the device server class
	 */
//	=========================================================
	public static void main(String[] argv)
	{
		try
		{
			Util tg = Util.init(argv,"SnapExtractor");
			tg.server_init();

			System.out.println("Ready to accept request");

			tg.server_run();
		}

		catch (OutOfMemoryError ex)
		{
			System.err.println("Can't allocate memory !!!!");
			System.err.println("Exiting");
		}
		catch (UserException ex)
		{
			Except.print_exception(ex);

			System.err.println("Received a CORBA user exception");
			System.err.println("Exiting");
		}
		catch (SystemException ex)
		{
			Except.print_exception(ex);

			System.err.println("Received a CORBA system exception");
			System.err.println("Exiting");
		}

		System.exit(-1);		
	}

	private String [] add_attributes( SnapAttributeExtract [] sae ) throws DevFailed
	{
		int numberOfAttributes = sae.length;

		String [] argout = new String [ 3 * numberOfAttributes ];
		int j = 0;
		for ( int i = 0 ; i < numberOfAttributes ; i++ )
		{
			SnapAttributeExtract currentExtract = sae [ i ];
			String realName = currentExtract.getAttribute_complete_name ();

//			Object val = currentExtract.getValue(); 
			String[] names = this.addAttribute ( currentExtract );

			String read_name = names[ 0 ];
			String write_name = names[ 1 ];

			argout [ j ] = realName;
			argout [ j + 1 ] = read_name;
			argout [ j + 2 ] = write_name;

			j += 3;
		}

		return argout;
	}


	/**
	 * @param currentExtract
	 * @return
	 * @throws DevFailed
	 */
	private String[] addAttribute(SnapAttributeExtract currentExtract) throws DevFailed 
	{
		try
		{
			if ( currentExtract.getValue() == null )
			{
				String message = "Null value for attribute |" + currentExtract.getAttribute_complete_name() + "|";
				this.trace ( message , Warnable.LOG_LEVEL_WARN );
				return getErrorNames ( currentExtract );
			}

			IConverter converter = ConverterFactory.getCurrentImpl ();
			DbData dbData = converter.convert ( currentExtract );
			if ( dbData == null )
			{
				String message = "Null DbData for attribute |" + currentExtract.getAttribute_complete_name() + "| (if the attribute is R/W or is a spectrum, at least one of its value's component is null)";
				this.trace ( message , Warnable.LOG_LEVEL_WARN );
				return getErrorNames ( currentExtract );
			}

			String[] names = this.buildDynamicAttributesNames ( currentExtract );

			this.createDynamicAttribute ( dbData , names );

			return names;
		}
		catch ( Throwable t )
		{
			//Tools.printIfDevFailed ( t );
			String message = "Exception for attribute |" + currentExtract.getAttribute_complete_name() + "|";
			this.trace ( message , t , Warnable.LOG_LEVEL_WARN );
			return getErrorNames ( currentExtract );
		}
	}

	private String[] getErrorNames ( SnapAttributeExtract currentExtract )
	{
		String [] errorNames = new String[ 2 ];
		String name = currentExtract.getAttribute_complete_name ();

		errorNames[ 0 ] = name + "_ERROR";
		errorNames[ 1 ] = name + "_ERROR";

		return errorNames;
	}

	/**
	 * @param currentExtract
	 * @return
	 */
	private String[] buildDynamicAttributesNames(SnapAttributeExtract currentExtract) 
	{
		String random_name_1 = "" , random_name_2 = "";
		String[] names;

//		String name = currentExtract.getAttribute_complete_name ();
//		String dynamicAttributeName = "";
		//get_logger().info("CLA/buildRandomNames/name/"+name+"/");
		boolean hasBothReadAndWriteValues = 
			( 
					( currentExtract.getWritable() == AttrWriteType._READ_WITH_WRITE ) 
					||
					( currentExtract.getWritable() == AttrWriteType._READ_WRITE )
			);

		//	Build new Attribute's name
		IDynamicAttributeNamer dynamicAttributeNamer = DynamicAttributeNamerFactory.getCurrentImpl ();
		boolean firstIsRead = ( currentExtract.getWritable() != AttrWriteType._WRITE );
		random_name_1 = dynamicAttributeNamer.getName ( currentExtract , id , firstIsRead );
		//id++;
		//get_logger().info("CLA/buildRandomNames/random_name_1/"+random_name_1+"/");
		if ( hasBothReadAndWriteValues )
		{
			random_name_2 = dynamicAttributeNamer.getName ( currentExtract , id , !firstIsRead );
			//id++;
			//get_logger().info("CLA/buildRandomNames/random_name_2/"+random_name_2+"/");
		}
		id++;

		names = new String[ 2 ];
		names[ 0 ] = random_name_1;
		names[ 1 ] = random_name_2;

		return names;
	}

	private DevVarLongStringArray createDynamicAttribute(DbData dbData /*added by CLA*/, String [] names) throws DevFailed
	{
		try
		{
			DevVarLongStringArray argout = new DevVarLongStringArray();

			boolean _2value = ( dbData.getWritable() == AttrWriteType._READ_WITH_WRITE ||
					dbData.getWritable() == AttrWriteType._READ_WRITE ) ? true : false;

			//CLA modified
			String random_name_1 = names [0];
			String random_name_2 = names [1];

			if (  dbData.getData_type () == Tango_DEV_STATE )
			{
				//We have to convert state vars to int otherwise they're not recognised by the method fr.esrf.TangoDs.check_type() 			    
				dbData.setData_type ( Tango_DEV_LONG );    
			}

			// Create the attribute depends on DataFormat
			switch ( dbData.getData_format() )
			{
			case AttrDataFormat._SCALAR:
				add_attribute(new Attr(random_name_1 , dbData.getData_type() , AttrWriteType.READ));
				if ( _2value )
					add_attribute(new Attr(random_name_2 , dbData.getData_type() , AttrWriteType.READ));
				break;
			case AttrDataFormat._SPECTRUM:
				
				add_attribute(new SpectrumAttr(random_name_1 , dbData.getData_type() , dbData.getMax_x()));
				if ( _2value ){
					
					add_attribute(new SpectrumAttr(random_name_2 , dbData.getData_type() , dbData.getMax_x()));
				}
				break;
			case AttrDataFormat._IMAGE:
				add_attribute(new ImageAttr(random_name_1 , dbData.getData_type() , dbData.getMax_x() , dbData.getMax_y()));
				break;
			}


			set_polled_attr(names);
			set_poll_ring_depth(dbData.size());

			// Start polling for this device in external sync. mode (udt = 0)
			DevVarLongStringArray poll_1 , poll_2;
			poll_1 = new DevVarLongStringArray();
			poll_2 = new DevVarLongStringArray();

			poll_1.lvalue = new int[ 1 ];
			poll_1.lvalue[ 0 ] = 0;
			poll_1.svalue = new String[ 3 ];
			poll_1.svalue[ 0 ] = device_name;
			poll_1.svalue[ 1 ] = "attribute";
			poll_1.svalue[ 2 ] = random_name_1;

			if ( _2value )
			{
				poll_2.lvalue = new int[ 1 ];
				poll_2.lvalue[ 0 ] = 0;
				poll_2.svalue = new String[ 3 ];
				poll_2.svalue[ 0 ] = device_name;
				poll_2.svalue[ 1 ] = "attribute";
				poll_2.svalue[ 2 ] = random_name_2;
			}
			Util tg_1 = Util.instance();
			tg_1.get_dserver_device().add_obj_polling(poll_1 , false);

			Util tg_2 = Util.instance();
			if ( _2value )
			{
				tg_2.get_dserver_device().add_obj_polling(poll_2 , false);
			}    

			//	And fill buffer with database's data


			if ( _2value )
			{
				//System.out.println ( "CLA/SnapExtractor/" +dbData.);

				DbData[] dbDatas = dbData.splitDbData();
				DbData readDbData = dbDatas [0];
				DbData writeDbData = dbDatas [1];


				TimedAttrData [] readTimedAttrDatas = readDbData.getDataAsTimedAttrData();

				TimedAttrData [] writeTimedAttrDatas = writeDbData.getDataAsTimedAttrData();

				updateWriteData(writeTimedAttrDatas);
				
				tg_1.fill_attr_polling_buffer(this , random_name_1 , readTimedAttrDatas);
				tg_2.fill_attr_polling_buffer(this , random_name_2 , writeTimedAttrDatas);
			}
			else
			{
				tg_1.fill_attr_polling_buffer(this , random_name_1 , dbData.getDataAsTimedAttrData());
			}

			argout = new DevVarLongStringArray();
			argout.lvalue = new int[ 1 ];
			if ( !_2value )
			{
				argout.svalue = new String[ 1 ];
			}
			else
			{
				argout.svalue = new String[ 2 ];
			}

			argout.lvalue[ 0 ] = dbData.getData_timed().length;
			argout.svalue[ 0 ] = random_name_1;
			if ( _2value )
				argout.svalue[ 1 ] = random_name_2;

			return argout;
		}
		catch ( Throwable t )
		{
			Tools.throwDevFailed ( t );
		}
		return null;
	}

	/**
	 * update x dimension to the data array length
	 * @param data
	 */
	private void updateWriteData(TimedAttrData[] data) {
		// TODO Auto-generated method stub
		
		for(int i=0; i<data.length;i++){
			
			int size = 0;
	
			switch (data[i].data_type)
			{
			case Tango_DEV_BOOLEAN :
				size = data[i].bool_ptr.length;
				break;

			case Tango_DEV_SHORT :
				size = data[i].sh_ptr.length;
				break;

			case Tango_DEV_LONG :
				size = data[i].lg_ptr.length;
				break;	

			case Tango_DEV_LONG64 :
				size = data[i].lg64_ptr.length;
				break;

			case Tango_DEV_FLOAT :
				size = data[i].fl_ptr.length;
				break;

			case Tango_DEV_DOUBLE :
				size = data[i].db_ptr.length;
				break;

			case Tango_DEV_STRING :
				size = data[i].str_ptr.length;
				break;
			default: ;
			}
			data[i].x = size;
		}

	}
	public synchronized void warnOn() 
	{
		switch ( this.state )
		{
		case DevState._ALARM:
			//do nothing
			return;
		}

		this.set_state ( (short) DevState._ON );    
	}
	/* (non-Javadoc)
	 * @see archwatch.strategy.delay.WarnAble#warnFault()
	 */
	public synchronized void warnFault() 
	{
		this.set_state ( (short) DevState._FAULT );     
	}

	public synchronized void set_state ( DevState in )
	{
		this.set_state ( (short) in.value () );
	}

	public synchronized void set_state ( short _state )
	{
		this.formerState = this.state;
		super.set_state ( DevState.from_int ( _state ) );

		String _status = this.formatStatus ( _state );
		this.setStatus ( _status );
	}
	/* (non-Javadoc)
	 * @see archwatch.tools.WarnAble#warnAlarm()
	 */
	public synchronized void warnAlarm() 
	{
		this.set_state ( (short) DevState._ALARM );      
	}

	public synchronized void warnOff() 
	{
		switch ( this.state )
		{
		case DevState._ALARM:
			//do nothing
			return;

		case DevState._FAULT:
			//do nothing
			return;
		}

		this.set_state ( (short) DevState._OFF );      
	}

	public synchronized void warnInit() 
	{
		switch ( this.state )
		{
		case DevState._ALARM:
			//do nothing
			return;
		}

		this.set_state ( (short) DevState._INIT );      
	}
	/* (non-Javadoc)
	 * @see archwatch.tools.Warnable#setStatus(java.lang.String)
	 */
	public synchronized void setStatus(String status) 
	{
		super.set_status ( status );    
	}

	/**
	 * @param i
	 */
	private String formatStatus ( int _state ) 
	{
		switch ( _state )
		{
		case DevState._ALARM :
			return "Archiving problems have been detected."; 

		case DevState._FAULT :
			return "This device isn't working properly.";

		case DevState._INIT :
			return "No control step has been completed yet. Please wait.";

		case DevState._OFF :
			return "This device is waiting.";

		case DevState._ON :
			return "This device is running normally.";

		default :
			return "Unknown";
		}
	}

	private void goBackToFormerState ()
	{
		this.set_state ( (short) this.formerState );
	}

	/* (non-Javadoc)
	 * @see fr.soleil.hdbtdbArchivingApi.ArchivingWatchApi.devicelink.Warnable#trace(java.lang.String, int)
	 */
	public synchronized void trace ( String msg, int level ) throws DevFailed 
	{
		switch ( level )
		{
		case Warnable.LOG_LEVEL_DEBUG :
			get_logger ().debug ( msg );	    
			break;

		case Warnable.LOG_LEVEL_INFO :
			get_logger ().info ( msg );   
			break;

		case Warnable.LOG_LEVEL_WARN :
			get_logger ().warn ( msg );
			break;

		case Warnable.LOG_LEVEL_ERROR :
			get_logger ().error ( msg );
			break;

		case Warnable.LOG_LEVEL_FATAL :
			get_logger ().fatal ( msg );
			break;

		default :
			Tools.throwDevFailed ( new IllegalArgumentException ( "Expected LOG_LEVEL_DEBUG(9), LOG_LEVEL_INFO(7), LOG_LEVEL_WARN(5), LOG_LEVEL_ERROR(3), or LOG_LEVEL_FATAL(1), got " + level + "instead." ) );
		}
	}

	public synchronized void trace ( String msg , Throwable t , int level ) throws DevFailed 
	{
		switch ( level )
		{
		case Warnable.LOG_LEVEL_DEBUG :
			get_logger ().debug ( msg , t );	    
			break;

		case Warnable.LOG_LEVEL_INFO :
			get_logger ().info ( msg , t );   
			break;

		case Warnable.LOG_LEVEL_WARN :
			get_logger ().warn ( msg , t );
			break;

		case Warnable.LOG_LEVEL_ERROR :
			get_logger ().error ( msg , t );
			break;

		case Warnable.LOG_LEVEL_FATAL :
			get_logger ().fatal ( msg , t );
			break;

		default :
			Tools.throwDevFailed ( new IllegalArgumentException ( "Expected LOG_LEVEL_DEBUG(9), LOG_LEVEL_INFO(7), LOG_LEVEL_WARN(5), LOG_LEVEL_ERROR(3), or LOG_LEVEL_FATAL(1), got " + level + "instead." ) ); 
		}
	}    

//	===================================================================
	/**
	 * Method called by the read_attributes CORBA operation to
	 * set internal attribute value.
	 *
	 * @param   attr    reference to the Attribute object
	 */
//	===================================================================
	public void read_attr(Attribute attr) throws DevFailed
	{
		String attr_name = attr.get_name();
		get_logger().info("In read_attr for attribute " + attr_name);

		//  Switch on attribute name
		//---------------------------------
		if ( attr_name == "version" )
		{
			//  Add your own code here
			attr.set_value(m_version);
		}
	}
}
//--------------------------------------------------------------------------
/* end of $Source: /cvsroot/tango-cs/tango/jserver/snapshoting/SnapExtractor/SnapExtractor.java,v $ */
