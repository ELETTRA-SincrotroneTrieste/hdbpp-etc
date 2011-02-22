//+======================================================================
// $Source: /cvsroot/tango-cs/tango/jserver/archiving/ArchivingManager/ArchivingManagerClass.java,v $
//
// Project:   	Tango Device Server
//
// Description:	java source code for the ArchivingManager class .
//              This class is a singleton class and implements everything
//              which exists only once for all the  ArchivingManager object
//              It inherits from the DeviceClass class.
//
// $Author: pierrejoseph $
//
// $Revision: 1.9 $
//
// $Log: ArchivingManagerClass.java,v $
// Revision 1.9  2007/05/11 13:58:34  pierrejoseph
// Attribute addition : release version
//
// Revision 1.8  2006/10/09 12:53:57  chinkumo
// Argin comment modification for the archivingStartHdb command.
//
// Revision 1.7  2006/01/27 13:06:40  ounsy
// organised imports
//
// Revision 1.6  2005/11/29 17:34:41  chinkumo
// no message
//
// Revision 1.5.10.2  2005/11/29 16:14:07  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.5.10.1  2005/11/15 13:46:24  chinkumo
// ...
//
// Revision 1.5  2005/06/14 10:24:03  chinkumo
// Branch (archivingManager_1_0_1-branch_0)  and HEAD merged.
//
// Revision 1.4.4.1  2005/06/13 14:33:46  chinkumo
// The ArchivingManager device was regenerated in Tango V5.
// This class was also modified as some commands now returns a void object (ArchivingStartHdb, ArchivingStartTdb, ArchivingStopHdb, ArchivingStopTdb, ArchivingModifHdb, ArchivingModifTdb).
//
// Revision 1.4  2005/01/28 13:11:14  taurel
// Some changes in source files to be Pogo compatible
//
// Revision 1.3  2005/01/26 16:33:32  chinkumo
// Export of the new DServer source code.
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

package ArchivingManager;

import java.util.Vector;

import fr.esrf.Tango.AttrWriteType;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DispLevel;
import fr.esrf.TangoApi.DbDatum;
import fr.esrf.TangoDs.Attr;
import fr.esrf.TangoDs.Command;
import fr.esrf.TangoDs.DeviceClass;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;

public class ArchivingManagerClass extends DeviceClass implements TangoConst
{
	/**
	 * ArchivingManagerClass class instance (it is a singleton).
	 */
	private static ArchivingManagerClass _instance = null;

	/**
	 * Class properties array.
	 */
	private DbDatum[] cl_prop = null;

	//--------- Start of properties data members ----------


//--------- End of properties data members ----------


//===================================================================			
//
// method : 		instance()
// 
// description : 	static method to retrieve the ArchivingManagerClass object 
//					once it has been initialised
//
//===================================================================
	public static ArchivingManagerClass instance()
	{
		if ( _instance == null )
		{
			System.err.println("ArchivingManagerClass is not initialised !!!");
			System.err.println("Exiting");
			System.exit(-1);
		}
		return _instance;
	}

//===================================================================			
//
// method : 		Init()
// 
// description : 	static method to create/retrieve the ArchivingManagerClass
//					object. This method is the only one which enables a 
//					user to create the object
//
// in :			- class_name : The class name
//
//===================================================================
	public static ArchivingManagerClass init(String class_name) throws DevFailed
	{
		if ( _instance == null )
		{
			_instance = new ArchivingManagerClass(class_name);
		}
		return _instance;
	}
	
//===================================================================			
//
// method : 		ArchivingManagerClass()
// 
// description : 	constructor for the ArchivingManagerClass class
//
// argument : in : 	- name : The class name
//
//===================================================================
	protected ArchivingManagerClass(String name) throws DevFailed
	{
		super(name);

		Util.out2.println("Entering ArchivingManagerClass constructor");
		write_class_property();
		get_class_property();

		Util.out2.println("Leaving ArchivingManagerClass constructor");
	}
	
//===================================================================			
//
// method : 		command_factory()
// 
// description : 	Create the command object(s) and store them in the
//					command list
//===================================================================
	public void command_factory()
	{
		command_list.addElement(new ArchivingConfigureCmd("ArchivingConfigure" ,
		                                                  Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                  "<ul> <li><var>argin</var>[<code>0</code>], the <em>user name</em> used to logg into the historical database. <li><var>argin</var>[<code>1</code>], the <em>password</em> used to logg into the historical database. <li><var>argin</var>[<code>2</code>], the <em>user name</em> used to logg into the temporary database. <li><var>argin</var>[<code>9</code>], the <em>password</em> used to logg into the temporary database.   </ul>" ,
		                                                  "" ,
		                                                  DispLevel.OPERATOR));
		command_list.addElement(new ArchivingStartHdbCmd("ArchivingStartHdb" ,
		                                                 Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                 "Archiving arguments... <BR>         <blockquote> <ul>              <li><strong>The first part :</strong>              <ul>                  <li><var>argin</var>[0]<b> =</b> the load balancing type of the archiving<br>                   &quot;1&quot;, if all the attribute are archived together in the same HdbArchiver device, <br>                  &quot;0&quot; otherwise.                  <li><var>argin</var>[1]<b> =</b>                   the number of attributes to archive<br> <li><var>argin</var>[2]                  to <var>argin</var> [2 + <var>argin</var>[1] - 1] = the name of each attribute              </ul>              <li><strong>The second part (the <i>Mode </i>part) :</strong> <br>              Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 2]).             <ul>                  <li><strong>If the Mode is composed of a <i>Periodical Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_P</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the period of the periodic mode in (ms)<br>                  <var>index</var> = <var>index</var> + 2<br>                                    <li><strong>If the Mode is composed of an <i>Absolute Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_A</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>absolute mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the delta value max when decreasing<br>                  <var>argin</var>[<var>index</var>+ 4] = the delta value max when increasing<br>                  <var>index</var> = <var>index</var> + 4<br>                                    <li><strong>If the Mode is composed of a <i>Relative Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_R</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>relative mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the <i>decreasing variation </i>associated to this mode<br>                  <var>argin</var>[<var>index</var>+ 4] = the <i>increasing variation </i>associated to this mode<br>                  <var>index</var> = <var>index</var> + 4<br>                                    <li><strong>If the Mode is composed of an <i>Threshold Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_T</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>threshold mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the smallest value (min) when decreasing<br>                  <var>argin</var>[<var>index</var>+ 4] = the biggest value (max) when increasing<br>                  <var>index</var> = <var>index</var> + 4<br>                                    <li>If the Mode is composed of a <i>On Calculation Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_C</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>on calculation mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the <i>number of values</i> taken into account<br>                  <var>argin</var>[<var>index</var>+ 4] = the <i>type </i>associated to this mode<br>                  <var>argin</var>[<var>index</var>+ 5] = Not used at the moment <br>                  <var>index</var> = <var>index</var> + 5<br>                                    <li><strong>If the Mode is composed of an <i>On Difference Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_D</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of this<i> mode </i>(in ms)<br>                  <var>index</var> = <var>index</var> + 2<br>                                    <li><strong>If the Mode is composed of an <i>External Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_E</code><br>                  <var>index</var> = <var>index</var> + 1<br>                                            </ul>                       </ul>          </blockquote> " ,
		                                                 "" ,
		                                                 DispLevel.OPERATOR));
		command_list.addElement(new ArchivingStartTdbCmd("ArchivingStartTdb" ,
		                                                 Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                 "Archiving arguments... <BR><blockquote> <ul>              <li><strong>The first part :</strong>              <ul>                  <li><var>argin</var>[0]<b> =</b> the load balancing type of the archiving<br>                   &quot;1&quot;, if all the attribute are archived together in the same TdbArchiver device, <br>                  &quot;0&quot; otherwise.                  <li><var>argin</var>[1]<b> =</b>                   the number of attributes to archive<br> <li><var>argin</var>[2]                  to <var>argin</var> [2 + <var>argin</var>[1] - 1] = the name of each attribute              </ul>              <li><strong>The second part (the <i>Mode </i>part) :</strong> <br>              Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 2]).             <ul>                  <li><strong>If the Mode is composed of a <i>Periodical Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_P</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the period of the periodic mode in (ms)<br>                  <var>index</var> = <var>index</var> + 2<br>                                    <li><strong>If the Mode is composed of an <i>Absolute Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_A</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>absolute mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the delta value max when decreasing<br>                  <var>argin</var>[<var>index</var>+ 4] = the delta value max when increasing<br>                  <var>index</var> = <var>index</var> + 4<br>                                    <li><strong>If the Mode is composed of a <i>Relative Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_R</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>relative mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the <i>decreasing variation </i>associated to this mode<br>                  <var>argin</var>[<var>index</var>+ 4] = the <i>increasing variation </i>associated to this mode<br>                  <var>index</var> = <var>index</var> + 4<br>                                    <li><strong>If the Mode is composed of an <i>Threshold Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_T</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>threshold mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the smallest value (min) when decreasing<br>                  <var>argin</var>[<var>index</var>+ 4] = the biggest value (max) when increasing<br>                  <var>index</var> = <var>index</var> + 4<br>                                    <li>If the Mode is composed of a <i>On Calculation Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_C</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>on calculation mode </i>in (ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the <i>number of values</i> taken into account<br>                  <var>argin</var>[<var>index</var>+ 4] = the <i>type </i>associated to this mode<br>                  <var>argin</var>[<var>index</var>+ 5] = Not used at the moment <br>                  <var>index</var> = <var>index</var> + 5<br>                                    <li><strong>If the Mode is composed of an <i>On Difference Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_D</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of this<i> mode </i>(in ms)<br>                  <var>index</var> = <var>index</var> + 2<br>                                    <li><strong>If the Mode is composed of an <i>External Mode</i></strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>MODE_E</code><br>                  <var>index</var> = <var>index</var> + 1<br>                                    <li><strong>The Temporary (<i>Intermediate Archiving</i>) archiving specific informations </strong><br>                  <var>argin</var>[<var>index</var>+ 1] = <code>TDB_SPEC</code><br>                  <var>argin</var>[<var>index</var>+ 2] = the <i>export frequency </i>(ms)<br>                  <var>argin</var>[<var>index</var>+ 3] = the <i>keeping window duration </i>in (ms)<br>                  <var>index</var> = <var>index</var> + 3<br>             </ul>                       </ul>          </blockquote>" ,
		                                                 "" ,
		                                                 DispLevel.OPERATOR));
		command_list.addElement(new ArchivingStopHdbCmd("ArchivingStopHdb" ,
		                                                Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                "The attribute list." ,
		                                                "" ,
		                                                DispLevel.OPERATOR));
		command_list.addElement(new ArchivingStopTdbCmd("ArchivingStopTdb" ,
		                                                Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                "The attribute list." ,
		                                                "" ,
		                                                DispLevel.OPERATOR));
		command_list.addElement(new ArchivingModifHdbCmd("ArchivingModifHdb" ,
		                                                 Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                 "The configuration to switch to... <br> <blockquote> <ul> <li><strong>The first part :</strong>  <ul> <li><var>argin</var>[0]<b> =</b> the load balancing type of the archiving<br>  &quot;1&quot;, if all the attribute are archived together in the same TdbArchiver device, <br> &quot;0&quot; otherwise. <li><var>argin</var>[1]<b> =</b>  the number of attributes to archive<br> <li><var>argin</var>[2] to <var>argin</var> [2 + <var>argin</var>[1] - 1] = the name of each attribute  </ul> <li><strong>The second part (the <i>Mode </i>part) :</strong> <br> Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 2]). <ul> <li><strong>If the Mode is composed of a <i>Periodical Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_P</code><br> <var>argin</var>[<var>index</var>+ 2] = the period of the periodic mode in (ms)<br> <var>index</var> = <var>index</var> + 2<br>  <li><strong>If the Mode is composed of an <i>Absolute Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_A</code><br> <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>absolute mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the delta value max when decreasing<br> <var>argin</var>[<var>index</var>+ 4] = the delta value max when increasing<br> <var>index</var> = <var>index</var> + 4<br>  <li><strong>If the Mode is composed of a <i>Relative Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_R</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>relative mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the <i>decreasing variation </i>associated to this mode<br> <var>argin</var>[<var>index</var>+ 4] = the <i>increasing variation </i>associated to this mode<br> <var>index</var> = <var>index</var> + 4<br>  <li><strong>If the Mode is composed of an <i>Threshold Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_T</code><br> <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>threshold mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the smallest value (min) when decreasing<br> <var>argin</var>[<var>index</var>+ 4] = the biggest value (max) when increasing<br> <var>index</var> = <var>index</var> + 4<br>  <li>If the Mode is composed of a <i>On Calculation Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_C</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>on calculation mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the <i>number of values</i> taken into account<br> <var>argin</var>[<var>index</var>+ 4] = the <i>type </i>associated to this mode<br> <var>argin</var>[<var>index</var>+ 5] = Not used at the moment <br> <var>index</var> = <var>index</var> + 5<br>  <li><strong>If the Mode is composed of an <i>On Difference Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_D</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of this<i> mode </i>(in ms)<br> <var>index</var> = <var>index</var> + 2<br>  <li><strong>If the Mode is composed of an <i>External Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_E</code><br> <var>index</var> = <var>index</var> + 1<br>  </ul>  </ul> </blockquote> " ,
		                                                 "" ,
		                                                 DispLevel.OPERATOR));
		command_list.addElement(new ArchivingModifTdbCmd("ArchivingModifTdb" ,
		                                                 Tango_DEVVAR_STRINGARRAY , Tango_DEV_VOID ,
		                                                 "The configuration to switch to...... <br> <blockquote> <ul> <li><strong>The first part :</strong>  <ul> <li><var>argin</var>[0]<b> =</b> the load balancing type of the archiving<br>  &quot;1&quot;, if all the attribute are archived together in the same TdbArchiver device, <br> &quot;0&quot; otherwise. <li><var>argin</var>[1]<b> =</b>  the number of attributes to archive<br> <li><var>argin</var>[2] to <var>argin</var> [2 + <var>argin</var>[1] - 1] = the name of each attribute  </ul> <li><strong>The second part (the <i>Mode </i>part) :</strong> <br> Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 2]). <ul> <li><strong>If the Mode is composed of a <i>Periodical Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_P</code><br> <var>argin</var>[<var>index</var>+ 2] = the period of the periodic mode in (ms)<br> <var>index</var> = <var>index</var> + 2<br>  <li><strong>If the Mode is composed of an <i>Absolute Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_A</code><br> <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>absolute mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the delta value max when decreasing<br> <var>argin</var>[<var>index</var>+ 4] = the delta value max when increasing<br> <var>index</var> = <var>index</var> + 4<br>  <li><strong>If the Mode is composed of a <i>Relative Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_R</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>relative mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the <i>decreasing variation </i>associated to this mode<br> <var>argin</var>[<var>index</var>+ 4] = the <i>increasing variation </i>associated to this mode<br> <var>index</var> = <var>index</var> + 4<br>  <li><strong>If the Mode is composed of an <i>Threshold Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_T</code><br> <var>argin</var>[<var>index</var>+ 2] = the frequency of the <i>threshold mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the smallest value (min) when decreasing<br> <var>argin</var>[<var>index</var>+ 4] = the biggest value (max) when increasing<br> <var>index</var> = <var>index</var> + 4<br>  <li>If the Mode is composed of a <i>On Calculation Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_C</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of the <i>on calculation mode </i>in (ms)<br> <var>argin</var>[<var>index</var>+ 3] = the <i>number of values</i> taken into account<br> <var>argin</var>[<var>index</var>+ 4] = the <i>type </i>associated to this mode<br> <var>argin</var>[<var>index</var>+ 5] = Not used at the moment <br> <var>index</var> = <var>index</var> + 5<br>  <li><strong>If the Mode is composed of an <i>On Difference Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_D</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>frequency </i>of this<i> mode </i>(in ms)<br> <var>index</var> = <var>index</var> + 2<br>  <li><strong>If the Mode is composed of an <i>External Mode</i></strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>MODE_E</code><br> <var>index</var> = <var>index</var> + 1<br>  <li><strong>The Temporary (<i>Intermediate Archiving</i>) archiving specific informations </strong><br> <var>argin</var>[<var>index</var>+ 1] = <code>TDB_SPEC</code><br> <var>argin</var>[<var>index</var>+ 2] = the <i>export frequency </i>(ms)<br> <var>argin</var>[<var>index</var>+ 3] = the <i>keeping window duration </i>in (ms)<br> <var>index</var> = <var>index</var> + 3<br> </ul>  </ul> </blockquote> " ,
		                                                 "" ,
		                                                 DispLevel.OPERATOR));
		command_list.addElement(new IsArchivedHdbCmd("IsArchivedHdb" ,
		                                             Tango_DEVVAR_STRINGARRAY , Tango_DEVVAR_SHORTARRAY ,
		                                             "The attribute list." ,
		                                             "For each attribute of the given list...<br>   <ul> <li><code>1</code>, if the attribute is currently being archived (historical archiving) <li><code>0</code>, otherwise </ul>" ,
		                                             DispLevel.OPERATOR));
		command_list.addElement(new IsArchivedTdbCmd("IsArchivedTdb" ,
		                                             Tango_DEVVAR_STRINGARRAY , Tango_DEVVAR_SHORTARRAY ,
		                                             "The attribute list." ,
		                                             "For each attribute of the given list...<br>   <ul> <li><code>1</code>, if the attribute is currently being archived (temporary archiving) <li><code>0</code>, otherwise </ul>" ,
		                                             DispLevel.OPERATOR));
		command_list.addElement(new GetArchivingModeHdbCmd("GetArchivingModeHdb" ,
		                                                   Tango_DEV_STRING , Tango_DEVVAR_STRINGARRAY ,
		                                                   "The attribute name." ,
		                                                   "The applied mode... <br> <blockquote> <ul>             Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 0]).      <li><strong>If the Mode is composed of a <i>Periodical Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_P</code><br>     <var>argout</var>[<var>index</var> + 1] = the period of the periodic mode in (ms)<br>     <var>index</var> = <var>index</var> + 2<br>          <li><strong>If the Mode is composed of an <i>Absolute Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_A</code><br>     <var>argout</var>[<var>index</var>+ 1] = the frequency of the <i>absolute mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the delta value max when decreasing<br>     <var>argout</var>[<var>index</var>+ 3] = the delta value max when increasing<br>     <var>index</var> = <var>index</var> + 4<br>          <li><strong>If the Mode is composed of a <i>Relative Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_R</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>frequency </i>of the <i>relative mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the <i>decreasing variation </i>associated to this mode<br>     <var>argout</var>[<var>index</var>+ 3] = the <i>increasing variation </i>associated to this mode<br>     <var>index</var> = <var>index</var> + 4<br>          <li><strong>If the Mode is composed of an <i>Threshold Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_T</code><br>     <var>argout</var>[<var>index</var>+ 1] = the frequency of the <i>threshold mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the smallest value (min) when decreasing<br>     <var>argout</var>[<var>index</var>+ 3] = the biggest value (max) when increasing<br>     <var>index</var> = <var>index</var> + 4<br>          <li>If the Mode is composed of a <i>On Calculation Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_C</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>frequency </i>of the <i>on calculation mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the <i>number of values</i> taken into account<br>     <var>argout</var>[<var>index</var>+ 3] = the <i>type </i>associated to this mode<br>     <var>argout</var>[<var>index</var>+ 4] = Not used at the moment <br>     <var>index</var> = <var>index</var> + 5<br>          <li><strong>If the Mode is composed of an <i>On Difference Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_D</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>frequency </i>of this<i> mode </i>(in ms)<br>     <var>index</var> = <var>index</var> + 2<br>          <li><strong>If the Mode is composed of an <i>External Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_E</code><br>     <var>index</var> = <var>index</var> + 1<br>     </ul> </blockquote> " ,
		                                                   DispLevel.OPERATOR));
		command_list.addElement(new GetArchivingModeTdbCmd("GetArchivingModeTdb" ,
		                                                   Tango_DEV_STRING , Tango_DEVVAR_STRINGARRAY ,
		                                                   "The attribute name." ,
		                                                   "The applied mode... <br> <blockquote> <ul>             Let us note <i>&quot;<var>index</var>&quot; </i>the last <var>index</var> used (for example, at this point, <i><var>index</var></i> = 0]).      <li><strong>If the Mode is composed of a <i>Periodical Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_P</code><br>     <var>argout</var>[<var>index</var> + 1] = the period of the periodic mode in (ms)<br>     <var>index</var> = <var>index</var> + 2<br>          <li><strong>If the Mode is composed of an <i>Absolute Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_A</code><br>     <var>argout</var>[<var>index</var>+ 1] = the frequency of the <i>absolute mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the delta value max when decreasing<br>     <var>argout</var>[<var>index</var>+ 3] = the delta value max when increasing<br>     <var>index</var> = <var>index</var> + 4<br>          <li><strong>If the Mode is composed of a <i>Relative Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_R</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>frequency </i>of the <i>relative mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the <i>decreasing variation </i>associated to this mode<br>     <var>argout</var>[<var>index</var>+ 3] = the <i>increasing variation </i>associated to this mode<br>     <var>index</var> = <var>index</var> + 4<br>          <li><strong>If the Mode is composed of an <i>Threshold Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_T</code><br>     <var>argout</var>[<var>index</var>+ 1] = the frequency of the <i>threshold mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the smallest value (min) when decreasing<br>     <var>argout</var>[<var>index</var>+ 3] = the biggest value (max) when increasing<br>     <var>index</var> = <var>index</var> + 4<br>          <li>If the Mode is composed of a <i>On Calculation Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_C</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>frequency </i>of the <i>on calculation mode </i>in (ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the <i>number of values</i> taken into account<br>     <var>argout</var>[<var>index</var>+ 3] = the <i>type </i>associated to this mode<br>     <var>argout</var>[<var>index</var>+ 4] = Not used at the moment <br>     <var>index</var> = <var>index</var> + 5<br>          <li><strong>If the Mode is composed of an <i>On Difference Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_D</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>frequency </i>of this<i> mode </i>(in ms)<br>     <var>index</var> = <var>index</var> + 2<br>          <li><strong>If the Mode is composed of an <i>External Mode</i></strong><br>     <var>argout</var>[<var>index</var>] = <code>MODE_E</code><br>     <var>index</var> = <var>index</var> + 1<br>          <li><strong>The Temporary (<i>Intermediate Archiving</i>) archiving specific informations </strong><br>     <var>argout</var>[<var>index</var>] = <code>TDB_SPEC</code><br>     <var>argout</var>[<var>index</var>+ 1] = the <i>export frequency </i>(ms)<br>     <var>argout</var>[<var>index</var>+ 2] = the <i>keeping window duration </i>in (ms)<br>     <var>index</var> = <var>index</var> + 3<br> </ul> </blockquote> " ,
		                                                   DispLevel.OPERATOR));
		command_list.addElement(new GetStatusHdbCmd("GetStatusHdb" ,
		                                            Tango_DEVVAR_STRINGARRAY , Tango_DEVVAR_STRINGARRAY ,
		                                            "The attribute list." ,
		                                            "The list of status." ,
		                                            DispLevel.OPERATOR));
		command_list.addElement(new GetStatusTdbCmd("GetStatusTdb" ,
		                                            Tango_DEVVAR_STRINGARRAY , Tango_DEVVAR_STRINGARRAY ,
		                                            "The attribute list." ,
		                                            "The list of status." ,
		                                            DispLevel.OPERATOR));

		//	add polling if any
		for ( int i = 0 ; i < command_list.size() ; i++ )
		{
			Command cmd = ( Command ) command_list.elementAt(i);
		}
	}


//===================================================================			
//
// method : 		device_factory()
// 
// description : 	Create the device object(s) and store them in the 
//					device list
//
// argument : in : 	String[] devlist : The device name list
//
//===================================================================
	public void device_factory(String[] devlist) throws DevFailed
	{

		for ( int i = 0 ; i < devlist.length ; i++ )
		{
			Util.out4.println("Device name : " + devlist[ i ]);

			// Create device and add it into the device list
			//----------------------------------------------
			device_list.addElement(new ArchivingManager(this , devlist[ i ]));

			// Export device to the outside world
			//----------------------------------------------
			if ( Util._UseDb == true )
				export_device(( ( DeviceImpl ) ( device_list.elementAt(i) ) ));
			else
				export_device(( ( DeviceImpl ) ( device_list.elementAt(i) ) ) , devlist[ i ]);
		}
	}
//	=============================================================================
	//
//			Method:	attribute_factory(Vector att_list)
	//
//		=============================================================================
	public void attribute_factory(Vector att_list) throws DevFailed
	{
	   //  Attribute : version
	   Attr version = new Attr("version" , Tango_DEV_STRING , AttrWriteType.READ);
	   att_list.addElement(version);
	}
//===================================================================
	/**
	 * Get the class property for specified name.
	 *
	 * @param name The property name.
	 */
//===================================================================
	public DbDatum get_class_property(String name)
	{
		for ( int i = 0 ; i < cl_prop.length ; i++ )
			if ( cl_prop[ i ].name.equals(name) )
				return cl_prop[ i ];
		//	if not found, return  an empty DbDatum
		return new DbDatum(name);
	}

//===================================================================
	/**
	 * Read the class properties from database.
	 */
//===================================================================
	public void get_class_property() throws DevFailed
	{
		//	Initialize your default values here.
		//------------------------------------------


		//	Read class properties from database.(Automatic code generation)
		//-------------------------------------------------------------
		if ( Util._UseDb == false )
			return;
		String[] propnames = {
		};

		//	Call database and extract values
		//--------------------------------------------
		cl_prop = get_db_class().get_property(propnames);
		int i = -1;

		//	End of Automatic code generation
		//-------------------------------------------------------------

	}

//===================================================================
	/**
	 * Set class description as property in database
	 */
//===================================================================
	private void write_class_property() throws DevFailed
	{
		//	First time, check if database used
		//--------------------------------------------
		if ( Util._UseDb == false )
			return;

		//	Prepeare DbDatum
		//--------------------------------------------
		DbDatum[] data = new DbDatum[ 2 ];
		data[ 0 ] = new DbDatum("ProjectTitle");
		data[ 0 ].insert("Tango Device Server");

		data[ 1 ] = new DbDatum("Description");
		data[ 1 ].insert("Device of Archiving system\n<Br><Br>\n<br><b>ROLE</b><br>\nThis DeviceServer is used in order to manage the archiving of exported Tango attributes device.\n<Br><Br>\n<br><b>ARCHIVING TYPE</b><br>\nThere is two kind of archiving :\n<Br><Br>\n<Li><b>The His");

		//	Call database and and values
		//--------------------------------------------
		get_db_class().put_property(data);
	}

}
