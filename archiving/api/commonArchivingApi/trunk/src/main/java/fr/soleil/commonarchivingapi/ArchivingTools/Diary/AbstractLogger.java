//+======================================================================
// $Source: /cvsroot/tango-cs/tango/api/java/fr/soleil/TangoArchiving/ArchivingTools/Diary/AbstractLogger.java,v $
//
// Project:      Tango Archiving Service
//
// Description:  Java source code for the class  AbstractLogger.
//						(Claisse Laurent) - 5 juil. 2005
//
// $Author: ounsy $
//
// $Revision: 1.3 $
//
// $Log: AbstractLogger.java,v $
// Revision 1.3  2006/11/20 09:31:54  ounsy
// the diary path is no longer added "/diary"
//
// Revision 1.2  2006/07/18 15:17:34  ounsy
// added a file-changing mechanism to change log files when the day changes
//
// Revision 1.1  2006/06/16 09:24:03  ounsy
// moved from the TdbArchiving project
//
// Revision 1.2  2006/06/13 13:30:04  ounsy
// minor changes
//
// Revision 1.1  2006/06/08 08:34:49  ounsy
// creation
//
// Revision 1.3  2006/05/19 15:05:29  ounsy
// minor changes
//
// Revision 1.2  2005/11/29 18:28:12  chinkumo
// no message
//
// Revision 1.1.2.2  2005/09/14 15:41:32  chinkumo
// Second commit !
//
//
// copyleft :	Synchrotron SOLEIL
//					L'Orme des Merisiers
//					Saint-Aubin - BP 48
//					91192 GIF-sur-YVETTE CEDEX
//
//-======================================================================
package fr.soleil.commonarchivingapi.ArchivingTools.Diary;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Hashtable;

public abstract class AbstractLogger implements ILogger {
    protected int traceLevel;
    protected PrintWriter writer;
    protected static Hashtable<Integer, String> levels = new Hashtable<Integer, String>(5);;
    protected static Hashtable<String, Integer> reverseLevels = new Hashtable<String, Integer>(5);
    static {
	levels.put(new Integer(LEVEL_CRITIC), CRITIC);
	levels.put(new Integer(LEVEL_ERROR), ERROR);
	levels.put(new Integer(LEVEL_WARNING), WARNING);
	levels.put(new Integer(LEVEL_INFO), INFO);
	levels.put(new Integer(LEVEL_DEBUG), DEBUG);

	reverseLevels.put(CRITIC, new Integer(LEVEL_CRITIC));
	reverseLevels.put(ERROR, new Integer(LEVEL_ERROR));
	reverseLevels.put(WARNING, new Integer(LEVEL_WARNING));
	reverseLevels.put(INFO, new Integer(LEVEL_INFO));
	reverseLevels.put(DEBUG, new Integer(LEVEL_DEBUG));
    }

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#getTraceLevel()
     */
    public int getTraceLevel() {
	return traceLevel;
    }

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#setTraceLevel(int)
     */
    public void setTraceLevel(final int level) {
	// System.out.println ( "AbstractLogger/setTraceLevel/level/"+level+"/"
	// );
	traceLevel = level;
    }

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#setTraceLevel(java.lang.String)
     */
    public void setTraceLevel(final java.lang.String _level) {
	// System.out.println ( "AbstractLogger/setTraceLevel/level/"+level+"/"
	// );
	int level = ILogger.LEVEL_DEBUG;

	try {
	    final Integer _levelI = reverseLevels.get(_level);
	    level = _levelI.intValue();
	} catch (final Exception e) {
	    e.printStackTrace();
	}

	traceLevel = level;
    }

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#initDiaryWriter(java.lang.String)
     */
    public void initDiaryWriter(final String path, final String archiver) throws IOException {
	final String refactoredArchiverName = refactorArchiverName(archiver);
	new File(path).mkdirs();
	final String format = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
	archiver.lastIndexOf("/");
	final String fileName = path + "/diary_" + refactoredArchiverName + "_" + format + ".log";
	System.out.println("New diary file " + fileName);
	writer = new PrintWriter(new FileWriter(fileName, true));
    }

    private String refactorArchiverName(final String archiver) {
	final String ret = archiver.substring(archiver.lastIndexOf("/") + 1);
	return ret;
    }

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#getTraceLevel(java.lang.String)
     */
    public int getTraceLevel(final String level_s) {
	final Integer lev = reverseLevels.get(level_s);
	if (lev == null) {
	    return LEVEL_DEBUG;
	}

	try {
	    final int ret = lev.intValue();
	    return ret;
	} catch (final NumberFormatException nfe) {
	    nfe.printStackTrace();
	    return LEVEL_DEBUG;
	}
    }

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#trace(int, java.lang.Object)
     */
    public void trace(final int level, final Object o) {
	// System.out.println (
	// "AbstractLogger/trace/level/"+level+"/this.traceLevel/"+this.traceLevel+"/"
	// );

	if (level <= traceLevel) {
	    try {
		changeDiaryFileIfNecessary();
		addToDiary(level, o);
	    } catch (final Exception e) {
		e.printStackTrace();
	    }

	    if (o instanceof String) {
		String msg = (String) o;
		msg = getDecoratedLog(msg, level);
		log(msg);
	    }
	}
    }

    protected abstract void changeDiaryFileIfNecessary() throws IOException;

    /**
     * @param msg
     * @param level
     * @return 8 juil. 2005
     */
    protected abstract String getDecoratedLog(String msg, int level);

    /**
     * @param level
     * @param o
     * @throws Exception
     *             8 juil. 2005
     */
    protected abstract void addToDiary(int level, Object o) throws Exception;

    /**
     * @param msg
     *            8 juil. 2005
     */
    protected abstract void log(String msg);

    /*
     * (non-Javadoc)
     * 
     * @see bensikin.logs.ILogger#close()
     */
    public abstract void close();

}
