//+======================================================================
// $Source$
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               TdbExtractor class.
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.3  2010/11/22 08:09:15  abeilleg
// remove critical of findbugs
//
// Revision 1.2  2009/12/17 12:47:15  pierrejoseph
// CheckStyle:  Organize imports / Format
//
// Revision 1.1  2008/02/28 15:37:14  pierrejoseph
// TdbExtractor has been forgotten
//
// Revision 1.4  2006/02/15 11:12:20  chinkumo
// Javadoc comments updated.
//
// Revision 1.3  2005/11/29 16:13:31  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.2  2005/11/15 13:45:16  chinkumo
// ...
//
// Revision 1.1  2005/09/09 10:03:41  chinkumo
// First commit !
// (Dynamic attribut release)
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
package TdbExtractor;

import org.omg.CORBA.Any;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.Except;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: Gets the total number of attributes defined in TDB with
 * the given type.
 */

public class GetAttCountFilterTypeClass extends Command implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class GetAttCountFilterTypeClass
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public GetAttCountFilterTypeClass(final String name, final int in, final int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetAttCountFilterTypeClass
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
	public GetAttCountFilterTypeClass(final String name, final int in, final int out, final String in_comments, final String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class GetAttCountFilterTypeClass
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
	public GetAttCountFilterTypeClass(final String name, final int in, final int out, final String in_comments, final String out_comments, final DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	@Override
	public Any execute(final DeviceImpl device, final Any in_any) throws DevFailed {
		Util.out2.println("GetAttCountFilterTypeClass.execute(): arrived");
		final short argin = extract_DevShort(in_any);

		if (!(device instanceof TdbExtractor)) {
			Except.throw_exception("DEVICE_ERROR", "Device parameter is not instance of TdbExtractor", "TdbExtractor");
		}

		return insert(((TdbExtractor) device).get_att_count_filter_type(argin));
	}

	// ===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	// ===============================================================
	@Override
	public boolean is_allowed(final DeviceImpl device, final Any data_in) {
		// End of Generated Code

		// Re-Start of Generated Code
		return true;
	}
}

// -----------------------------------------------------------------------------
/*
 * end of $Source:
 * /cvsroot/tango-cs/archiving/server/hdbtdbArchivingServers/src/
 * main/java/TdbExtractor/GetAttCountFilterTypeClass.java,v $
 */
