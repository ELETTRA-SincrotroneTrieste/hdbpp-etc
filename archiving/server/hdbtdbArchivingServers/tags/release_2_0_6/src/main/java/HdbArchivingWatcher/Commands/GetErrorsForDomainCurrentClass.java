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
// Revision 1.1  2006/08/24 13:51:57  ounsy
// creation
//
// Revision 1.1  2006/01/19 16:36:59  ounsy
// New device specialised in watching archiving.
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
package HdbArchivingWatcher.Commands;



import org.omg.CORBA.Any;

import HdbArchivingWatcher.HdbArchivingWatcher;

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 *	Class Description:
 *	
*/


public class GetErrorsForDomainCurrentClass extends Command implements TangoConst
{
	//===============================================================
	/**
	 *	Constructor for Command class GetErrorsForDomainCurrentClass
	 *
	 *	@param	name	command name
	 *	@param	in	argin type
	 *	@param	out	argout type
	 */
	//===============================================================
	public GetErrorsForDomainCurrentClass(String name,int in,int out)
	{
		super(name, in, out);
	}

	//===============================================================
	/**
	 *	Constructor for Command class GetErrorsForDomainCurrentClass
	 *
	 *	@param	name            command name
	 *	@param	in              argin type
	 *	@param	in_comments     argin description
	 *	@param	out             argout type
	 *	@param	out_comments    argout description
	 */
	//===============================================================
	public GetErrorsForDomainCurrentClass(String name,int in,int out, String in_comments, String out_comments)
	{
		super(name, in, out, in_comments, out_comments);
	}
	//===============================================================
	/**
	 *	Constructor for Command class GetErrorsForDomainCurrentClass
	 *
	 *	@param	name            command name
	 *	@param	in              argin type
	 *	@param	in_comments     argin description
	 *	@param	out             argout type
	 *	@param	out_comments    argout description
	 *	@param	level           The command display type OPERATOR or EXPERT
	 */
	//===============================================================
	public GetErrorsForDomainCurrentClass(String name,int in,int out, String in_comments, String out_comments, DispLevel level)
	{
		super(name, in, out, in_comments, out_comments, level);
	}
	//===============================================================
	/**
	 *	return the result of the device's command.
	 */
	//===============================================================
	public Any execute(DeviceImpl device,Any in_any) throws DevFailed
	{
		Util.out2.println("GetErrorsForDomainCurrentClass.execute(): arrived");
		String argin = extract_DevString(in_any);
		return insert(((HdbArchivingWatcher)(device)).get_errors_for_domain_current(argin));
	}

	//===============================================================
	/**
	 *	Check if it is allowed to execute the command.
	 */
	//===============================================================
	public boolean is_allowed(DeviceImpl device, Any data_in)
	{
		if (device.get_state() == DevState.OFF  ||
			device.get_state() == DevState.INIT)
		{
			//	End of Generated Code

			//	Re-Start of Generated Code
			return false;
		}
		return true;
	}
}
//-----------------------------------------------------------------------------
/* end of $Source$ */
