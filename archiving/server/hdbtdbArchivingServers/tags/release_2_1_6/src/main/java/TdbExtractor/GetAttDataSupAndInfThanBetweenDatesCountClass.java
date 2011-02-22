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
// Revision 1.1  2008/02/28 15:37:14  pierrejoseph
// TdbExtractor has been forgotten
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
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: Returns the number of data higher than the given value x,
 * (AND) lower than the given value y, and beetwen two dates (date_1 & date_2).
 */

public class GetAttDataSupAndInfThanBetweenDatesCountClass extends Command
		implements TangoConst {
	// ===============================================================
	/**
	 * Constructor for Command class
	 * GetAttDataSupAndInfThanBetweenDatesCountClass
	 * 
	 * @param name
	 *            command name
	 * @param in
	 *            argin type
	 * @param out
	 *            argout type
	 */
	// ===============================================================
	public GetAttDataSupAndInfThanBetweenDatesCountClass(String name, int in,
			int out) {
		super(name, in, out);
	}

	// ===============================================================
	/**
	 * Constructor for Command class
	 * GetAttDataSupAndInfThanBetweenDatesCountClass
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
	public GetAttDataSupAndInfThanBetweenDatesCountClass(String name, int in,
			int out, String in_comments, String out_comments) {
		super(name, in, out, in_comments, out_comments);
	}

	// ===============================================================
	/**
	 * Constructor for Command class
	 * GetAttDataSupAndInfThanBetweenDatesCountClass
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
	public GetAttDataSupAndInfThanBetweenDatesCountClass(String name, int in,
			int out, String in_comments, String out_comments, DispLevel level) {
		super(name, in, out, in_comments, out_comments, level);
	}

	// ===============================================================
	/**
	 * return the result of the device's command.
	 */
	// ===============================================================
	public Any execute(DeviceImpl device, Any in_any) throws DevFailed {
		Util.out2
				.println("GetAttDataSupAndInfThanBetweenDatesCountClass.execute(): arrived");
		String[] argin = extract_DevVarStringArray(in_any);
		return insert(((TdbExtractor) (device))
				.get_att_data_sup_and_inf_than_between_dates_count(argin));
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
/*
 * end of $Source:
 * /cvsroot/tango-cs/archiving/server/hdbtdbArchivingServers/src/
 * main/java/TdbExtractor/GetAttDataSupAndInfThanBetweenDatesCountClass.java,v $
 */
