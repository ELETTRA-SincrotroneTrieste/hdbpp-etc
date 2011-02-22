//+======================================================================
// $Source$
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               SnapManager class.
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.1  2006/04/21 09:05:28  ounsy
// New command "UpdateSnapComment" added
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
 * @author	$Author$
 * @version	$Revision$
 */
package SnapManager;

import org.omg.CORBA.Any;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevVarLongStringArray;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: This command updates the comment of given snapshot
 * 
 */

public class UpdateSnapCommentClass extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class UpdateSnapCommentClass
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public UpdateSnapCommentClass(String name, int in, int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class UpdateSnapCommentClass
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
	public UpdateSnapCommentClass(String name, int in, int out,
			String in_comments, String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class UpdateSnapCommentClass
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
	public UpdateSnapCommentClass(String name, int in, int out,
			String in_comments, String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2.println("UpdateSnapCommentClass.execute(): arrived");
		DevVarLongStringArray argin = extract_DevVarLongStringArray(in_any);
		((SnapManager) (device)).update_snap_comment(argin);
		return insert();
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	public boolean is_allowed(DeviceImpl device, Any data_in) {
		// End of Generated Code

		// Re-Start of Generated Code
		return true;
	}
}
// -----------------------------------------------------------------------------
/* end of $Source$ */
