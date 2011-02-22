//+======================================================================
// $Source$
//
// Project:   	Tango Device Server
//
// Description:	java source code for the TdbExtractor class .
//              This class is a singleton class and implements everything
//              which exists only once for all the  TdbExtractor object
//              It inherits from the DeviceClass class.
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.2  2008/04/08 11:34:58  pierrejoseph
// Génération automatique de la version des devices.
//
// Revision 1.1  2008/02/28 15:37:14  pierrejoseph
// TdbExtractor has been forgotten
//
// Revision 1.14  2007/05/11 13:58:54  pierrejoseph
// Attribute addition : release version
//
// Revision 1.13  2007/03/16 08:44:14  ounsy
// added a GetMinTime command
//
// Revision 1.12  2007/03/02 08:46:03  ounsy
// added the GetMaxTime command
//
// Revision 1.11  2007/03/01 17:59:00  pierrejoseph
// The new  input args date format is YYYY-MM-DD
//
// Revision 1.10  2007/03/01 10:08:01  ounsy
// added the RemoveDynamicAttributes command
//
// Revision 1.9  2006/09/07 13:48:30  ounsy
// added extraction with sampling methods
//
// Revision 1.8  2006/02/15 11:12:20  chinkumo
// Javadoc comments updated.
//
// Revision 1.7  2006/01/27 13:07:20  ounsy
// organised imports
//
// Revision 1.6  2005/11/29 17:32:48  chinkumo
// no message
//
// Revision 1.5.10.4  2005/11/29 16:13:31  chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.5.10.3  2005/11/15 13:45:16  chinkumo
// ...
//
// Revision 1.5.10.2  2005/09/26 07:58:42  chinkumo
// Every commands of shape 'getAttDataXXXXCount(...)' was changed. The type of their returned object was changed from 'short' to 'long'.
//
// Revision 1.5.10.1  2005/09/09 10:34:41  chinkumo
// Since the extraction politic changed to 'dynamic attributes', the device was pogo-regenerated.
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

package TdbExtractor;

import java.util.ResourceBundle;
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

public class TdbExtractorClass extends DeviceClass implements TangoConst {
	/**
	 * TdbExtractorClass class instance (it is a singleton).
	 */
	private static TdbExtractorClass _instance = null;

	/**
	 * Class properties array.
	 */
	private DbDatum[] cl_prop = null;

	// --------- Start of properties data members ----------

	// --------- End of properties data members ----------

	// ===================================================================
	//
	// method : instance()
	// 
	// description : static method to retrieve the TdbExtractorClass object
	// once it has been initialised
	//
	// ===================================================================
	public static TdbExtractorClass instance() {
		if (_instance == null) {
			System.err.println("TdbExtractorClass is not initialised !!!");
			System.err.println("Exiting");
			System.exit(-1);
		}
		return _instance;
	}

	// ===================================================================
	//
	// method : Init()
	// 
	// description : static method to create/retrieve the TdbExtractorClass
	// object. This method is the only one which enables a
	// user to create the object
	//
	// in : - class_name : The class name
	//
	// ===================================================================
	public static TdbExtractorClass init(String class_name) throws DevFailed {
		if (_instance == null) {
			_instance = new TdbExtractorClass(class_name);
		}
		return _instance;
	}

	// ===================================================================
	//
	// method : TdbExtractorClass()
	// 
	// description : constructor for the TdbExtractorClass class
	//
	// argument : in : - name : The class name
	//
	// ===================================================================
	protected TdbExtractorClass(String name) throws DevFailed {
		super(name);

		Util.out2.println("Entering TdbExtractorClass constructor");
		write_class_property();
		get_class_property();

		Util.out2.println("Leaving TdbExtractorClass constructor");
	}

	// ===================================================================
	//
	// method : command_factory()
	// 
	// description : Create the command object(s) and store them in the
	// command list
	// ===================================================================
	public void command_factory() {
		command_list.addElement(new GetMinTimeClass("GetMinTime",
				Tango_DEV_STRING, Tango_DEV_STRING, "The attribute to search",
				"The earliest value's timestamp", DispLevel.OPERATOR));
		command_list.addElement(new GetMaxTimeClass("GetMaxTime",
				Tango_DEV_STRING, Tango_DEV_STRING, "The attribute to search",
				"The latest value's timestamp", DispLevel.OPERATOR));
		command_list.addElement(new GetInfoClass("GetInfo", Tango_DEV_VOID,
				Tango_DEV_STRING, "",
				"The informations that characterize the database",
				DispLevel.OPERATOR));
		command_list.addElement(new GetHostClass("GetHost", Tango_DEV_VOID,
				Tango_DEV_STRING, "",
				"The connected database host identifier.", DispLevel.OPERATOR));
		command_list.addElement(new GetUserClass("GetUser", Tango_DEV_VOID,
				Tango_DEV_STRING, "",
				"The current user's name used for the connection.",
				DispLevel.OPERATOR));
		command_list.addElement(new GetConnectionStateClass(
				"GetConnectionState", Tango_DEV_VOID, Tango_DEV_BOOLEAN, "",
				"The connection state", DispLevel.OPERATOR));
		command_list.addElement(new GetAttDefinitionDataClass(
				"GetAttDefinitionData", Tango_DEV_STRING,
				Tango_DEVVAR_STRINGARRAY, "The attribute's name",
				"Differents definition informations for the given attribute",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttPropertiesDataClass(
						"GetAttPropertiesData",
						Tango_DEV_STRING,
						Tango_DEVVAR_STRINGARRAY,
						"The attribute's name",
						"An array containing the differents properties for the given attribute",
						DispLevel.OPERATOR));
		command_list.addElement(new GetAttIdClass("GetAttId", Tango_DEV_STRING,
				Tango_DEV_SHORT, "The attribute's name",
				"The TDB's ID that characterize the given attribute",
				DispLevel.OPERATOR));
		command_list.addElement(new GetAttNameAllClass("GetAttNameAll",
				Tango_DEV_VOID, Tango_DEVVAR_STRINGARRAY, "",
				"The whole list of the attributes registered in TDB",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttNameFacilityClass(
						"GetAttNameFacility",
						Tango_DEV_VOID,
						Tango_DEVVAR_STRINGARRAY,
						"",
						"The whole list of the attributes registered in TDB, and that belong to the current facility.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttNameFilterFormatClass(
						"GetAttNameFilterFormat",
						Tango_DEV_SHORT,
						Tango_DEVVAR_STRINGARRAY,
						"A format [0 -> scalar - 1 -> spectrum - 2 -> image]",
						"The filtered list of attributes registered in TDB.  The filtering is made according to the given format [0 -> scalar - 1 -> spectrum - 2 -> image]",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttNameFilterTypeClass(
						"GetAttNameFilterType",
						Tango_DEV_SHORT,
						Tango_DEVVAR_STRINGARRAY,
						"A type [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 -> Tango::DevDouble and 8 -> Tango::DevString]",
						"The filtered list of attributes registered in TDB.  The filtering is made according to the given type [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 -> Tango::DevDouble and 8 -> Tango::DevString]",
						DispLevel.OPERATOR));
		command_list.addElement(new GetAttCountAllClass("GetAttCountAll",
				Tango_DEV_VOID, Tango_DEV_SHORT, "",
				"The total number of attributes defined in TDB",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttCountFilterFormatClass(
						"GetAttCountFilterFormat",
						Tango_DEV_SHORT,
						Tango_DEV_SHORT,
						"A format [0 -> scalar - 1 -> spectrum - 2 -> image]",
						"The total number of attributes defined in TDB with the given format [0 -> scalar - 1 -> spectrum - 2 -> image]",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttCountFilterTypeClass(
						"GetAttCountFilterType",
						Tango_DEV_SHORT,
						Tango_DEV_SHORT,
						"A type [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 -> Tango::DevDouble and 8 -> Tango::DevString]",
						"The total number of attributes defined in TDB with the given type [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 -> Tango::DevDouble and 8 -> Tango::DevString]",
						DispLevel.OPERATOR));
		command_list.addElement(new GetDomainsClass("GetDomains",
				Tango_DEV_VOID, Tango_DEVVAR_STRINGARRAY, "",
				"The registered domains", DispLevel.OPERATOR));
		command_list.addElement(new GetDomainsCountClass("GetDomainsCount",
				Tango_DEV_VOID, Tango_DEV_SHORT, "",
				"The number of distinct registered domains.",
				DispLevel.OPERATOR));
		command_list.addElement(new GetFamiliesClass("GetFamilies",
				Tango_DEV_VOID, Tango_DEVVAR_STRINGARRAY, "",
				"The registered families", DispLevel.OPERATOR));
		command_list.addElement(new GetFamiliesCountClass("GetFamiliesCount",
				Tango_DEV_VOID, Tango_DEV_SHORT, "",
				"The number of distinct registered families.",
				DispLevel.OPERATOR));
		command_list.addElement(new GetFamiliesByDomainClass(
				"GetFamiliesByDomain", Tango_DEV_STRING,
				Tango_DEVVAR_STRINGARRAY, "The given domain",
				"The registered families for the given domain",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetFamiliesByDomainCountClass(
						"GetFamiliesByDomainCount",
						Tango_DEV_STRING,
						Tango_DEV_SHORT,
						"A domain name",
						"The number of distinct registered families for a given domain.",
						DispLevel.OPERATOR));
		command_list.addElement(new GetMembersClass("GetMembers",
				Tango_DEV_VOID, Tango_DEVVAR_STRINGARRAY, "",
				"The registered members", DispLevel.OPERATOR));
		command_list.addElement(new GetMembersCountClass("GetMembersCount",
				Tango_DEV_VOID, Tango_DEV_SHORT, "",
				"The number of distinct members.", DispLevel.OPERATOR));
		command_list.addElement(new GetMembersByDomainFamilyClass(
				"GetMembersByDomainFamily", Tango_DEVVAR_STRINGARRAY,
				Tango_DEVVAR_STRINGARRAY, "The given domain and family",
				"The registered members for the given domain and family",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetMembersByDomainFamilyCountClass(
						"GetMembersByDomainFamilyCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_SHORT,
						"A domain name, a family name",
						"The number of distinct registered members for the given domain and family.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttributesByDomainFamilyMembersCountClass(
						"GetAttributesByDomainFamilyMembersCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_SHORT,
						"A domain name, a family name, a member name.",
						"The number of registered the attributes for a given  domain, family, member.",
						DispLevel.OPERATOR));
		command_list.addElement(new GetCurrentArchivedAttClass(
				"GetCurrentArchivedAtt", Tango_DEV_VOID,
				Tango_DEVVAR_STRINGARRAY, "",
				"The list of attributes that are being archived",
				DispLevel.OPERATOR));
		command_list.addElement(new IsArchivedClass("IsArchived",
				Tango_DEV_STRING, Tango_DEV_BOOLEAN, "The attribute's name",
				"true if the given attribute is being archived",
				DispLevel.OPERATOR));
		command_list.addElement(new GetArchivingModeClass("GetArchivingMode",
				Tango_DEV_STRING, Tango_DEVVAR_STRINGARRAY,
				"The attribute's name",
				"The archiving mode used for the specified attribute",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataClass(
						"GetAttData",
						Tango_DEV_STRING,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list.addElement(new GetAttDataCountClass("GetAttDataCount",
				Tango_DEV_STRING, Tango_DEV_LONG, "An attribute name.",
				"The number of the data archieved for an attribute.",
				DispLevel.OPERATOR));
		command_list.addElement(new GetAttDataAvgClass("GetAttDataAvg",
				Tango_DEV_STRING, Tango_DEV_DOUBLE, "The attribute's name",
				"The average of the values generated by the attribute",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataAvgBetweenDatesClass(
						"GetAttDataAvgBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_DOUBLE,
						"The attribute's name, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"The average value generated by the given attribute and between the two given dates. ",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataBetweenDatesClass(
						"GetAttDataBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataBetweenDatesSamplingClass(
						"GetAttDataBetweenDatesSampling",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS), and the sampling type (ALL, SECOND, MINUTE, HOUR, DAY)",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataBetweenDatesCountClass(
						"GetAttDataBetweenDatesCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the beginning (YYYY-MM-DD HH24:MI:SS) date and the ending date (YYYY-MM-DD HH24:MI:SS).",
						"The number of data beetwen two dates and, for a given scalar attribute.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfOrSupThanClass(
						"GetAttDataInfOrSupThan",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the lower limit and the upper limit",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfOrSupThanCountClass(
						"GetAttDataInfOrSupThanCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the lower limit and the upper limit",
						"The number of scalar data lower than the given value x OR higher than the given value y.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfOrSupThanBetweenDatesClass(
						"GetAttDataInfOrSupThanBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the lower limit, the upper limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfOrSupThanBetweenDatesCountClass(
						"GetAttDataInfOrSupThanBetweenDatesCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the lower limit, the upper limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS).",
						"The number of scalar data lower than the given value x OR higher than the given value y, beetwen two dates and for the specified attribute.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfThanClass(
						"GetAttDataInfThan",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the upper limit",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfThanCountClass(
						"GetAttDataInfThanCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name and the  upper limit.",
						"The number of scalar data lower than the given value and for the specified attribute.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfThanBetweenDatesClass(
						"GetAttDataInfThanBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the upper limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataInfThanBetweenDatesCountClass(
						"GetAttDataInfThanBetweenDatesCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the upper limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS). ",
						"The number data lower than the given value x, and beetwen two dates (date_1 & date_2).",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataLastNClass(
						"GetAttDataLastN",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name and the number of wished data",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list.addElement(new GetAttDataMaxClass("GetAttDataMax",
				Tango_DEV_STRING, Tango_DEV_DOUBLE, "The attribute's name",
				"The biggest value generated by the attribute",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataMaxBetweenDatesClass(
						"GetAttDataMaxBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_DOUBLE,
						"The attribute's name, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"The biggest value generated between the two given dates.",
						DispLevel.OPERATOR));
		command_list.addElement(new GetAttDataMinClass("GetAttDataMin",
				Tango_DEV_STRING, Tango_DEV_DOUBLE, "The attribute's name",
				"The smallest value generated by the attribute",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataMinBetweenDatesClass(
						"GetAttDataMinBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_DOUBLE,
						"The attribute's name, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"The smallest scalar value generated by the given attribute and between the two given dates.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupThanClass(
						"GetAttDataSupThan",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name and the  lower limit",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list.addElement(new GetAttDataSupThanCountClass(
				"GetAttDataSupThanCount", Tango_DEVVAR_STRINGARRAY,
				Tango_DEV_LONG, "The attribute's name and the  lower limit.",
				"The number of data higher than the given value.",
				DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupAndInfThanClass(
						"GetAttDataSupAndInfThan",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the lower limit and the upper limit",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupAndInfThanCountClass(
						"GetAttDataSupAndInfThanCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the lower limit and the upper limit",
						"The data that are highter than the given value x AND lower than the given value y.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupAndInfThanBetweenDatesClass(
						"GetAttDataSupAndInfThanBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the lower limit, the upper limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupAndInfThanBetweenDatesCountClass(
						"GetAttDataSupAndInfThanBetweenDatesCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the lower limit, the upper limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS).",
						"The number of data higher than the given value x, (AND) lower than the given value y, and beetwen two dates (date_1 & date_2).",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupThanBetweenDatesClass(
						"GetAttDataSupThanBetweenDates",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEVVAR_LONGSTRINGARRAY,
						"The attribute's name, the lower limit, the beginning date (YYYY-MM-DD HH24:MI:SS) and the ending date (YYYY-MM-DD HH24:MI:SS)",
						"String  : The new created dynamic attribute name, Long : the number of data.",
						DispLevel.OPERATOR));
		command_list
				.addElement(new GetAttDataSupThanBetweenDatesCountClass(
						"GetAttDataSupThanBetweenDatesCount",
						Tango_DEVVAR_STRINGARRAY,
						Tango_DEV_LONG,
						"The attribute's name, the lower limit, the beginning date  (YYYY-MM-DD HH24:MI:SS) and the ending date  (YYYY-MM-DD HH24:MI:SS).",
						"The number of data higher than the given value y, and beetwen two dates (date_1 & date_2).",
						DispLevel.OPERATOR));
		command_list.addElement(new RemoveDynamicAttributeClass(
				"RemoveDynamicAttribute", Tango_DEV_STRING, Tango_DEV_VOID,
				"The TDBExtractor dynamic attribute's name", "",
				DispLevel.OPERATOR));
		command_list.addElement(new RemoveDynamicAttributesClass(
				"RemoveDynamicAttributes", Tango_DEV_VOID, Tango_DEV_VOID, "",
				"", DispLevel.OPERATOR));

		// add polling if any
		for (int i = 0; i < command_list.size(); i++) {
			Command cmd = (Command) command_list.elementAt(i);
		}
	}

	// =============================================================================
	//
	// Method: attribute_factory(Vector att_list)
	//
	// =============================================================================
	public void attribute_factory(Vector att_list) throws DevFailed {
		// Attribute : version
		Attr version = new Attr("version", Tango_DEV_STRING, AttrWriteType.READ);
		att_list.addElement(version);
	}

	// ===================================================================
	//
	// method : device_factory()
	// 
	// description : Create the device object(s) and store them in the
	// device list
	//
	// argument : in : String[] devlist : The device name list
	//
	// ===================================================================
	public void device_factory(String[] devlist) throws DevFailed {
		String device_version = ResourceBundle.getBundle("application")
				.getString("project.version");

		for (int i = 0; i < devlist.length; i++) {
			// Util.out4.println("Device name : " + devlist[ i ]);

			// Create device and add it into the device list
			// ----------------------------------------------
			device_list.addElement(new TdbExtractor(this, devlist[i],
					device_version));

			// Export device to the outside world
			// ----------------------------------------------
			if (Util._UseDb == true)
				export_device(((DeviceImpl) (device_list.elementAt(i))));
			else
				export_device(((DeviceImpl) (device_list.elementAt(i))),
						devlist[i]);
		}
	}

	// ===================================================================
	/**
	 * Get the class property for specified name.
	 * 
	 * @param name
	 *            The property name.
	 */
	// ===================================================================
	public DbDatum get_class_property(String name) {
		for (int i = 0; i < cl_prop.length; i++)
			if (cl_prop[i].name.equals(name))
				return cl_prop[i];
		// if not found, return an empty DbDatum
		return new DbDatum(name);
	}

	// ===================================================================
	/**
	 * Read the class properties from database.
	 */
	// ===================================================================
	public void get_class_property() throws DevFailed {
		// Initialize your default values here.
		// ------------------------------------------

		// Read class properties from database.(Automatic code generation)
		// -------------------------------------------------------------
		if (Util._UseDb == false)
			return;
		String[] propnames = {};

		// Call database and extract values
		// --------------------------------------------
		cl_prop = get_db_class().get_property(propnames);
		int i = -1;

		// End of Automatic code generation
		// -------------------------------------------------------------

	}

	// ===================================================================
	/**
	 * Set class description as property in database
	 */
	// ===================================================================
	private void write_class_property() throws DevFailed {
		// First time, check if database used
		// --------------------------------------------
		if (Util._UseDb == false)
			return;

		// Prepeare DbDatum
		// --------------------------------------------
		DbDatum[] data = new DbDatum[2];
		data[0] = new DbDatum("ProjectTitle");
		data[0].insert("Tango Device Server");

		data[1] = new DbDatum("Description");
		data[1].insert("A DServer used for temporary database's extractions.");

		// Call database and and values
		// --------------------------------------------
		get_db_class().put_property(data);
	}

}
