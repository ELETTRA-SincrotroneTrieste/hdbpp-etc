/**
 * @author  $Author$
 * @version $Revision$
 */
package TdbArchivingWatcher.Commands.AdminRepot;

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
 * Class Description:
 * 
 */

public class GetAttributesCountClass extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class GetAttributesCountClass
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public GetAttributesCountClass(String name, int in, int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetAttributesCountClass
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
	public GetAttributesCountClass(String name, int in, int out,
			String in_comments, String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetAttributesCountClass
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
	public GetAttributesCountClass(String name, int in, int out,
			String in_comments, String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2.println("GetAttributesCountClass.execute(): arrived");
		return insert(((TdbArchivingWatcher) (device))
				.get_current_archiving_attributes_count());
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	public boolean is_allowed(DeviceImpl device, Any data_in) {
		if (device.get_state() == DevState.OFF
				|| device.get_state() == DevState.INIT) {
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
 * main/java/TdbArchivingWatcher
 * /Commands/AdminRepot/GetAttributesCountClass.java,v $
 */
