//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/TdbArchiver/ExportData2DbCmd.java,v $
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               TdbArchiver class.
//
// $Author: chinkumo $
//
// $Revision: 1.4 $
//
// $Log: ExportData2DbCmd.java,v $
// Revision 1.4  2005/11/29 17:34:14  chinkumo
// no message
//
// Revision 1.3.10.3  2005/11/29 16:15:11  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.3.10.2  2005/11/15 13:45:39  chinkumo
// ...
//
// Revision 1.3.10.1  2005/09/26 08:01:54  chinkumo
// Minor changes !
//
// Revision 1.3  2005/06/15 14:02:59  chinkumo
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

/**
 * @author	$Author: chinkumo $
 * @version	$Revision: 1.4 $
 */
package TdbArchiver;

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
 * Class Description: This command need an AttributeLightMode type object. An
 * AttributeLightMode type object encapsulate all the informations needed to
 * found the Collector in charge of the archiving of the specified attribute The
 * informations needed are the type, the format, the writable property and the
 * archiving mode
 */

public class ExportData2DbCmd extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class ExportData2DbCmd
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public ExportData2DbCmd(String name, int in, int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class ExportData2DbCmd
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
	public ExportData2DbCmd(String name, int in, int out, String in_comments, String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class ExportData2DbCmd
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
	public ExportData2DbCmd(String name, int in, int out, String in_comments, String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	@Override
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2.println("ExportData2DbCmd.execute(): arrived");
		if (!(device instanceof TdbArchiver)) {
			Except.throw_exception("DEVICE_ERROR", "Device parameter is not instance of TdbArchiver", "TdbArchiver");
		}

		String[] argin = extract_DevVarStringArray(in_any);
		((TdbArchiver) (device)).export_data2_db(argin);
		return insert();
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	@Override
	public boolean is_allowed(DeviceImpl device, Any data_in) {
		if (device.get_state() == DevState.FAULT) {
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
 * /cvsroot/tango-cs/tango/jserver/archiving/TdbArchiver/ExportData2DbCmd.java,v
 * $
 */
