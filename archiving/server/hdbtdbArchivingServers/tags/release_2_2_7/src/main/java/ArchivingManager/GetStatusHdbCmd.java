//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/ArchivingManager/GetStatusHdbCmd.java,v $
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
// $Log: GetStatusHdbCmd.java,v $
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
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.Except;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: For each attribute of the given list, get the status of
 * the device in charge of its historical archiving
 */

public class GetStatusHdbCmd extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class GetStatusHdbCmd
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public GetStatusHdbCmd(String name, int in, int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetStatusHdbCmd
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param in_comments
	 *            argin description
	 * @param out
	 *            argout type
	 * @param out_comments
	 *            argout description
	 */
	// ===============================================================
	public GetStatusHdbCmd(String name, int in, int out, String in_comments, String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetStatusHdbCmd
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param in_comments
	 *            argin description
	 * @param out
	 *            argout type
	 * @param out_comments
	 *            argout description
	 * @param level
	 *            The command display type OPERATOR or EXPERT
	 */
	// ===============================================================
	public GetStatusHdbCmd(String name, int in, int out, String in_comments, String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	@Override
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2.println("GetStatusHdbCmd.execute(): arrived");
		if (!(device instanceof ArchivingManager)) {
			Except.throw_exception("DEVICE_ERROR", "Device parameter is not instance of ArchivingManager", "ArchivingManager");
		}
		String[] argin = extract_DevVarStringArray(in_any);
		return insert(((ArchivingManager) (device)).get_status_hdb(argin));
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	@Override
	public boolean is_allowed(DeviceImpl device, Any data_in) {
		// End of Generated Code

		// Re-Start of Generated Code
		return true;
	}
}

// -----------------------------------------------------------------------------
/*
 * end of $Source:
 * /cvsroot/tango-cs/tango/jserver/archiving/ArchivingManager/GetStatusHdbCmd
 * .java,v $
 */
