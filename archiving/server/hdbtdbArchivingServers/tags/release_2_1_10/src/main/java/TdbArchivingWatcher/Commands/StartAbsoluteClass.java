//+======================================================================
// $Source$
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               ArchivingWatcher class.
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.1  2009/08/28 14:27:59  soleilarc
// add and organize admin report commands to the watcher devices
//
// Revision 1.1  2006/08/24 13:52:22  ounsy
// creation
//
// Revision 1.1  2006/01/27 13:07:54  ounsy
// New commands to start archiving control in absolute/relative mode
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
package TdbArchivingWatcher.Commands;

import org.omg.CORBA.Any;

import TdbArchivingWatcher.TdbArchivingWatcher;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: Start in absolute mode
 */

public class StartAbsoluteClass extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class StartAbsoluteClass
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public StartAbsoluteClass(String name, int in, int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class StartAbsoluteClass
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
	public StartAbsoluteClass(String name, int in, int out, String in_comments,
			String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class StartAbsoluteClass
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
	public StartAbsoluteClass(String name, int in, int out, String in_comments,
			String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2.println("StartAbsoluteClass.execute(): arrived");
		int[] argin = extract_DevVarLongArray(in_any);
		((TdbArchivingWatcher) (device)).start_absolute(argin);
		return insert();
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	public boolean is_allowed(DeviceImpl device, Any data_in) {
		if (device.get_state() == DevState.ON
				|| device.get_state() == DevState.FAULT
				|| device.get_state() == DevState.INIT
				|| device.get_state() == DevState.ALARM) {
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
 * /cvsroot/tango-cs/archiving/server/hdbtdbArchivingServers/src/
 * main/java/TdbArchivingWatcher/Commands/StartAbsoluteClass.java,v $
 */
