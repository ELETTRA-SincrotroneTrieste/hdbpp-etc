//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/TdbArchivingWatcher/StartRelativeClass.java,v $
//
// Project:      Tango Device Server
//
// Description:  Java source code for the command TemplateClass of the
//               ArchivingWatcher class.
//
// $Author: ounsy $
//
// $Revision: 1.1 $
//
// $Log: StartRelativeClass.java,v $
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
 * @author	$Author: ounsy $
 * @version	$Revision: 1.1 $
 */
package TdbArchivingWatcher;



import org.omg.CORBA.*;
import fr.esrf.Tango.*;
import fr.esrf.TangoDs.*;

/**
 *	Class Description:
 *	Start in relative mode
*/


public class StartRelativeClass extends Command implements TangoConst
{
	//===============================================================
	/**
	 *	Constructor for Command class StartRelativeClass
	 *
	 *	@param	name	command name
	 *	@param	in	argin type
	 *	@param	out	argout type
	 */
	//===============================================================
	public StartRelativeClass(String name,int in,int out)
	{
		super(name, in, out);
	}

	//===============================================================
	/**
	 *	Constructor for Command class StartRelativeClass
	 *
	 *	@param	name            command name
	 *	@param	in              argin type
	 *	@param	in_comments     argin description
	 *	@param	out             argout type
	 *	@param	out_comments    argout description
	 */
	//===============================================================
	public StartRelativeClass(String name,int in,int out, String in_comments, String out_comments)
	{
		super(name, in, out, in_comments, out_comments);
	}
	//===============================================================
	/**
	 *	Constructor for Command class StartRelativeClass
	 *
	 *	@param	name            command name
	 *	@param	in              argin type
	 *	@param	in_comments     argin description
	 *	@param	out             argout type
	 *	@param	out_comments    argout description
	 *	@param	level           The command display type OPERATOR or EXPERT
	 */
	//===============================================================
	public StartRelativeClass(String name,int in,int out, String in_comments, String out_comments, DispLevel level)
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
		Util.out2.println("StartRelativeClass.execute(): arrived");
		double argin = extract_DevDouble(in_any);
		((TdbArchivingWatcher)(device)).start_relative(argin);
		return insert();
	}

	//===============================================================
	/**
	 *	Check if it is allowed to execute the command.
	 */
	//===============================================================
	public boolean is_allowed(DeviceImpl device, Any data_in)
	{
		if (device.get_state() == DevState.ON  ||
			device.get_state() == DevState.FAULT  ||
			device.get_state() == DevState.INIT  ||
			device.get_state() == DevState.ALARM)
		{
			//	End of Generated Code

			//	Re-Start of Generated Code
			return false;
		}
		return true;
	}
}
//-----------------------------------------------------------------------------
/* end of $Source: /cvsroot/tango-cs/tango/jserver/archiving/TdbArchivingWatcher/StartRelativeClass.java,v $ */
