/**
 * @author	$Author: ounsy $
 * @version	$Revision: 1.3 $
 */
package HdbArchiver;

import org.omg.CORBA.Any;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.Except;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: Command that stops the archiving of an attibute. This
 * command need an AttributeLightMode type object. An AttributeLightMode type
 * object encapsulate all the informations needed to found the Collector in
 * charge of the archiving of the specified attribute The informations needed
 * are the type, the format, the writable property and the archiving mode
 */

public class RetryForAttributeCmd extends Command implements TangoConst {
    // ===============================================================
    /**
     * Constructor for Command class StopArchiveAttCmd
     * 
     * @param name
     *            command name
     * @param in
     *            argin type
     * @param out
     *            argout type
     */
    // ===============================================================
    public RetryForAttributeCmd(final String name, final int in, final int out) {
	super(name, in, out);
    }

    // ===============================================================
    /**
     * Constructor for Command class StopArchiveAttCmd
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
    public RetryForAttributeCmd(final String name, final int in, final int out,
	    final String in_comments, final String out_comments) {
	super(name, in, out, in_comments, out_comments);
    }

    // ===============================================================
    /**
     * Constructor for Command class StopArchiveAttCmd
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
    public RetryForAttributeCmd(final String name, final int in, final int out,
	    final String in_comments, final String out_comments, final DispLevel level) {
	super(name, in, out, in_comments, out_comments, level);
    }

    // ===============================================================
    /**
     * return the result of the device's command.
     */
    // ===============================================================
    @Override
    public Any execute(final DeviceImpl device, final Any in_any) throws DevFailed {
	Util.out2.println("HDB RetryForAttributeCmd.execute(): arrived");
	final String argin = super.extract_DevString(in_any);

	if (!(device instanceof HdbArchiver)) {
	    Except.throw_exception("DEVICE_ERROR",
		    "Device parameter is not instance of HdbArchiver", "HdbArchiver");
	}

	return insert(((HdbArchiver) device).retry_for_attribute(argin));
    }

    // ===============================================================
    /**
     * Check if it is allowed to execute the command.
     */
    // ===============================================================
    @Override
    public boolean is_allowed(final DeviceImpl device, final Any data_in) {
	if (device.get_state() == DevState.FAULT) {
	    // if (device.get_state() != DevState.ON) {
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
 * /cvsroot/tango-cs/tango/jserver/archiving/HdbArchiver/RetryForAttributeCmd
 * .java,v $
 */
