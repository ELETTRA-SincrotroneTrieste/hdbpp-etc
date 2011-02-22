//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/snapshoting/SnapManager/SnapManagerClass.java,v $
//
// Project:   	Tango Device Server
//
// Description:	java source code for the SnapManager class .
//              This class is a singleton class and implements everything
//              which exists only once for all the  SnapManager object
//              It inherits from the DeviceClass class.
//
// $Author: pierrejoseph $
//
// $Revision: 1.14 $
//
// $Log: SnapManagerClass.java,v $
// Revision 1.14  2007/12/12 17:39:42  pierrejoseph
// Add comments on the SetEquipments method for the Javadoc
//
// Revision 1.13  2007/11/16 10:16:55  soleilarc
// Author: XPigeon
// Mantis bug ID: 5341
// Comment : Add the SetEquipments method in the commands list.
//
// Revision 1.12  2007/05/11 13:58:53  pierrejoseph
// Attribute addition : release version
//
// Revision 1.11  2006/12/06 10:16:02  ounsy
// minor changes
//
// Revision 1.10  2006/04/21 09:05:28  ounsy
// New command "UpdateSnapComment" added
//
// Revision 1.9  2006/03/27 13:58:35  ounsy
// organized imports
//
// Revision 1.8  2005/11/29 17:34:34  chinkumo
// no message
//
// Revision 1.7.2.2  2005/11/29 16:18:25  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.7.2.1  2005/11/15 13:45:32  chinkumo
// ...
//
// Revision 1.7  2005/08/19 14:03:26  chinkumo
// no message
//
// Revision 1.6.6.1  2005/08/11 08:16:44  chinkumo
// The 'SetEquipement' command and thus functionnality was added.
//
// Revision 1.6  2005/06/22 09:28:34  chinkumo
// Tango V5 regenerated.
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

package SnapManager;

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

public class SnapManagerClass extends DeviceClass implements TangoConst
{
	/**
	 * SnapManagerClass class instance (it is a singleton).
	 */
	private static SnapManagerClass _instance = null;

	/**
	 * Class properties array.
	 */
	private DbDatum[] cl_prop = null;

	//--------- Start of properties data members ----------


//--------- End of properties data members ----------


//===================================================================			
//
// method : 		instance()
// 
// description : 	static method to retrieve the SnapManagerClass object 
//					once it has been initialised
//
//===================================================================
	public static SnapManagerClass instance()
	{
		if ( _instance == null )
		{
			System.err.println("SnapManagerClass is not initialised !!!");
			System.err.println("Exiting");
			System.exit(-1);
		}
		return _instance;
	}

//===================================================================			
//
// method : 		Init()
// 
// description : 	static method to create/retrieve the SnapManagerClass
//					object. This method is the only one which enables a 
//					user to create the object
//
// in :			- class_name : The class name
//
//===================================================================
	public static SnapManagerClass init(String class_name) throws DevFailed
	{
		if ( _instance == null )
		{
			_instance = new SnapManagerClass(class_name);
		}
		return _instance;
	}
	
//===================================================================			
//
// method : 		SnapManagerClass()
// 
// description : 	constructor for the SnapManagerClass class
//
// argument : in : 	- name : The class name
//
//===================================================================
	protected SnapManagerClass(String name) throws DevFailed
	{
		super(name);

		Util.out2.println("Entering SnapManagerClass constructor");
		write_class_property();
		get_class_property();

		Util.out2.println("Leaving SnapManagerClass constructor");
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
		command_list.addElement(new CreateNewContextClass("CreateNewContext" ,
		                                                  Tango_DEVVAR_STRINGARRAY , Tango_DEV_LONG ,
		                                                  "All the informations usefull to create a context ,Snapshot pattern)." ,
		                                                  "The new assigned context ID" ,
		                                                  DispLevel.OPERATOR));
		command_list.addElement(new SetEquipmentsWithSnapshotClass("SetEquipmentsWithSnapshot" ,
		                                                           Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                           "The snapshot from which equipments are set." ,
		                                                           "" ,
		                                                           DispLevel.OPERATOR));
		command_list.addElement(new SetEquipmentsClass("SetEquipments" ,
                Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
                "The attributes from which equipments are set." ,
                "SetEquipments arguments... <BR><blockquote> <ul>             <li><strong>First Case:</strong> Setpoint is  done on all the snapshot attributes             <ul>                  <li><var>argin</var>[0]<b> =</b> the snap identifier<li><var>argin</var>[1]<b> =</b> STORED_READ_VALUE (Setpoint with theirs read values) or STORED_WRITE_VALUE (Setpoint with theirs write values)<br> </ul><li><strong>Second Case: </strong> Setpoint is done on a set of the snapshot attributes               <ul>                  <li><var>argin</var>[0]<b> =</b> the snap identifier<li><var>argin</var>[1]<b> =</b> the number of attributes <br>           Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 2).<li><var>argin</var>[index]<b> =</b> NEW_VALUE or STORED_READ_VALUE or STORED_WRITE_VALUE<li><var>argin</var>[index+1]<b> =</b> the attribut name <li><var>argin</var>[index+2]<b> =</b> the value to set when NEW_VALUE is requested </ul>          </blockquote>" ,
                DispLevel.OPERATOR));
		command_list.addElement(new UpdateSnapCommentClass("UpdateSnapComment",
			Tango_DEVVAR_LONGSTRINGARRAY, Tango_DEV_VOID,
			"1) snapshot identifier 2) The new comment",
			"",
			DispLevel.OPERATOR));
		
		//Modify LaunchSnapShot call from archiver to manager
	    command_list.addElement(new LaunchSnapShotCmd("LaunchSnapShot" ,
                Tango_DEV_SHORT , Tango_DEV_SHORT ,
                "The snapshot associated context's identifier." ,
                "" ,
                DispLevel.OPERATOR));


		//	add polling if any
		for ( int i = 0 ; i < command_list.size() ; i++ )
		{
			Command cmd = ( Command ) command_list.elementAt(i);
		}
	}

//	=============================================================================
	//
//			Method:	attribute_factory(Vector att_list)
	//
//		=============================================================================
	public void attribute_factory(Vector att_list) throws DevFailed
	{
	   //  Attribute : version
	   Attr version = new Attr("version" , Tango_DEV_STRING , AttrWriteType.READ);
	   att_list.addElement(version);
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
			device_list.addElement(new SnapManager(this , devlist[ i ], device_version));

			// Export device to the outside world
			//----------------------------------------------
			if ( Util._UseDb == true )
				export_device(( ( DeviceImpl ) ( device_list.elementAt(i) ) ));
			else
				export_device(( ( DeviceImpl ) ( device_list.elementAt(i) ) ) , devlist[ i ]);
		}
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
		};

		//	Call database and extract values
		//--------------------------------------------
		cl_prop = get_db_class().get_property(propnames);
		int i = -1;

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
		data[ 1 ].insert("This DServer provides the connections points and methods to the SnapShot service.");

		//	Call database and and values
		//--------------------------------------------
		get_db_class().put_property(data);
	}

}
