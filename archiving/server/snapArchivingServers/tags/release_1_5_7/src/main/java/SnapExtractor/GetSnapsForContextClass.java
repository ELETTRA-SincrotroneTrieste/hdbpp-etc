//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/snapshoting/SnapExtractor/GetSnapsForContextClass.java,v $
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               SnapExtractor class.
//
// $Author: ounsy $
//
// $Revision: 1.1 $
//
// $Log: GetSnapsForContextClass.java,v $
// Revision 1.1  2006/02/07 13:03:52  ounsy
// moved from the Archiving package
//
// Revision 1.1  2006/01/27 14:39:14  ounsy
// new device for snap extracting
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
 * @author	$Author: ounsy $
 * @version	$Revision: 1.1 $
 */
package SnapExtractor;

import org.omg.CORBA.Any;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: Returns snapshots for a given context id
 */

public class GetSnapsForContextClass extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class GetSnapsForContextClass
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public GetSnapsForContextClass(String name, int in, int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetSnapsForContextClass
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
	public GetSnapsForContextClass(String name, int in, int out,
			String in_comments, String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetSnapsForContextClass
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
	public GetSnapsForContextClass(String name, int in, int out,
			String in_comments, String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2.println("GetSnapsForContextClass.execute(): arrived");
		int argin = extract_DevLong(in_any);
		return insert(((SnapExtractor) (device)).get_snaps_for_context(argin));
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	public boolean is_allowed(DeviceImpl device, Any data_in) {
		if (device.get_state() == DevState.OFF
				|| device.get_state() == DevState.INIT
				|| device.get_state() == DevState.FAULT
				|| device.get_state() == DevState.EXTRACT
				|| device.get_state() == DevState.UNKNOWN) {
			// End of Generated Code

			// Re-Start of Generated Code
			return false;
		}
		return true;
	}
}
// -----------------------------------------------------------------------------
/*
 * end of $Source:
 * /cvsroot/tango-cs/tango/jserver/snapshoting/SnapExtractor/GetSnapsForContextClass
 * .java,v $
 */
