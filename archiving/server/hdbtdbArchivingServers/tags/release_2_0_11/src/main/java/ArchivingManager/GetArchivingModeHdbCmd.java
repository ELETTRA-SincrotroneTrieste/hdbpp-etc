//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/ArchivingManager/GetArchivingModeHdbCmd.java,v $
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               ArchivingManager class.
//
// $Author: chinkumo $
//
// $Revision: 1.4 $
//
// $Log: GetArchivingModeHdbCmd.java,v $
// Revision 1.4  2005/11/29 17:34:41  chinkumo
// no message
//
// Revision 1.3.10.2  2005/11/29 16:14:07  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.3.10.1  2005/11/15 13:46:24  chinkumo
// ...
//
// Revision 1.3  2005/06/14 10:24:03  chinkumo
// Branch (archivingManager_1_0_1-branch_0)  and HEAD merged.
//
// Revision 1.2.4.1  2005/06/13 14:27:26  chinkumo
// The ArchivingManager device was regenerated in Tango V5.
//
// Revision 1.2  2005/01/28 13:11:14  taurel
// Some changes in source files to be Pogo compatible
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



/**
 * @author	$Author: chinkumo $
 * @version	$Revision: 1.4 $
 */
package ArchivingManager;


import org.omg.CORBA.Any;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description:
 * Return the historical archiving mode applied to an attribute.
 */


public class GetArchivingModeHdbCmd extends Command implements TangoConst
{
	//===============================================================
	/**
	 * Constructor for Command class GetArchivingModeHdbCmd
	 *
	 * @param	name	command name
	 * @param	in	argin type
	 * @param	out	argout type
	 */
	//===============================================================
	public GetArchivingModeHdbCmd(String name , int in , int out)
	{
		super(name , in , out);
	}

	//===============================================================
	/**
	 * Constructor for Command class GetArchivingModeHdbCmd
	 *
	 * @param	name command name
	 * @param	in argin type
	 * @param	in_comments argin description
	 * @param	out argout type
	 * @param	out_comments argout description
	 */
	//===============================================================
	public GetArchivingModeHdbCmd(String name , int in , int out , String in_comments , String out_comments)
	{
		super(name , in , out , in_comments , out_comments);
	}
	//===============================================================
	/**
	 * Constructor for Command class GetArchivingModeHdbCmd
	 *
	 * @param	name command name
	 * @param	in argin type
	 * @param	in_comments argin description
	 * @param	out argout type
	 * @param	out_comments argout description
	 * @param	level The command display type OPERATOR or EXPERT
	 */
	//===============================================================
	public GetArchivingModeHdbCmd(String name , int in , int out , String in_comments , String out_comments , DispLevel level)
	{
		super(name , in , out , in_comments , out_comments , level);
	}
	//===============================================================
	/**
	 * return the result of the device's command.
	 */
	//===============================================================
	public Any execute(DeviceImpl device , Any in_any) throws DevFailed
	{
		Util.out2.println("GetArchivingModeHdbCmd.execute(): arrived");
		String argin = extract_DevString(in_any);
		return insert(( ( ArchivingManager ) ( device ) ).get_archiving_mode_hdb(argin));
	}

	//===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	//===============================================================
	public boolean is_allowed(DeviceImpl device , Any data_in)
	{
		if ( device.get_state() == DevState.INIT ||
		     device.get_state() == DevState.FAULT )
		{
			//	End of Generated Code

			//	Re-Start of Generated Code
			return false;
		}
		return true;
	}
}

//-----------------------------------------------------------------------------
/* end of $Source: /cvsroot/tango-cs/tango/jserver/archiving/ArchivingManager/GetArchivingModeHdbCmd.java,v $ */
