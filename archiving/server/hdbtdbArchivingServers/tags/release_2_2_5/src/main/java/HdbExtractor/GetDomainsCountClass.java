//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/HdbExtractor/GetDomainsCountClass.java,v $
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               HdbExtractor class.
//
// $Author: chinkumo $
//
// $Revision: 1.3 $
//
// $Log: GetDomainsCountClass.java,v $
// Revision 1.3  2005/11/29 16:17:38  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.2  2005/11/15 13:45:52  chinkumo
// ...
//
// Revision 1.1  2005/09/09 10:03:21  chinkumo
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
 * @author	$Author: chinkumo $
 * @version	$Revision: 1.3 $
 */
package HdbExtractor;

import org.omg.CORBA.Any;

import TdbExtractor.TdbExtractor;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description: Returns the number of distinct registered domains.
 */

public class GetDomainsCountClass extends Command implements TangoConst {
    // ===============================================================
    /**
     * Constructor for Command class GetDomainsCountClass
     * 
     * @param name
     *            command name
     * @param in
     *            argin type
     * @param out
     *            argout type
     */
    // ===============================================================
    public GetDomainsCountClass(final String name, final int in, final int out) {
	super(name, in, out);
    }

    // ===============================================================
    /**
     * Constructor for Command class GetDomainsCountClass
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
    public GetDomainsCountClass(final String name, final int in, final int out,
	    final String in_comments, final String out_comments) {
	super(name, in, out, in_comments, out_comments);
    }

    // ===============================================================
    /**
     * Constructor for Command class GetDomainsCountClass
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
    public GetDomainsCountClass(final String name, final int in, final int out,
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
	Util.out2.println("GetDomainsCountClass.execute(): arrived");
	Any result = null;
	if (device instanceof TdbExtractor) {
	    result = insert(((TdbExtractor) device).get_domains_count());
	}
	return result;
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
 * /cvsroot/tango-cs/tango/jserver/archiving/HdbExtractor/GetDomainsCountClass
 * .java,v $
 */
