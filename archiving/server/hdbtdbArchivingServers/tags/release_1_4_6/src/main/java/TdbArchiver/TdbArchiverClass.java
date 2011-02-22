//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/TdbArchiver/TdbArchiverClass.java,v $
//
// Project:   	Tango Device Server
//
// Description:	java source code for the TdbArchiver class .
//              This class is a singleton class and implements everything
//              which exists only once for all the  TdbArchiver object
//              It inherits from the DeviceClass class.
//
// $Author: ounsy $
//
// $Revision: 1.10 $
//
// $Log: TdbArchiverClass.java,v $
// Revision 1.10  2006/07/25 13:22:32  ounsy
// added a "version" attribute
//
// Revision 1.9  2006/03/08 14:36:21  ounsy
// added pogo comments
//
// Revision 1.8  2006/02/15 12:56:18  ounsy
// added retry_for_attribute and retry_for_attributes commands
//
// Revision 1.7  2006/02/15 11:10:12  chinkumo
// Javadoc comment update.
//
// Revision 1.6  2006/01/27 13:07:20  ounsy
// organised imports
//
// Revision 1.5  2005/11/29 17:34:14  chinkumo
// no message
//
// Revision 1.4.10.3  2005/11/29 16:15:11  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.4.10.2  2005/11/15 13:45:39  chinkumo
// ...
//
// Revision 1.4.10.1  2005/09/26 08:01:54  chinkumo
// Minor changes !
//
// Revision 1.4  2005/06/15 14:03:00  chinkumo
// The device was regenerated in Tango V5.
//
//
// copyleft :    European Synchrotron Radiation Facility
//               BP 220, Grenoble 38043
//               FRANCE
//
//-======================================================================
//
//  		This file is generated by POGO
//	(Program Obviously used to Generate tango Object)
//
//         (c) - Software Engineering Group - ESRF
//=============================================================================

package TdbArchiver;

import java.util.ResourceBundle;
import java.util.Vector;

import fr.esrf.Tango.AttrWriteType;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoApi.DbDatum;
import fr.esrf.TangoDs.Attr;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceClass;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

public class TdbArchiverClass extends DeviceClass implements TangoConst
{
	/**
	 * TdbArchiverClass class instance (it is a singleton).
	 */
	private static TdbArchiverClass _instance = null;

	/**
	 * Class properties array.
	 */
	private DbDatum[] cl_prop = null;

	//--------- Start of properties data members ----------

	/**
	 * Computer identifier on wich is settled the database TDB. The identifier can be the computer name or its IP address. <br>
	 * <b>Default value : </b> localhost
	 */
	String dbHost;
	/**
	 * Database name.<br>
	 * <b>Default value : </b> tdb
	 */
	String dbName;

//--------- End of properties data members ----------


//===================================================================			
//
// method : 		instance()
// 
// description : 	static method to retrieve the TdbArchiverClass object 
//					once it has been initialised
//
//===================================================================
	public static TdbArchiverClass instance()
	{
		if ( _instance == null )
		{
			System.err.println("TdbArchiverClass is not initialised !!!");
			System.err.println("Exiting");
			System.exit(-1);
		}
		return _instance;
	}

//===================================================================			
//
// method : 		Init()
// 
// description : 	static method to create/retrieve the TdbArchiverClass
//					object. This method is the only one which enables a 
//					user to create the object
//
// in :			- class_name : The class name
//
//===================================================================
	public static TdbArchiverClass init(String class_name) throws DevFailed
	{
		if ( _instance == null )
		{
			_instance = new TdbArchiverClass(class_name);
		}
		return _instance;
	}
	
//===================================================================			
//
// method : 		TdbArchiverClass()
// 
// description : 	constructor for the TdbArchiverClass class
//
// argument : in : 	- name : The class name
//
//===================================================================
	protected TdbArchiverClass(String name) throws DevFailed
	{
		super(name);

		Util.out2.println("Entering TdbArchiverClass constructor");
		write_class_property();
		get_class_property();

		Util.out2.println("Leaving TdbArchiverClass constructor");
	}
	
//===================================================================			
//
// method : 		command_factory()
// 
// description : 	Create the command object(s) and store them in the
//					command list
//===================================================================
	public void command_factory()
	{
	    command_list.addElement(new RetryForAttributesCmd("RetryForAttributes" ,
                Tango_DEVVAR_STRINGARRAY , Tango_DEV_SHORT ,
                "The complete names of the attributes" ,
                "A return code, can be either: 10 (the archiver isn't in charge of any of the specified attributes) 20 (the retry succeeded) or 30 (the retry failed)" ,
                DispLevel.OPERATOR));
	    
	    command_list.addElement(new RetryForAttributeCmd("RetryForAttribute" ,
                Tango_DEV_STRING , Tango_DEV_SHORT ,
                "The complete name of the attribute" ,
                "A return code, can be either: 10 (the archiver isn't in charge of the specified attribute) 20 (the retry succeeded) or 30 (the retry failed)" ,
                DispLevel.OPERATOR));
	    
		command_list.addElement(new TriggerArchiveConfCmd("TriggerArchiveConf" ,
		                                                  Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                  "The group of attributes to archive" ,
		                                                  "" ,
		                                                  DispLevel.OPERATOR));
		command_list.addElement(new TriggerArchiveAttCmd("TriggerArchiveAtt" ,
		                                                 Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                 "The attribute to archive" ,
		                                                 "" ,
		                                                 DispLevel.OPERATOR));
		command_list.addElement(new TriggerArchiveAttCheckCmd("TriggerArchiveAttCheck" ,
		                                                      Tango_DEV_STRING , Tango_DEV_VOID ,
		                                                      "The name of the attribute to archive" ,
		                                                      "" ,
		                                                      DispLevel.OPERATOR));
		command_list.addElement(new StopArchiveConfCmd("StopArchiveConf" ,
		                                               Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                               "The group of attributes" ,
		                                               "" ,
		                                               DispLevel.OPERATOR));
		command_list.addElement(new StopArchiveAttCmd("StopArchiveAtt" ,
		                                              Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                              "the attribute on witch archiving must be stopped" ,
		                                              "" ,
		                                              DispLevel.OPERATOR));
		command_list.addElement(new ExportData2DbCmd("ExportData2Db" ,
		                                             Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                             "the attribute from witch  data are expected." ,
		                                             "" ,
		                                             DispLevel.OPERATOR));
		command_list.addElement(new StateDetailedClass("StateDetailed" ,
		                                               Tango_DEV_VOID , Tango_DEV_STRING ,
		                                               "" ,
		                                               "The detailed state" ,
		                                               DispLevel.EXPERT));

		//	add polling if any
		for ( int i = 0 ; i < command_list.size() ; i++ )
		{
			Command cmd = ( Command ) command_list.elementAt(i);
		}
	}


//===================================================================			
//
// method : 		device_factory()
// 
// description : 	Create the device object(s) and store them in the 
//					device list
//
// argument : in : 	String[] devlist : The device name list
//
//===================================================================
	public void device_factory(String[] devlist) throws DevFailed
	{
		String device_version = ResourceBundle.getBundle("application").getString("project.version");
		for ( int i = 0 ; i < devlist.length ; i++ )
		{
			//Util.out4.println("Device name : " + devlist[ i ]);

			// Create device and add it into the device list
			//----------------------------------------------
			device_list.addElement(new TdbArchiver(this , devlist[ i ], device_version));

			// Export device to the outside world
			//----------------------------------------------
			if ( Util._UseDb == true )
				export_device(( ( DeviceImpl ) ( device_list.elementAt(i) ) ));
			else
				export_device(( ( DeviceImpl ) ( device_list.elementAt(i) ) ) , devlist[ i ]);
		}
	}

//=============================================================================
//
//	Method:	attribute_factory(Vector att_list)
//
//=============================================================================
	public void attribute_factory(Vector att_list) throws DevFailed
	{
		//	Attribute : scalar_charge
		Attr scalar_charge =
		        new Attr("scalar_charge" , Tango_DEV_SHORT , AttrWriteType.READ);
		att_list.addElement(scalar_charge);

		//	Attribute : spectrum_charge
		Attr spectrum_charge =
		        new Attr("spectrum_charge" , Tango_DEV_SHORT , AttrWriteType.READ);
		att_list.addElement(spectrum_charge);

		//	Attribute : image_charge
		Attr image_charge =
		        new Attr("image_charge" , Tango_DEV_SHORT , AttrWriteType.READ);
		att_list.addElement(image_charge);
        
         //  Attribute : version
        Attr version =
                new Attr("version" , Tango_DEV_STRING , AttrWriteType.READ);
        att_list.addElement(version);

	}
//===================================================================
	/**
	 * Get the class property for specified name.
	 *
	 * @param name The property name.
	 */
//===================================================================
	public DbDatum get_class_property(String name)
	{
		for ( int i = 0 ; i < cl_prop.length ; i++ )
			if ( cl_prop[ i ].name.equals(name) )
				return cl_prop[ i ];
		//	if not found, return  an empty DbDatum
		return new DbDatum(name);
	}

//===================================================================
	/**
	 * Read the class properties from database.
	 */
//===================================================================
	public void get_class_property() throws DevFailed
	{
		//	Initialize your default values here.
		//------------------------------------------


		//	Read class properties from database.(Automatic code generation)
		//-------------------------------------------------------------
		if ( Util._UseDb == false )
			return;
		String[] propnames = {
			"DbHost",
			"DbName"
		};

		//	Call database and extract values
		//--------------------------------------------
		cl_prop = get_db_class().get_property(propnames);
		int i = -1;
		//	Extract DbHost value
		if ( cl_prop[ ++i ].is_empty() == false )
			dbHost = cl_prop[ i ].extractString();
		else
			cl_prop[ i ].insert(dbHost);

		//	Extract DbName value
		if ( cl_prop[ ++i ].is_empty() == false )
			dbName = cl_prop[ i ].extractString();
		else
			cl_prop[ i ].insert(dbName);

		//	End of Automatic code generation
		//-------------------------------------------------------------

	}

//===================================================================
	/**
	 * Set class description as property in database
	 */
//===================================================================
	private void write_class_property() throws DevFailed
	{
		//	First time, check if database used
		//--------------------------------------------
		if ( Util._UseDb == false )
			return;

		//	Prepeare DbDatum
		//--------------------------------------------
		DbDatum[] data = new DbDatum[ 2 ];
		data[ 0 ] = new DbDatum("ProjectTitle");
		data[ 0 ].insert("Tango Device Server");

		data[ 1 ] = new DbDatum("Description");
		data[ 1 ].insert("Tis project defines the Tango DServer in charge of the intermediate archiving service.");

		//	Call database and and values
		//--------------------------------------------
		get_db_class().put_property(data);
	}

}
