// +============================================================================
// $Source:
// /cvsroot/tango-cs/archiving/server/hdbtdbArchivingServers/src/main/java/TdbExtractor/TdbExtractor.java,v
// $
//
// project : Tango Device Server
//
// Description: java source code for the TdbExtractor class and its commands.
// This class is derived from DeviceImpl class.
// It represents the CORBA servant obbject which
// will be accessed from the network. All commands which
// can be executed on the TdbExtractor are implemented
// in this file.
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.16  2010/11/19 16:17:40  abeilleg
// remove critical of findbugs
//
// Revision 1.15  2010/08/17 09:50:10  pierrejoseph
// correction in the getArchivingMode
//
// Revision 1.14  2010/08/04 07:28:22  pierrejoseph
// delete_device method addition - must be implemented
//
// Revision 1.13  2010/08/03 11:55:59  pierrejoseph
// Remove not useful traces
//
// Revision 1.12  2010/01/21 14:56:42  extia-soleil
// AW (Extia) - Mantis 14438:
// - Added commands to get the full name of an attribute from its id, in HDB and TDB
//
// Revision 1.11 2009/12/17 12:47:15 pierrejoseph
// CheckStyle: Organize imports / Format
//
// Revision 1.10 2009/09/04 13:13:49 soleilarc
// fix java 6 bug: remove singleton from constructor
//
// Revision 1.9 2009/08/10 14:04:43 soleilarc
// Oracle RAC connection
//
// Revision 1.8 2009/06/12 15:00:40 soleilarc
// Api: new architecture
//
// Revision 1.6 2008/08/01 13:16:22 soleilarc
// Bug: 9378: Extract data from file when the needed respecting the export
// period and the extract precision value.
//
// Revision 1.5 2008/07/11 07:49:12 soleilarc
// Solve Bug n�8827: remove Corba exception when getting data between an invalid
// dates.
//
// Revision 1.4 2008/05/07 16:37:39 pierrejoseph
// The tango state type is transformed in string type before the dynamic
// attribut creation because the state type.
//
// Revision 1.3 2008/04/08 11:34:58 pierrejoseph
// G�n�ration automatique de la version des devices.
//
// Revision 1.2 2008/03/21 16:46:16 pierrejoseph
// minor change
//
// Revision 1.1 2008/02/28 15:37:15 pierrejoseph
// TdbExtractor has been forgotten
//
// Revision 1.28 2007/05/11 13:58:54 pierrejoseph
// Attribute addition : release version
//
// Revision 1.27 2007/03/16 14:09:35 ounsy
// minor changes
//
// Revision 1.26 2007/03/16 08:44:14 ounsy
// added a GetMinTime command
//
// Revision 1.25 2007/03/05 16:25:20 ounsy
// non-static DataBase
//
// Revision 1.24 2007/03/02 08:46:02 ounsy
// added the GetMaxTime command
//
// Revision 1.23 2007/03/01 10:08:01 ounsy
// added the RemoveDynamicAttributes command
//
// Revision 1.22 2007/02/26 16:14:24 ounsy
// archiving devices now inherits just from DeviceImpl instead of
// DeviceImplWithShutdownRunnable (they're nonlonger unexported onn shutdown)
//
// Revision 1.21 2007/02/08 08:44:15 pierrejoseph
// The method getAttData is no more available (costly).
//
// Revision 1.20 2007/02/05 17:04:52 ounsy
// corrected a bug for spectrum attributes with an empty value
//
// Revision 1.19 2006/12/06 10:17:06 ounsy
// minor changes
//
// Revision 1.18 2006/11/30 15:30:15 ounsy
// corrected a bug in add_attribute() when the extracted DbData is empty; the
// dynamic attribute is no longer created, and a DevFailed is recieved instead
//
// Revision 1.17 2006/11/20 09:24:49 ounsy
// minor changes
//
// Revision 1.16 2006/11/13 15:57:37 ounsy
// all java devices now inherit from UnexportOnShutdownDeviceImpl instead of
// from DeviceImpl
//
// Revision 1.15 2006/10/31 16:54:12 ounsy
// milliseconds and null values management
//
// Revision 1.14 2006/10/09 12:56:28 chinkumo
// A specific exception is raised when an outOfMemory error appeared, for the
// methods who generate dynamics attributes.
//
// Revision 1.13 2006/09/07 13:48:30 ounsy
// added extraction with sampling methods
//
// Revision 1.12 2006/09/05 12:25:16 ounsy
// updated for sampling compatibility
//
// Revision 1.11 2006/07/24 09:55:22 ounsy
// now uses buffered attribute ids
//
// Revision 1.10 2006/02/15 13:11:44 ounsy
// organized imports
//
// Revision 1.9 2006/02/07 11:57:49 ounsy
// small changes in logs
//
// Revision 1.8 2006/01/27 13:07:20 ounsy
// organised imports
//
// Revision 1.7 2005/11/29 17:32:48 chinkumo
// no message
//
// Revision 1.6.10.4 2005/11/29 16:13:31 chinkumo
// Code reformated (pogo compatible)
//
// Revision 1.6.10.3 2005/11/15 13:45:16 chinkumo
// ...
//
// Revision 1.6.10.2 2005/09/26 07:58:42 chinkumo
// Every commands of shape 'getAttDataXXXXCount(...)' was changed. The type of
// their returned object was changed from 'short' to 'long'.
//
// Revision 1.6.10.1 2005/09/09 10:37:47 chinkumo
// Since the extraction politic changed to 'dynamic attributes', the device was
// pogo-regenerated.
//
//
// copyleft : European Synchrotron Radiation Facility
// BP 220, Grenoble 38043
// FRANCE
//
// -============================================================================
//
// This file is generated by POGO
// (Program Obviously used to Generate tango Object)
//
// (c) - Software Engineering Group - ESRF
// =============================================================================

package TdbExtractor;

import java.sql.Connection;
import java.sql.Timestamp;
import java.util.StringTokenizer;

import org.omg.CORBA.SystemException;
import org.omg.CORBA.UserException;

import TdbExtractor.Proxy.DbProxy;
import fr.esrf.Tango.AttrDataFormat;
import fr.esrf.Tango.AttrWriteType;
import fr.esrf.Tango.DevError;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.DevState;
import fr.esrf.Tango.DevVarLongStringArray;
import fr.esrf.Tango.ErrSeverity;
import fr.esrf.TangoApi.DbDatum;
import fr.esrf.TangoDs.Attr;
import fr.esrf.TangoDs.Attribute;
import fr.esrf.TangoDs.DeviceClass;
import fr.esrf.TangoDs.DeviceImpl;
import fr.esrf.TangoDs.Except;
import fr.esrf.TangoDs.ImageAttr;
import fr.esrf.TangoDs.SpectrumAttr;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.DbData;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.ConfigConst;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseManager;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.TDBDataBaseManager;
import fr.soleil.hdbtdbArchivingApi.ArchivingApi.DataBaseUtils.DbUtils;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Mode.Mode;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ArchivingException;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.SamplingType;

/**
 * Class Description: A DServer used for temporary database's extractions.
 * 
 * @author $Author$
 * @version $Revision$
 */

// --------- Start of States Description ----------
/*
 * Device States Description:
 */
// --------- End of States Description ----------

public class TdbExtractor extends DeviceImpl
/* WithShutdownRunnable */implements TangoConst {

    private int state;
    private DbProxy dbProxy;
    private final String m_version;

    // --------- Start of attributes data members ----------

    // --------- End of attributes data members ----------

    // --------- Start of properties data members ----------
    /**
     * Computer identifier on wich is settled the database HDB. The identifier
     * can be the computer name or its IP address. <br>
     * <b>Default value : </b> Class value
     */
    private String dbHost;
    /**
     * Database name.<br>
     * <b>Default value : </b> hdb
     */
    private String dbName;

    /**
     * User identifier (name) used to connect the database.<br>
     * <b>Default value : </b> tdb
     */
    private String dbUser;
    /**
     * Password used to connect the database.<br>
     * <b>Default value : </b> tdb
     */
    private String dbPassword;
    /**
     * true if the ORACLE RAC connection is activated. This information is
     * appended to all device's (or attributes) name. false otherwise.<br>
     * <b>Default value : </b> false
     */
    private boolean RacConnection;

    // --------- End of properties data members ----------

    int id = 0;

    public static final String HintOnMemoryError = "Hint: suppress some dynamics attributs before executing this command.";

    // --------------------------------------

    // =========================================================
    /**
     * Constructor for simulated Time Device Server.
     * 
     * @param cl
     *            The DeviceClass object
     * @param s
     *            The Device name.
     * @param version
     *            The device version
     */
    // =========================================================
    TdbExtractor(final DeviceClass cl, final String s, final String version) throws DevFailed {
	super(cl, s);
	m_version = version;
	init_device();
    }

    // =========================================================
    /**
     * Constructor for simulated Time Device Server.
     * 
     * @param cl
     *            The DeviceClass object
     * @param s
     *            The Device name.
     * @param d
     *            Device description.
     * @param version
     *            The device version
     */
    // =========================================================
    TdbExtractor(final DeviceClass cl, final String s, final String d, final String version)
	    throws DevFailed {
	super(cl, s, d);
	m_version = version;
	init_device();
    }

    // =========================================================
    /**
     * Initialize the device.
     */
    // =========================================================
    @Override
    public void init_device() throws DevFailed {
	System.out.println("TdbExtractor() create " + device_name);

	// Initialise variables to default values
	// -------------------------------------------
	get_device_property();

	get_logger().info("dbHost = " + dbHost);
	get_logger().info("dbName = " + dbName);
	get_logger().info("dbUser = " + dbUser);
	get_logger().info("dbPassword = " + dbPassword);
	get_logger().info("Rac Connection  = " + RacConnection);

	dbProxy = new DbProxy(dbHost, dbName, dbUser, dbPassword, RacConnection);
	if (!dbProxy.is_db_connected()) {
	    set_state(DevState.FAULT);
	    set_status(device_name + " : DevState.FAULT" + "\r\n"
		    + "Temporary database connection : " + "FAULT" + " (may be broken...)" + "\r\n");
	    get_logger().error("ERROR : Database unconnected !!");
	} else {
	    set_state(DevState.ON);
	    set_status(device_name + " : " + "DevState.ON" + "\r\n"
		    + "Temporary database connection : " + "OK" + "\r\n");
	}
	get_logger().info("Exiting init_device()");
    }

    // ===================================================================
    /**
     * Read the device properties from database.
     */
    // ===================================================================
    public void get_device_property() throws DevFailed {
	// Initialize your default values here.
	// ------------------------------------------
	dbUser = ConfigConst.TDB_BROWSER_USER;
	dbPassword = ConfigConst.TDB_BROWSER_PASSWORD;

	// Read device properties from database.(Automatic code generation)
	// -------------------------------------------------------------
	if (Util._UseDb == false) {
	    return;
	}
	final String[] propnames = { "DbHost", "DbName", "DbUser", "DbPassword", "RacConnection" };

	// Call database and extract values
	// --------------------------------------------
	final DbDatum[] dev_prop = get_db_device().get_property(propnames);
	final TdbExtractorClass ds_class = (TdbExtractorClass) get_device_class();
	int i = -1;
	// Extract DbHost value
	if (dev_prop[++i].is_empty() == false) {
	    dbHost = dev_prop[i].extractString();
	} else {
	    // Try to get value from class property
	    final DbDatum cl_prop = ds_class.get_class_property(dev_prop[i].name);
	    if (cl_prop.is_empty() == false) {
		dbHost = cl_prop.extractString();
	    }
	}

	// Extract DbName value
	if (dev_prop[++i].is_empty() == false) {
	    dbName = dev_prop[i].extractString();
	} else {
	    // Try to get value from class property
	    final DbDatum cl_prop = ds_class.get_class_property(dev_prop[i].name);
	    if (cl_prop.is_empty() == false) {
		dbName = cl_prop.extractString();
	    }
	}

	// Extract DbUser value
	if (dev_prop[++i].is_empty() == false) {
	    dbUser = dev_prop[i].extractString();
	} else {
	    // Try to get value from class property
	    final DbDatum cl_prop = ds_class.get_class_property(dev_prop[i].name);
	    if (cl_prop.is_empty() == false) {
		dbUser = cl_prop.extractString();
	    }
	}

	// Extract DbPassword value
	if (dev_prop[++i].is_empty() == false) {
	    dbPassword = dev_prop[i].extractString();
	} else {
	    // Try to get value from class property
	    final DbDatum cl_prop = ds_class.get_class_property(dev_prop[i].name);
	    if (cl_prop.is_empty() == false) {
		dbPassword = cl_prop.extractString();
	    }
	}
	// Extract RacConnection value
	if (dev_prop[++i].is_empty() == false) {
	    RacConnection = dev_prop[i].extractBoolean();
	} else {
	    // Try to get value from class property
	    final DbDatum cl_prop = ds_class.get_class_property(dev_prop[i].name);
	    if (cl_prop.is_empty() == false) {
		RacConnection = cl_prop.extractBoolean();
	    }
	}

	// End of Automatic code generation
	// -------------------------------------------------------------

    }

    // =========================================================
    /**
     * Method always executed before command execution.
     */
    // =========================================================
    @Override
    public void always_executed_hook() {
	// get_logger().info("In always_executed_hook method()");
    }

    // =========================================================
    /**
     * Execute command "GetInfo" on device. Returns misc informations about the
     * database and a set of parameters characterizing the connection.
     * 
     * @return The informations that characterize the database
     */
    // =========================================================
    public String get_info() throws DevFailed {
	String argout = null;

	get_logger().info("Entering get_info()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getDbConn().getInfo();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_info()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetHost" on device. Returns the connected database host
     * identifier.
     * 
     * @return The connected database host identifier.
     * @throws ArchivingException
     */
    // =========================================================
    public String get_host() throws DevFailed {

	get_logger().info("Entering get_host()");

	// ---Add your Own code to control device here ---
	String argout = "";
	try {
	    argout = dbProxy.getDataBase().getDbConn().getHost();
	} catch (final ArchivingException e) {
	    Except.throw_exception("ArchivingException", "Cannot get host", "get_host");
	}
	get_logger().info("Exiting get_host()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetUser" on device. Gets the current user's name used
     * for the connection.
     * 
     * @return The current user's name used for the connection.
     * @throws ArchivingException
     */
    // =========================================================
    public String get_user() throws DevFailed, ArchivingException {

	get_logger().info("Entering get_user()");

	// ---Add your Own code to control device here ---
	final String argout = dbProxy.getDataBase().getDbConn().getUser();
	get_logger().info("Exiting get_user()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetConnectionState" on device. Cheks if the connection
     * to the temporary database is alive.
     * 
     * @return The connection state
     */
    // =========================================================
    public boolean get_connection_state() throws DevFailed {
	boolean argout = false;

	get_logger().info("Entering get_connection_state()");

	// ---Add your Own code to control device here ---
	Connection connection = null;
	try {
	    connection = dbProxy.getDataBase().getDbConn().getConnection();
	    if (connection != null) {
		argout = true;
		dbProxy.getDataBase().getDbConn().closeConnection(connection);
	    }
	} catch (final ArchivingException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}

	get_logger().info("Exiting get_connection_state()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDefinitionData" on device. Returns an array
     * containing the differents definition informations for the given
     * attribute.
     * 
     * @param argin
     *            The attribute's name
     * @return Differents definition informations for the given attribute
     */
    // =========================================================
    public String[] get_att_definition_data(final String argin) throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_att_definition_data()");

	// ---Add your Own code to control device here ---
	try {
	    argout = DbUtils.toStringArray(dbProxy.getDataBase().getAttribute()
		    .getAttDefinitionData(argin));
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_definition_data()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttPropertiesData" on device. Gets the differents
     * properties informations for the given attribute
     * 
     * @param argin
     *            The attribute's name
     * @return An array containing the differents properties for the given
     *         attribute
     */
    // =========================================================
    public String[] get_att_properties_data(final String argin) throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_att_properties_data()");

	// ---Add your Own code to control device here ---
	try {
	    argout = DbUtils.toStringArray(dbProxy.getDataBase().getAttribute().getProperties()
		    .getAttPropertiesData(argin));
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_properties_data()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttFullName" on device. Gets for a specified
     * attribute id its full name as defined in TDB
     * 
     * @param argin
     *            The attribute's id
     * @return The TDB's full name that characterize the given attribute
     */
    // =========================================================
    public String get_att_full_name(final short argin) throws DevFailed {
	String argout;

	get_logger().info("Entering get_att_full_name()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getAttribute().getNames().getAttFullName(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_full_name()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttId" on device. Gets for a specified attribute its
     * ID as defined in TDB
     * 
     * @param argin
     *            The attribute's name
     * @return The TDB's ID that characterize the given attribute
     */
    // =========================================================
    public short get_att_id(final String argin) throws DevFailed {
	short argout;

	get_logger().info("Entering get_att_id()");

	// ---Add your Own code to control device here ---
	try {
	    // argout = ( short ) this.dbProxy.getDataBase().getAttID(argin);
	    argout = (short) dbProxy.getDataBase().getAttribute().getIds().getBufferedAttID(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_id()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttNameAll" on device. Gets whole list of the
     * attributes registered in TDB.
     * 
     * @return The whole list of the attributes registered in TDB.
     */
    // =========================================================
    public String[] get_att_name_all() throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_att_name_all()");

	// ---Add your Own code to control device here ---
	try {
	    argout = DbUtils.toStringArray(dbProxy.getDataBase().getAttribute().getNames()
		    .getAttributes());
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_name_all()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttNameFacility" on device. Gets whole list of the
     * attributes registered in TDB and that belong to the current facility.
     * 
     * @return The whole list of the attributes registered in TDB, and that
     *         belong to the current facility.
     */
    // =========================================================
    public String[] get_att_name_facility() throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_att_name_facility()");

	// ---Add your Own code to control device here ---
	try {
	    argout = DbUtils.toStringArray(dbProxy.getDataBase().getAttribute().getNames()
		    .getAttributes(System.getProperty("TANGO_HOST")));
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_name_facility()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttNameFilterFormat" on device. Gets the list of
     * <I>TDB</I> registered attributes for the given format
     * 
     * @param argin
     *            A format [0 -> scalar - 1 -> spectrum - 2 -> image]
     * @return The filtered list of attributes registered in TDB. The filtering
     *         is made according to the given format [0 -> scalar - 1 ->
     *         spectrum - 2 -> image]
     */
    // =========================================================
    public String[] get_att_name_filter_format(final short argin) throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_att_name_filter_format()");

	// ---Add your Own code to control device here ---
	try {
	    argout = DbUtils.toStringArray(dbProxy.getDataBase().getAttribute().getNames()
		    .getAttributesNamesF(argin));
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_name_filter_format()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttNameFilterType" on device. Gets the list of
     * <I>TDB</I> registered attributes for the given type
     * 
     * @param argin
     *            A type [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 ->
     *            Tango::DevDouble and 8 -> Tango::DevString]
     * @return The filtered list of attributes registered in TDB. The filtering
     *         is made according to the given type [2 -> Tango::DevShort | 3 ->
     *         Tango::DevLong | 5 -> Tango::DevDouble and 8 -> Tango::DevString]
     */
    // =========================================================
    public String[] get_att_name_filter_type(final short argin) throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_att_name_filter_type()");

	// ---Add your Own code to control device here ---
	try {
	    argout = DbUtils.toStringArray(dbProxy.getDataBase().getAttribute().getNames()
		    .getAttributesNamesT(argin));
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_name_filter_type()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttCountAll" on device. Gets the total number of
     * attributes defined in TDB.
     * 
     * @return The total number of attributes defined in TDB
     */
    // =========================================================
    public short get_att_count_all() throws DevFailed {
	short argout;

	get_logger().info("Entering get_att_count_all()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getNames().getAttributesCount();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_count_all()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttCountFilterFormat" on device. Gets the total
     * number of attributes defined in TDB with the given format.
     * 
     * @param argin
     *            A format [0 -> scalar - 1 -> spectrum - 2 -> image]
     * @return The total number of attributes defined in TDB with the given
     *         format [0 -> scalar - 1 -> spectrum - 2 -> image]
     */
    // =========================================================
    public short get_att_count_filter_format(final short argin) throws DevFailed {
	short argout;

	get_logger().info("Entering get_att_count_filter_format()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getNames().getAttributesCountF(
		    argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_count_filter_format()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttCountFilterType" on device. Gets the total number
     * of attributes defined in TDB with the given type.
     * 
     * @param argin
     *            A type [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 ->
     *            Tango::DevDouble and 8 -> Tango::DevString]
     * @return The total number of attributes defined in TDB with the given type
     *         [2 -> Tango::DevShort | 3 -> Tango::DevLong | 5 ->
     *         Tango::DevDouble and 8 -> Tango::DevString]
     */
    // =========================================================
    public short get_att_count_filter_type(final short argin) throws DevFailed {
	short argout;

	get_logger().info("Entering get_att_count_filter_type()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getNames().getAttributesCountT(
		    argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_count_filter_type()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetDomains" on device. Gets all the registered domains.
     * 
     * @return The registered domains
     */
    // =========================================================
    public String[] get_domains() throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_domains()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getAttribute().getDomains().get_domains();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_domains()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetDomainsCount" on device. Returns the number of
     * distinct registered domains.
     * 
     * @return The number of distinct registered domains.
     */
    // =========================================================
    public short get_domains_count() throws DevFailed {
	short argout;

	get_logger().info("Entering get_domains_count()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getDomains().get_domainsCount();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_domains_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetFamilies" on device. Gets all the registered families
     * 
     * @return The registered families
     */
    // =========================================================
    public String[] get_families() throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_families()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getAttribute().getFamilies().get_families();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_families()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetFamiliesCount" on device. Returns the number of
     * distinct registered families.
     * 
     * @return The number of distinct registered families.
     */
    // =========================================================
    public short get_families_count() throws DevFailed {
	short argout;

	get_logger().info("Entering get_families_count()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getFamilies().get_familiesCount();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_families_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetFamiliesByDomain" on device. Gets all the registered
     * families for the given domain.
     * 
     * @param argin
     *            The given domain
     * @return The registered families for the given domain
     */
    // =========================================================
    public String[] get_families_by_domain(final String argin) throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_families_by_domain()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getAttribute().getFamilies().get_families(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_families_by_domain()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetFamiliesByDomainCount" on device. Returns the number
     * of distinct registered families for a given domain.
     * 
     * @param argin
     *            A domain name
     * @return The number of distinct registered families for a given domain.
     */
    // =========================================================
    public short get_families_by_domain_count(final String argin) throws DevFailed {
	short argout;

	get_logger().info("Entering get_families_by_domain_count()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getFamilies().get_familiesCount(
		    argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_families_by_domain_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetMembers" on device. Gets all the registered members
     * 
     * @return The registered members
     */
    // =========================================================
    public String[] get_members() throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_members()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getAttribute().getMembers().get_members();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_members()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetMembersCount" on device. Returns the number of
     * distinct members.
     * 
     * @return The number of distinct members.
     */
    // =========================================================
    public short get_members_count() throws DevFailed {
	short argout;

	get_logger().info("Entering get_members_count()");

	// ---Add your Own code to control device here ---
	try {
	    argout = (short) dbProxy.getDataBase().getAttribute().getMembers().get_membersCount();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_members_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetMembersByDomainFamily" on device. Gets all the
     * registered members for the given domain and family
     * 
     * @param argin
     *            The given domain and family
     * @return The registered members for the given domain and family
     */
    // =========================================================
    public String[] get_members_by_domain_family(final String[] argin) throws DevFailed {
	String[] argout = null;

	get_logger().info("Entering get_members_by_domain_family()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_members_by_domain_family");
	} else {
	    try {
		argout = dbProxy.getDataBase().getAttribute().getMembers().get_members(
			argin[0].trim(), argin[1].trim());
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_members_by_domain_family()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetMembersByDomainFamilyCount" on device. Returns the
     * number of distinct registered members for the given domain and family.
     * 
     * @param argin
     *            A domain name, a family name
     * @return The number of distinct registered members for the given domain
     *         and family.
     */
    // =========================================================
    public short get_members_by_domain_family_count(final String[] argin) throws DevFailed {
	short argout = 0;

	get_logger().info("Entering get_members_by_domain_family_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_members_by_domain_family_count");
	} else {
	    try {
		argout = (short) dbProxy.getDataBase().getAttribute().getMembers()
			.get_membersCount(argin[0], argin[1]);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_members_by_domain_family_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttributesByDomainFamilyMembersCount" on device.
     * Returns the number of registered the attributes for a given domain,
     * family, member.
     * 
     * @param argin
     *            A domain name, a family name, a member name.
     * @return The number of registered the attributes for a given domain,
     *         family, member.
     */
    // =========================================================
    public short get_attributes_by_domain_family_members_count(final String[] argin)
	    throws DevFailed {
	short argout = 0;

	get_logger().info("Entering get_attributes_by_domain_family_members_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_attributes_by_domain_family_members_count");
	} else {
	    try {
		argout = (short) dbProxy.getDataBase().getAttribute().getNames()
			.getAttributesCount(argin[0], argin[1], argin[2]);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_attributes_by_domain_family_members_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetCurrentArchivedAtt" on device. Gets the list of
     * attributes that are being archived in <I>TDB</I>
     * 
     * @return The list of attributes that are being archived
     */
    // =========================================================
    public String[] get_current_archived_att() throws DevFailed {
	String[] argout;

	get_logger().info("Entering get_current_archived_att()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getMode().getCurrentArchivedAtt();
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_current_archived_att()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "IsArchived" on device. Returns "true" if the attribute
     * of given name is currently archived, "false" otherwise.
     * 
     * @param argin
     *            The attribute's name
     * @return true if the given attribute is being archived
     */
    // =========================================================
    public boolean is_archived(final String argin) throws DevFailed {
	boolean argout = false;

	get_logger().info("Entering is_archived()");

	// ---Add your Own code to control device here ---
	try {
	    argout = dbProxy.getDataBase().getMode().isArchived(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting is_archived()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetArchivingMode" on device. Gets the archiving mode
     * used for the specified attribute.
     * 
     * @param argin
     *            The attribute's name
     * @return The archiving mode used for the specified attribute
     */
    // =========================================================
    public String[] get_archiving_mode(final String argin) throws DevFailed {
	String[] argout = null;

	// ---Add your Own code to control device here ---
	try {
	    final Mode mode = dbProxy.getDataBase().getMode().getCurrentArchivingMode(argin);
	    if (mode != null) {
		argout = mode.toArray();
	    }

	    if (argout == null || argout.length == 0) {
		throw new ArchivingException("Invalid attribute: " + argin, "Invalid attribute: "
			+ argin, ErrSeverity.WARN, "No database connection or \"" + argin
			+ "\" attribute not found in database", this.getClass().getName());
	    }
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}

	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttData" on device. Gets all the data archieved for
     * an attribute. Create a dynamic attribute, retrieve data from database and
     * prepare result for attribute_history call.
     * 
     * @param argin
     *            The attribute's name
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data(final String argin) throws DevFailed {
	Except.throw_exception("DEPRECATED", "This method is no more available.",
		"TdbExtractor.get_att_data");
	return null;

	/*
	 * DevVarLongStringArray argout = new DevVarLongStringArray();
	 * get_logger().info("Entering get_att_data()"); // ---Add your Own code
	 * to control device here --- DbData dbData = null; try {
	 * ArchivingManagerApi.ExportData2Tdb(argin); // Get data from db.
	 * dbData = this.dbProxy.getDataBase().getAttData(argin); // Buid an
	 * attribute and gets its references argout = add_attribute(dbData); }
	 * catch ( ArchivingException e ) { e.printStackTrace(); //throw new
	 * ArchivingException(GlobalConst.ARC_UNREACH_EXCEPTION);
	 * get_logger().info("cla ZZZZZZZZZZ!!!!!!!!!!!!!!!!!!"); throw
	 * e.toTangoException(); } catch (java.lang.OutOfMemoryError oome) {
	 * get_logger().info("OutOfMemoryError in get_att_data");
	 * Except.throw_exception("MEMORY_ERROR",HintOnMemoryError,
	 * "TdbExtractor.get_att_data"); } catch ( Throwable t ) {
	 * t.printStackTrace(); if (t instanceof DevFailed) { DevError [] errors
	 * = ( (DevFailed) t ).errors; if ( errors != null ) { for ( int i = 0 ;
	 * i < errors.length ; i ++ ) { DevError error = errors [ i ];
	 * get_logger().error (
	 * "Error: /desc/"+error.desc+"/origin/"+error.origin
	 * +"/reason/"+error.reason ); } } get_logger().error (
	 * "Error: /CAUSE---------------" ); if ( ( (DevFailed) t ).getCause()
	 * != null ) { ( (DevFailed) t ).getCause().printStackTrace (); } } }
	 * get_logger().info("Exiting get_att_data()"); return argout;
	 */
    }

    // =========================================================
    /**
     * Execute command "GetAttDataCount" on device. Returns the number of the
     * data archieved for an attribute.
     * 
     * @param argin
     *            An attribute name.
     * @return The number of the data archieved for an attribute.
     */
    // =========================================================
    public int get_att_data_count(final String argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_count()");

	// ---Add your Own code to control device here ---
	try {
	    ((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(argin,
		    getTimeNow());
	    argout = dbProxy.getDataBase().getExtractor().getDataGetters().getAttDataCount(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_data_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataAvg" on device. Returns the average value
     * generated by the given attribute.
     * 
     * @param argin
     *            The attribute's name
     * @return The average of the values generated by the attribute
     */
    // =========================================================
    public double get_att_data_avg(final String argin) throws DevFailed {
	double argout = 0;

	get_logger().info("Entering get_att_data_avg()");

	// ---Add your Own code to control device here ---
	try {
	    ((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(argin,
		    getTimeNow());
	    argout = dbProxy.getDataBase().getExtractor().getMinMaxAvgGetters()
		    .getAttDataAvg(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_data_avg()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataAvgBetweenDates" on device. Returns the
     * average value generated by the given attribute and between the two given
     * dates.
     * 
     * @param argin
     *            The attribute's name, the beginning date (DD-MM-YYYY
     *            HH24:MI:SS) and the ending date (DD-MM-YYYY HH24:MI:SS)
     * @return The average value generated by the given attribute and between
     *         the two given dates.
     */
    // =========================================================
    public double get_att_data_avg_between_dates(final String[] argin) throws DevFailed {
	double argout = 0;

	get_logger().info("Entering get_att_data_avg_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_avg_between_dates");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[2]);

		argout = dbProxy.getDataBase().getExtractor().getMinMaxAvgGettersBetweenDates()
			.getAttDataAvgBetweenDates(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_avg_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataBetweenDates" on device. Retrieves data
     * beetwen two dates, for a given scalar attribute. Create a dynamic
     * attribute, retrieve data from database and prepare result for
     * attribute_history call.
     * 
     * @param argin
     *            : The attribute's name, the beginning date (DD-MM-YYYY
     *            HH24:MI:SS) and the ending date (DD-MM-YYYY HH24:MI:SS), and
     *            the sampling type (ALL, SECOND, MINUTE, HOUR, DAY)
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_between_dates_sampling(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;
	SamplingType samplingType = null;

	get_logger().info("Entering get_att_data_between_dates_sampling()");

	// ---Add your Own code to control device here ---
	if (argin.length != 4) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_data_between_dates_sampling");
	}

	try {
	    samplingType = SamplingType.getSamplingTypeByLabel(argin[3]);
	} catch (final IllegalArgumentException iae) {
	    Except.throw_exception("CONFIGURATION_ERROR",
		    "Invalid sampling type. Valid types are ALL, SECOND, MINUTE, HOUR, or DAY",
		    "TdbExtractor.get_att_data_between_dates_sampling");
	}

	try {
	    // Get data from db.
	    final DbData dbData = dbProxy.getDataBase().getExtractor().getDataGettersBetweenDates()
		    .getAttDataBetweenDates(argin, samplingType);
	    // Buid an attribute and gets its references
	    argout = add_attribute(dbData);
	} catch (final ArchivingException e) {
	    e.printStackTrace();
	    throw e.toTangoException();
	} catch (final java.lang.OutOfMemoryError oome) {
	    get_logger().info("OutOfMemoryError in get_att_data_between_dates_sampling");
	    Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
		    "TdbExtractor.get_att_data_between_dates_sampling");
	} catch (final Throwable t) {
	    t.printStackTrace();
	    if (t instanceof DevFailed) {
		final DevError[] errors = ((DevFailed) t).errors;
		if (errors != null) {
		    for (final DevError error : errors) {
			get_logger().error(
				"Error: /desc/" + error.desc + "/origin/" + error.origin
					+ "/reason/" + error.reason);
		    }
		}

		get_logger().error("Error: /CAUSE---------------");
		if (((DevFailed) t).getCause() != null) {
		    ((DevFailed) t).getCause().printStackTrace();
		}
	    }
	}

	get_logger().info("Exiting get_att_data_between_dates_sampling()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataBetweenDates" on device. Retrieves data
     * beetwen two dates, for a given scalar attribute. Create a dynamic
     * attribute, retrieve data from database and prepare result for
     * attribute_history call.
     * 
     * @param argin
     *            : The attribute's name, the beginning date (DD-MM-YYYY
     *            HH24:MI:SS) and the ending date (DD-MM-YYYY HH24:MI:SS)
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_between_dates(final String[] argin) throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_beetween_dates");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[2]);

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor()
			.getDataGettersBetweenDates().getAttDataBetweenDates(argin,
				SamplingType.getSamplingType(SamplingType.ALL));

		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		e.printStackTrace();
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_between_dates");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_between_dates");
	    } catch (final Throwable t) {
		t.printStackTrace();
		if (t instanceof DevFailed) {
		    final DevError[] errors = ((DevFailed) t).errors;
		    if (errors != null) {
			for (final DevError error : errors) {
			    get_logger().error(
				    "Error: /desc/" + error.desc + "/origin/" + error.origin
					    + "/reason/" + error.reason);
			}
		    }

		    get_logger().error("Error: /CAUSE---------------");
		    if (((DevFailed) t).getCause() != null) {
			((DevFailed) t).getCause().printStackTrace();
		    }
		}
	    }
	}
	get_logger().info("Exiting get_att_data_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataBetweenDatesCount" on device. Returns the
     * number of data beetwen two dates and, for a given scalar attribute.
     * 
     * @param argin
     *            The attribute's name, the beginning (DD-MM-YYYY HH24:MI:SS)
     *            date and the ending date (DD-MM-YYYY HH24:MI:SS).
     * @return The number of data beetwen two dates and, for a given scalar
     *         attribute.
     */
    // =========================================================
    public int get_att_data_between_dates_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_between_dates_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_between_dates_count");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[2]);

		argout = dbProxy.getDataBase().getExtractor().getDataGettersBetweenDates()
			.getAttDataBetweenDatesCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_between_dates_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfOrSupThan" on device. Retrieves all data
     * that are lower than the given value x OR higher than the given value y.
     * Create a dynamic attribute, retrieve the corresponding data from database
     * and prepare result for attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the lower limit and the upper limit
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_inf_or_sup_than(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_inf_or_sup_than()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_or_sup_than");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getManager()).getTdbExport().ExportData2Tdb(argin[0],
			getTimeNow());

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataInfOrSupThan(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_inf_or_sup_than");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_inf_or_sup_than");
	    }
	}
	get_logger().info("Exiting get_att_data_inf_or_sup_than()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfOrSupThanCount" on device. Returns the
     * number of data lower than the given value x OR higher than the given
     * value y.
     * 
     * @param argin
     *            The attribute's name, the lower limit and the upper limit
     * @return The number of scalar data lower than the given value x OR higher
     *         than the given value y.
     */
    // =========================================================
    public int get_att_data_inf_or_sup_than_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_inf_or_sup_than_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_or_sup_than_count");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		argout = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataInfOrSupThanCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_inf_or_sup_than_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfOrSupThanBetweenDates" on device. Retrieves
     * data beetwen two dates (date_1 & date_2) - Data are lower than the given
     * value x OR higher than the given value y. Create a dynamic attribute,
     * retrieve the corresponding data from database and prepare result for
     * attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the lower limit, the upper limit, the
     *            beginning date (DD-MM-YYYY HH24:MI:SS) and the ending date
     *            (DD-MM-YYYY HH24:MI:SS)
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_inf_or_sup_than_between_dates(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_inf_or_sup_than_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 5) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_or_sup_than_beetween_dates");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[4]);

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor()
			.getInfSupGettersBetweenDates().getAttDataInfOrSupThanBetweenDates(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_inf_or_sup_than_between_dates");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_inf_or_sup_than_between_dates");
	    }
	}
	get_logger().info("Exiting get_att_data_inf_or_sup_than_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfOrSupThanBetweenDatesCount" on device.
     * Returns the number of data beetwen two dates (date_1 & date_2). Data are
     * lower than the given value x OR higher than the given value y.
     * 
     * @param argin
     *            The attribute's name, the lower limit, the upper limit, the
     *            beginning date (DD-MM-YYYY HH24:MI:SS) and the ending date
     *            (DD-MM-YYYY HH24:MI:SS).
     * @return The number of scalar data lower than the given value x OR higher
     *         than the given value y, beetwen two dates and for the specified
     *         attribute.
     */
    // =========================================================
    public int get_att_data_inf_or_sup_than_between_dates_count(final String[] argin)
	    throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_inf_or_sup_than_between_dates_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 5) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_or_sup_than_between_dates_count");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[5]);

		argout = dbProxy.getDataBase().getExtractor().getInfSupGettersBetweenDates()
			.getAttDataInfOrSupThanBetweenDatesCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_inf_or_sup_than_between_dates_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfThan" on device. Retrieves all the data
     * that are lower than the given value x. Create a dynamic attribute,
     * retrieve the corresponding data from database and prepare result for
     * attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the upper limit
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_inf_than(final String[] argin) throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_inf_than()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_than");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataInfThan(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_inf_than");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_inf_than");
	    }
	}
	get_logger().info("Exiting get_att_data_inf_than()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfThanCount" on device. Returns the number of
     * data lower than the given value.
     * 
     * @param argin
     *            The attribute's name and the upper limit.
     * @return The number of scalar data lower than the given value and for the
     *         specified attribute.
     */
    // =========================================================
    public int get_att_data_inf_than_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_inf_than_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_than_count");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		argout = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataInfThanCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_inf_than_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfThanBetweenDates" on device. Retrieves data
     * beetwen two dates (date_1 & date_2) - Data are lower than the given value
     * x. Create a dynamic attribute, retrieve the corresponding data from
     * database and prepare result for attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the upper limit, the beginning date
     *            (DD-MM-YYYY HH24:MI:SS) and the ending date (DD-MM-YYYY
     *            HH24:MI:SS)
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_inf_than_between_dates(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_inf_than_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 4) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_than_beetween_dates");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[3]);

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor()
			.getInfSupGettersBetweenDates().getAttDataInfThanBetweenDates(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_inf_than_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataInfThanBetweenDatesCount" on device. Returns
     * the number data lower than the given value x, and beetwen two dates
     * (date_1 & date_2).
     * 
     * @param argin
     *            The attribute's name, the upper limit, the beginning date
     *            (DD-MM-YYYY HH24:MI:SS) and the ending date (DD-MM-YYYY
     *            HH24:MI:SS).
     * @return The number data lower than the given value x, and beetwen two
     *         dates (date_1 & date_2).
     */
    // =========================================================
    public int get_att_data_inf_than_between_dates_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_inf_than_between_dates_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 4) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_inf_than_between_dates_count");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[3]);

		argout = dbProxy.getDataBase().getExtractor().getInfSupGettersBetweenDates()
			.getAttDataInfThanBetweenDatesCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_inf_than_between_dates_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataLastN" on device. Retrieves the last n
     * archived data, for a given scalar attribute. Create a dynamic attribute,
     * retrieve the corresponding data from database and prepare result for
     * attribute_history call.
     * 
     * @param argin
     *            The attribute's name and the number of wished data
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_last_n(final String[] argin) throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_last_n()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_last_n");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor().getDataGetters()
			.getAttDataLast_n(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		e.printStackTrace();
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_last_n");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_last_n");
	    } catch (final Throwable t) {
		t.printStackTrace();
		if (t instanceof DevFailed) {
		    final DevError[] errors = ((DevFailed) t).errors;
		    if (errors != null) {
			for (final DevError error : errors) {
			    get_logger().error(
				    "Error: /desc/" + error.desc + "/origin/" + error.origin
					    + "/reason/" + error.reason);
			}
		    }

		    get_logger().error("Error: /CAUSE---------------");
		    if (((DevFailed) t).getCause() != null) {
			((DevFailed) t).getCause().printStackTrace();
		    }
		}
	    }
	}
	get_logger().info("Exiting get_att_data_last_n()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataMax" on device. Returns the biggest value
     * generated by the attribute.
     * 
     * @param argin
     *            The attribute's name
     * @return The biggest value generated by the attribute
     */
    // =========================================================
    public double get_att_data_max(final String argin) throws DevFailed {
	double argout = 0;

	get_logger().info("Entering get_att_data_max()");

	// ---Add your Own code to control device here ---
	try {
	    ((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(argin,
		    getTimeNow());

	    argout = dbProxy.getDataBase().getExtractor().getMinMaxAvgGetters()
		    .getAttDataMax(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_data_max()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataMaxBetweenDates" on device. Returns the
     * biggest value generated between the two given dates.
     * 
     * @param argin
     *            The attribute's name, the beginning date (DD-MM-YYYY
     *            HH24:MI:SS) and the ending date (DD-MM-YYYY HH24:MI:SS)
     * @return The biggest value generated between the two given dates.
     */
    // =========================================================
    public double get_att_data_max_between_dates(final String[] argin) throws DevFailed {
	double argout = 0;

	get_logger().info("Entering get_att_data_max_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_max_between_dates");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[2]);

		argout = dbProxy.getDataBase().getExtractor().getMinMaxAvgGettersBetweenDates()
			.getAttDataMaxBetweenDates(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_max_between_dates()");
	return argout;
    }

    public void remove_dynamic_attributes() throws DevFailed {
	get_logger().info("Entering remove_dynamic_attributes()");
	while (dev_attr.get_attr_nb() > 0) {
	    final Attribute nextAttribute = dev_attr.get_attr_by_ind(0);
	    final String attributeName = nextAttribute.get_name();
	    remove_attribute(attributeName);
	}
	get_logger().info("Exiting remove_dynamic_attributes()");
    }

    // =========================================================
    /**
     * Execute command "GetAttDataMin" on device. Returns the smallest scalar
     * value generated by the attribute.
     * 
     * @param argin
     *            The attribute's name
     * @return The smallest value generated by the attribute
     */
    // =========================================================
    public double get_att_data_min(final String argin) throws DevFailed {
	double argout = 0;

	get_logger().info("Entering get_att_data_min()");

	// ---Add your Own code to control device here ---
	try {
	    ((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(argin,
		    getTimeNow());

	    argout = dbProxy.getDataBase().getExtractor().getMinMaxAvgGetters()
		    .getAttDataMin(argin);
	} catch (final ArchivingException e) {
	    throw e.toTangoException();
	}
	get_logger().info("Exiting get_att_data_min()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataMinBetweenDates" on device. Returns the
     * smallest scalar value generated by the given attribute and between two
     * given dates
     * 
     * @param argin
     *            The attribute's name, the beginning date (DD-MM-YYYY
     *            HH24:MI:SS) and the ending date (DD-MM-YYYY HH24:MI:SS)
     * @return The smallest scalar value generated by the given attribute and
     *         between the two given dates.
     */
    // =========================================================
    public double get_att_data_min_between_dates(final String[] argin) throws DevFailed {
	double argout = 0;

	get_logger().info("Entering get_att_data_min_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_min_between_dates");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[2]);

		argout = dbProxy.getDataBase().getExtractor().getMinMaxAvgGettersBetweenDates()
			.getAttDataMinBetweenDates(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_min_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupThan" on device. Retrieves all the data
     * that are higher than the given value x. Create a dynamic attribute,
     * retrieve the corresponding data from database and prepare result for
     * attribute_history call.
     * 
     * @param argin
     *            The attribute's name and the lower limit
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_sup_than(final String[] argin) throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_sup_than()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_than");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataSupThan(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_sup_than");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_sup_than");
	    }
	}
	get_logger().info("Exiting get_att_data_sup_than()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupThanCount" on device. Returns the number of
     * data higher than the given value.
     * 
     * @param argin
     *            The attribute's name and the lower limit.
     * @return The number of data higher than the given value.
     */
    // =========================================================
    public int get_att_data_sup_than_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_sup_than_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 2) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_than_count");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		argout = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataSupThanCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_sup_than_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupAndInfThan" on device. Retrieves all data
     * that are higher than the given value x AND lower than the given value y.
     * Create a dynamic attribute, retrieve the corresponding data from database
     * and prepare result for attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the lower limit and the upper limit
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_sup_and_inf_than(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_sup_and_inf_than()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_and_inf_than");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataSupAndInfThan(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_sup_and_inf_than");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_sup_and_inf_than");
	    }
	}
	get_logger().info("Exiting get_att_data_sup_and_inf_than()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupAndInfThanCount" on device. Returns data
     * that are highter than the given value x AND lower than the given value y.
     * 
     * @param argin
     *            The attribute's name, the lower limit and the upper limit
     * @return The data that are highter than the given value x AND lower than
     *         the given value y.
     */
    // =========================================================
    public int get_att_data_sup_and_inf_than_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_sup_and_inf_than_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 3) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_and_inf_than_count");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], getTimeNow());

		argout = dbProxy.getDataBase().getExtractor().getInfSupGetters()
			.getAttDataSupAndInfThanCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_sup_and_inf_than_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupAndInfThanBetweenDates" on device.
     * Retrieves data beetwen two dates (date_1 & date_2) - Data are higher than
     * the given value x AND lower than the given value y. Create a dynamic
     * attribute, retrieve the corresponding data from database and prepare
     * result for attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the lower limit, the upper limit, the
     *            beginning date (DD-MM-YYYY HH24:MI:SS) and the ending date
     *            (DD-MM-YYYY HH24:MI:SS)
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_sup_and_inf_than_between_dates(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_sup_and_inf_than_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 5) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_and_inf_than_beetween_dates");
	} else {
	    try {

		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[4]);

		// Get data from db.
		final DbData dbData = dbProxy.getDataBase().getExtractor()
			.getInfSupGettersBetweenDates().getAttDataSupAndInfThanBetweenDates(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger()
			.info("OutOfMemoryError in get_att_data_sup_and_inf_than_between_dates");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_sup_and_inf_than_between_dates");
	    }
	}
	get_logger().info("Exiting get_att_data_sup_and_inf_than_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupAndInfThanBetweenDatesCount" on device.
     * Returns the number of data higher than the given value x, (AND) lower
     * than the given value y, and beetwen two dates (date_1 & date_2).
     * 
     * @param argin
     *            The attribute's name, the lower limit, the upper limit, the
     *            beginning date (DD-MM-YYYY HH24:MI:SS) and the ending date
     *            (DD-MM-YYYY HH24:MI:SS).
     * @return The number of data higher than the given value x, (AND) lower
     *         than the given value y, and beetwen two dates (date_1 & date_2).
     */
    // =========================================================
    public int get_att_data_sup_and_inf_than_between_dates_count(final String[] argin)
	    throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_sup_and_inf_than_between_dates_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 5) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_and_inf_than_between_dates_count");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[4]);

		argout = dbProxy.getDataBase().getExtractor().getInfSupGettersBetweenDates()
			.getAttDataSupAndInfThanBetweenDatesCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_sup_and_inf_than_between_dates_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupThanBetweenDates" on device. Retrieves data
     * beetwen two dates (date_1 & date_2) - Data are higher than the given
     * value x. Create a dynamic attribute, retrieve the corresponding data from
     * database and prepare result for attribute_history call.
     * 
     * @param argin
     *            The attribute's name, the lower limit, the beginning date
     *            (DD-MM-YYYY HH24:MI:SS) and the ending date (DD-MM-YYYY
     *            HH24:MI:SS)
     * @return String : The new created dynamic attribute name, Long : the
     *         number of data.
     */
    // =========================================================
    public DevVarLongStringArray get_att_data_sup_than_between_dates(final String[] argin)
	    throws DevFailed {
	DevVarLongStringArray argout = null;

	get_logger().info("Entering get_att_data_sup_than_between_dates()");

	// ---Add your Own code to control device here ---
	if (argin.length != 4) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_than_beetween_dates");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[3]);

		final DbData dbData = dbProxy.getDataBase().getExtractor()
			.getInfSupGettersBetweenDates().getAttDataSupThanBetweenDates(argin);
		// Buid an attribute and gets its references
		argout = add_attribute(dbData);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    } catch (final java.lang.OutOfMemoryError oome) {
		get_logger().info("OutOfMemoryError in get_att_data_sup_than_between_dates");
		Except.throw_exception("MEMORY_ERROR", HintOnMemoryError,
			"TdbExtractor.get_att_data_sup_than_between_dates");
	    }
	}
	get_logger().info("Exiting get_att_data_sup_than_between_dates()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "GetAttDataSupThanBetweenDatesCount" on device. Returns
     * the number of data higher than the given value y, and beetwen two dates
     * (date_1 & date_2).
     * 
     * @param argin
     *            The attribute's name, the lower limit, the beginning date
     *            (DD-MM-YYYY HH24:MI:SS) and the ending date (DD-MM-YYYY
     *            HH24:MI:SS).
     * @return The number of data higher than the given value y, and beetwen two
     *         dates (date_1 & date_2).
     */
    // =========================================================
    public int get_att_data_sup_than_between_dates_count(final String[] argin) throws DevFailed {
	int argout = 0;

	get_logger().info("Entering get_att_data_sup_than_between_dates_count()");

	// ---Add your Own code to control device here ---
	if (argin.length != 4) {
	    Except.throw_exception("CONFIGURATION_ERROR", "Wrong number of parameters",
		    "TdbExtractor.get_att_scalar_data_sup_than_between_dates_count");
	} else {
	    try {
		((TDBDataBaseManager) dbProxy.getDataBase()).getTdbExport().ExportData2Tdb(
			argin[0], argin[3]);

		argout = dbProxy.getDataBase().getExtractor().getInfSupGettersBetweenDates()
			.getAttDataSupThanBetweenDatesCount(argin);
	    } catch (final ArchivingException e) {
		throw e.toTangoException();
	    }
	}
	get_logger().info("Exiting get_att_data_sup_than_between_dates_count()");
	return argout;
    }

    // =========================================================
    /**
     * Execute command "RemoveDynamicAttribute" on device. Remove the dynamic
     * attribute.
     * 
     * @param argin
     *            The TdbExtractor dynamic attribute's name
     */
    // =========================================================
    public void remove_dynamic_attribute(final String argin) throws DevFailed {
	get_logger().info("Entering remove_dynamic_attribute()");

	// ---Add your Own code to control device here ---
	// Remove prop for the attribute
	final String[] obj_to_del = new String[3];
	obj_to_del[0] = device_name;
	obj_to_del[1] = "attribute";
	obj_to_del[2] = argin;
	final Util tg = Util.instance();
	tg.get_dserver_device().rem_obj_polling(obj_to_del, false);

	// Remove the attribute
	remove_attribute(argin);
	get_logger().info("Exiting remove_dynamic_attribute()");
    }

    private DevVarLongStringArray add_attribute(final DbData dbData) throws DevFailed,
	    ArchivingException {
	if (dbData.getData_timed() == null || dbData.getData_timed().length == 0) {
	    // final String message = "Can't create the dynamic attribute!";
	    // final String reason = "The data is empty.";
	    // final String desc =
	    // "add_attribute/The DbData argument is empty.";
	    // throw new ArchivingException(message , reason , null , desc ,
	    // this.getClass().getName());
	    final int[] lv = { 0 };
	    final String[] sv = { "NO_DATA" };
	    final DevVarLongStringArray res = new DevVarLongStringArray(lv, sv);
	    return res;

	}

	final boolean _2value = dbData.getWritable() == AttrWriteType._READ_WITH_WRITE
		|| dbData.getWritable() == AttrWriteType._READ_WRITE ? true : false;
	String random_name_1 = "", random_name_2 = "";
	String[] names;
	// Build new Attribute's name
	final boolean firstIsRead = dbData.getWritable() != AttrWriteType._WRITE;
	// random_name_1 = "att_000" + id++;
	random_name_1 = getName(dbData.getName(), id, firstIsRead);
	if (_2value) {
	    // random_name_2 = "att_000" + id++;
	    random_name_2 = getName(dbData.getName(), id, !firstIsRead);
	}
	id++;

	if (!_2value) {
	    names = new String[1];
	    names[0] = random_name_1;
	} else {
	    names = new String[2];
	    names[0] = random_name_1;
	    names[1] = random_name_2;
	}

	// Create the attribute depends on DataFormat
	int data_type;
	data_type = dbData.getData_type();
	// The STATE type attributes are translated in STRING TYPE because the
	// State type is not well
	// managed in Tango layers
	if (data_type == TangoConst.Tango_DEV_STATE) {
	    data_type = TangoConst.Tango_DEV_STRING;
	}

	switch (dbData.getData_format()) {
	case AttrDataFormat._SCALAR:
	    // TODO Correction Tango � reporter !! Tango [Java side] ne
	    // supporte pas les attributs READ-WRITE.
	    // add_attribute(new Attr(random_name_1, dbData.getData_type(),
	    // AttrWriteType.from_int(dbData.getWritable())));

	    add_attribute(new Attr(random_name_1, data_type, AttrWriteType.READ));
	    if (_2value) {
		add_attribute(new Attr(random_name_2, data_type, AttrWriteType.READ));
	    }
	    break;
	case AttrDataFormat._SPECTRUM:
	    int dimX = dbData.getMax_x();
	    if (dimX == 0) {
		dimX = 1;
	    }
	    add_attribute(new SpectrumAttr(random_name_1, data_type, dimX));
	    if (_2value) {
		add_attribute(new SpectrumAttr(random_name_2, data_type, dimX));
	    }
	    break;
	case AttrDataFormat._IMAGE:
	    add_attribute(new ImageAttr(random_name_1, data_type, dbData.getMax_x(), dbData
		    .getMax_y()));
	    break;
	}

	get_logger().info("attr " + random_name_1 + " created");
	if (_2value) {
	    get_logger().info("attr " + random_name_2 + " created");
	}
	set_polled_attr(names);
	set_poll_ring_depth(dbData.size());

	// Start polling for this device in external sync. mode (udt = 0)
	DevVarLongStringArray poll_1, poll_2;
	poll_1 = new DevVarLongStringArray();
	poll_2 = new DevVarLongStringArray();

	poll_1.lvalue = new int[1];
	poll_1.lvalue[0] = 0;
	poll_1.svalue = new String[3];
	poll_1.svalue[0] = device_name;
	poll_1.svalue[1] = "attribute";
	poll_1.svalue[2] = random_name_1;

	if (_2value) {
	    poll_2.lvalue = new int[1];
	    poll_2.lvalue[0] = 0;
	    poll_2.svalue = new String[3];
	    poll_2.svalue[0] = device_name;
	    poll_2.svalue[1] = "attribute";
	    poll_2.svalue[2] = random_name_2;
	}
	final Util tg_1 = Util.instance();
	tg_1.get_dserver_device().add_obj_polling(poll_1, false);

	final Util tg_2 = Util.instance();
	if (_2value) {
	    tg_2.get_dserver_device().add_obj_polling(poll_2, false);
	}

	// And fill buffer with database's data

	if (_2value) {
	    final DbData[] dbDatas = dbData.splitDbData();
	    tg_1.fill_attr_polling_buffer(this, random_name_1, dbDatas[0].getDataAsTimedAttrData());
	    tg_2.fill_attr_polling_buffer(this, random_name_2, dbDatas[1].getDataAsTimedAttrData());
	} else {
	    tg_1.fill_attr_polling_buffer(this, random_name_1, dbData.getDataAsTimedAttrData());
	}

	final DevVarLongStringArray argout = new DevVarLongStringArray();
	argout.lvalue = new int[1];
	if (!_2value) {
	    argout.svalue = new String[1];
	} else {
	    argout.svalue = new String[2];
	}

	argout.lvalue[0] = dbData.getData_timed().length;
	argout.svalue[0] = random_name_1;
	if (_2value) {
	    argout.svalue[1] = random_name_2;
	}

	return argout;
    }

    public String getName(final String completeName, final int id, final boolean isReadValue) {
	final String partialName = getPartialName(completeName);
	final String marker = isReadValue ? "r" : "w";

	final StringBuffer buff = new StringBuffer();
	buff.append(partialName);
	buff.append("_");
	buff.append(String.valueOf(id));
	buff.append("_");
	buff.append(marker);

	return buff.toString().toLowerCase();
    }

    /**
     * @param completeName
     * @return
     */
    private String getPartialName(final String completeName) {
	try {
	    final StringTokenizer st = new StringTokenizer(completeName, "/");
	    st.nextToken();// domain
	    st.nextToken();// family
	    st.nextToken();// member
	    return st.nextToken();// attribute
	} catch (final Exception e) {
	    e.printStackTrace();
	    return null;
	}
    }

    /*
     * private DevVarLongStringArray add_attribute(DbData dbData) throws
     * DevFailed { DevVarLongStringArray argout = new DevVarLongStringArray();
     * boolean _2value = ( dbData.getWritable() ==
     * AttrWriteType._READ_WITH_WRITE || dbData.getWritable() ==
     * AttrWriteType._READ_WRITE ) ? true : false; String random_name_1 = "" ,
     * random_name_2 = ""; String[] names; // Build new Attribute's name
     * random_name_1 = "att_000" + id++; if ( _2value ) random_name_2 =
     * "att_000" + id++; if ( !_2value ) { names = new String[ 1 ]; names[ 0 ] =
     * random_name_1; } else { names = new String[ 2 ]; names[ 0 ] =
     * random_name_1; names[ 1 ] = random_name_2; } // Create the attribute
     * depends on DataFormat switch ( dbData.getData_format() ) { case
     * AttrDataFormat._SCALAR: // TODO Correction Tango � reporter !! Tango
     * [Java side] ne supporte pas les attributs READ-WRITE. //add_attribute(new
     * Attr(random_name_1, dbData.getData_type(),
     * AttrWriteType.from_int(dbData.getWritable()))); add_attribute(new
     * Attr(random_name_1 , dbData.getData_type() , AttrWriteType.READ)); if (
     * _2value ) add_attribute(new Attr(random_name_2 , dbData.getData_type() ,
     * AttrWriteType.READ)); break; case AttrDataFormat._SPECTRUM:
     * add_attribute(new SpectrumAttr(random_name_1 , dbData.getData_type() ,
     * dbData.getMax_x())); break; case AttrDataFormat._IMAGE: add_attribute(new
     * ImageAttr(random_name_1 , dbData.getData_type() , dbData.getMax_x() ,
     * dbData.getMax_y())); break; } get_logger().info("attr " + random_name_1 +
     * " created"); if ( _2value ) get_logger().info("attr " + random_name_2 +
     * " created"); set_polled_attr(names); set_poll_ring_depth(dbData.size());
     * // Start polling for this device in external sync. mode (udt = 0)
     * DevVarLongStringArray poll_1 , poll_2; poll_1 = new
     * DevVarLongStringArray(); poll_2 = new DevVarLongStringArray();
     * poll_1.lvalue = new int[ 1 ]; poll_1.lvalue[ 0 ] = 0; poll_1.svalue = new
     * String[ 3 ]; poll_1.svalue[ 0 ] = device_name; poll_1.svalue[ 1 ] =
     * "attribute"; poll_1.svalue[ 2 ] = random_name_1; if ( _2value ) {
     * poll_2.lvalue = new int[ 1 ]; poll_2.lvalue[ 0 ] = 0; poll_2.svalue = new
     * String[ 3 ]; poll_2.svalue[ 0 ] = device_name; poll_2.svalue[ 1 ] =
     * "attribute"; poll_2.svalue[ 2 ] = random_name_2; } Util tg_1 =
     * Util.instance(); tg_1.get_dserver_device().add_obj_polling(poll_1 ,
     * false); Util tg_2 = Util.instance(); if ( _2value )
     * tg_2.get_dserver_device().add_obj_polling(poll_2 , false); // And fill
     * buffer with database's data DbData[] dbDatas = dbData.splitDbData();
     * tg_1.fill_attr_polling_buffer(this , random_name_1 , dbDatas[ 0
     * ].getData()); if ( _2value ) tg_2.fill_attr_polling_buffer(this ,
     * random_name_2 , dbDatas[ 1 ].getData()); argout = new
     * DevVarLongStringArray(); argout.lvalue = new int[ 1 ]; if ( !_2value ) {
     * argout.svalue = new String[ 1 ]; } else { argout.svalue = new String[ 2
     * ]; } argout.lvalue[ 0 ] = dbData.getData().length; argout.svalue[ 0 ] =
     * random_name_1; if ( _2value ) argout.svalue[ 1 ] = random_name_2; return
     * argout; }
     */
    // ===================================================================
    /**
     * Method called by the read_attributes CORBA operation to set internal
     * attribute value.
     * 
     * @param attr
     *            reference to the Attribute object
     */
    // ===================================================================
    @Override
    public void read_attr(final Attribute attr) throws DevFailed {
	final String attr_name = attr.get_name();
	// get_logger().info("In read_attr for attribute " + attr_name);

	// Switch on attribute name
	// ---------------------------------
	if (attr_name == "version") {
	    // Add your own code here
	    attr.set_value(m_version);
	}
    }

    // =========================================================
    /**
     * main part for the device server class
     */
    // =========================================================
    public static void main(final String[] argv) {
	try {
	    final Util tg = Util.init(argv, "TdbExtractor");
	    tg.server_init();

	    System.out.println("Ready to accept request");

	    tg.server_run();
	} catch (final OutOfMemoryError ex) {
	    System.err.println("Can't allocate memory !!!!");
	    System.err.println("Exiting");
	} catch (final UserException ex) {
	    Except.print_exception(ex);

	    System.err.println("Received a CORBA user exception");
	    System.err.println("Exiting");
	} catch (final SystemException ex) {
	    Except.print_exception(ex);

	    System.err.println("Received a CORBA system exception");
	    System.err.println("Exiting");
	}

	System.exit(-1);
    }

    public String get_max_time(final String argin) throws DevFailed {
	try {
	    final DataBaseManager db = dbProxy.getDataBase();
	    final Timestamp last = db.getDbUtil().getTimeOfLastInsert(argin, true);
	    if (last == null) {
		return "NO VALUES RECORDED";
	    }
	    return "" + last;
	} catch (final ArchivingException e) {
	    e.printStackTrace();
	    throw e.toTangoException();
	}
    }

    public String get_min_time(final String argin) throws DevFailed {
	try {
	    final DataBaseManager db = dbProxy.getDataBase();
	    final Timestamp last = db.getDbUtil().getTimeOfLastInsert(argin, false);
	    if (last == null) {
		return "NO VALUES RECORDED";
	    }
	    return "" + last;
	} catch (final ArchivingException e) {
	    e.printStackTrace();
	    throw e.toTangoException();
	}
    }

    /*
 *
 */
    public static String getTimeNow() {
	// TODO Auto-generated method stub
	final long _now = System.currentTimeMillis();
	return new Timestamp(_now).toString();

    }

    @Override
    public void delete_device() throws DevFailed {
	// TODO Auto-generated method stub

    }
}
// --------------------------------------------------------------------------
/*
 * end of $Source:
 * /cvsroot/tango-cs/archiving/server/hdbtdbArchivingServers/src/
 * main/java/TdbExtractor/TdbExtractor.java,v $
 */
