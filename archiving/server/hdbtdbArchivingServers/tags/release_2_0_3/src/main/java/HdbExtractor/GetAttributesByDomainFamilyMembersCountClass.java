//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/HdbExtractor/GetAttributesByDomainFamilyMembersCountClass.java,v $
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
// $Log: GetAttributesByDomainFamilyMembersCountClass.java,v $
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

import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

/**
 * Class Description:
 * Returns the number of registered the attributes for a given domain, family, member.
 */


public class GetAttributesByDomainFamilyMembersCountClass extends Command implements TangoConst
{
	//===============================================================
	/**
	 * Constructor for Command class GetAttributesByDomainFamilyMembersCountClass
	 *
	 * @param	name	command name
	 * @param	in	argin type
	 * @param	out	argout type
	 */
	//===============================================================
	public GetAttributesByDomainFamilyMembersCountClass(String name , int in , int out)
	{
		super(name , in , out);
	}

	//===============================================================
	/**
	 * Constructor for Command class GetAttributesByDomainFamilyMembersCountClass
	 *
	 * @param	name command name
	 * @param	in argin type
	 * @param	in_comments argin description
	 * @param	out argout type
	 * @param	out_comments argout description
	 */
	//===============================================================
	public GetAttributesByDomainFamilyMembersCountClass(String name , int in , int out , String in_comments , String out_comments)
	{
		super(name , in , out , in_comments , out_comments);
	}
	//===============================================================
	/**
	 * Constructor for Command class GetAttributesByDomainFamilyMembersCountClass
	 *
	 * @param	name command name
	 * @param	in argin type
	 * @param	in_comments argin description
	 * @param	out argout type
	 * @param	out_comments argout description
	 * @param	level The command display type OPERATOR or EXPERT
	 */
	//===============================================================
	public GetAttributesByDomainFamilyMembersCountClass(String name , int in , int out , String in_comments , String out_comments , DispLevel level)
	{
		super(name , in , out , in_comments , out_comments , level);
	}
	//===============================================================
	/**
	 * return the result of the device's command.
	 */
	//===============================================================
	public Any execute(DeviceImpl device , Any in_any) throws DevFailed
	{
		Util.out2.println("GetAttributesByDomainFamilyMembersCountClass.execute(): arrived");
		String[] argin = extract_DevVarStringArray(in_any);
		return insert(( ( HdbExtractor ) ( device ) ).get_attributes_by_domain_family_members_count(argin));
	}

	//===============================================================
	/**
	 * Check if it is allowed to execute the command.
	 */
	//===============================================================
	public boolean is_allowed(DeviceImpl device , Any data_in)
	{
		//	End of Generated Code

		//	Re-Start of Generated Code
		return true;
	}
}

//-----------------------------------------------------------------------------
/* end of $Source: /cvsroot/tango-cs/tango/jserver/archiving/HdbExtractor/GetAttributesByDomainFamilyMembersCountClass.java,v $ */
