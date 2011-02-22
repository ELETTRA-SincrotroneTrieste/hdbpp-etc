/*	Synchrotron Soleil
 *
 *   File          :  StringScalar.java
 *
 *   Project       :  TdbArchiver_CVS
 *
 *   Description   :
 *
 *   Author        :  SOLEIL
 *
 *   Original      :  28 f�vr. 2006
 *
 *   Revision:  					Author:
 *   Date: 							State:
 *
 *   Log: StringScalar.java,v
 *
 */
package TdbArchiver.Collector.scalar;

import java.util.Hashtable;

import Common.Archiver.Collector.ModesCounters;
import TdbArchiver.Collector.TdbCollector;
import TdbArchiver.Collector.TdbModeHandler;
import TdbArchiver.Collector.Tools.FileTools;
import fr.esrf.Tango.DevFailed;
import fr.esrf.Tango.ErrSeverity;
import fr.esrf.TangoDs.TangoConst;
import fr.esrf.TangoDs.Util;
import fr.esrf.tangoatk.core.AttributeStateEvent;
import fr.esrf.tangoatk.core.ConnectionException;
import fr.esrf.tangoatk.core.ErrorEvent;
import fr.esrf.tangoatk.core.IStringScalar;
import fr.esrf.tangoatk.core.IStringScalarListener;
import fr.esrf.tangoatk.core.StringScalarEvent;
import fr.soleil.commonarchivingapi.ArchivingTools.Diary.ILogger;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.GlobalConst;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ArchivingException;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.AttributeLightMode;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ScalarEvent;

public abstract class StringScalar extends TdbCollector implements
		IStringScalarListener {

	/**
	 * This Hashtable is used to store the last value of an attribute. This
	 * value will be compared to the current one (cf. Modes)
	 */
	protected Hashtable lastValueHashtable;

	public StringScalar(TdbModeHandler _modeHandler, String currentDsPath,
			String currentDbPath) {
		super(_modeHandler, currentDsPath, currentDbPath);
		lastValueHashtable = new Hashtable();
	}

	public synchronized void addSource(AttributeLightMode attributeLightMode)
			throws ArchivingException {
		try {
			synchronized (attributeList) {
				/*
				 * while ( ( IStringScalar )
				 * attributeList.get(attributeLightMode
				 * .getAttribute_complete_name()) == null ) {
				 */
				IStringScalar attribute = null;
				String att_name = attributeLightMode
						.getAttribute_complete_name();
				attribute = (IStringScalar) attributeList.add(att_name);

				attribute.addStringScalarListener(this);
				attribute.addErrorListener(this);
				Util.out4.println("\t The attribute named " + att_name
						+ " was hired to the Collector list...");
				// informs the mother class that one new attribute must be
				// managed
				addAttribute(att_name);

				String table_name = super.dbProxy.getDataBase().getDbUtil()
						.getTableName(att_name);
				FileTools myFile = new FileTools(table_name, attributeLightMode
						.getData_format(), attributeLightMode.getWritable(),
						attributeLightMode.getMode().getTdbSpec()
								.getExportPeriod(), super.m_logger, true,
						super.dbProxy, super.m_currentDsPath,
						super.m_currentDbPath);
				myFile.initialize();
				filesNames.put(att_name, myFile);
				// Verify that the recording file exists

				if (attributeList.size() == 1) {
					startCollecting();
				}
				// }
				if (attributeList.get(att_name) == null) {
					super.m_logger.trace(ILogger.LEVEL_WARNING,
							"addSource/The first add test failed for attribute|"
									+ att_name);
				}
			}
		} catch (ConnectionException e) {
			super.m_logger.trace(ILogger.LEVEL_WARNING, e);

			String message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : "
					+ "Failed adding '"
					+ attributeLightMode.getAttribute_complete_name()
					+ "' as source";
			String reason = GlobalConst.TANGO_COMM_EXCEPTION;
			String desc = "Failed while executing StringScalar.addSource() method...";
			throw new ArchivingException(message, reason, ErrSeverity.WARN,
					desc, "", e);
		} catch (Exception e) {
			super.m_logger.trace(ILogger.LEVEL_WARNING,
					"Unexpected exception during addSource:");
			super.m_logger.trace(ILogger.LEVEL_WARNING, e);
		}
	}

	public synchronized void removeSource(String attributeName)
			throws ArchivingException {
		Util.out2.println("StringScalar.removeSource");
		try {
			synchronized (attributeList) {
				/*
				 * while ( ( IStringScalar ) attributeList.get(attributeName) !=
				 * null ) {
				 */
				IStringScalar attribute = (IStringScalar) attributeList
						.get(attributeName);

				if (attribute != null) {
					attribute.removeStringScalarListener(this);
					attribute.removeErrorListener(this);
					attributeList.remove(attributeName);
					// informs the mother class that one new attribute must be
					// removed
					removeAttribute(attributeName);
					Util.out4.println("\t The attribute named " + attributeName
							+ " was fired from the Collector list...");
					((FileTools) filesNames.get(attributeName)).closeFile(true);
					filesNames.remove(attributeName);
					if (attributeList.isEmpty()) {
						stopCollecting();
					}
				}
			}
		} catch (Exception e) {
			String message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : "
					+ "Failed removing '" + attributeName + "' from sources";
			String reason = GlobalConst.TANGO_COMM_EXCEPTION;
			String desc = "Failed while executing StringScalar.removeSource() method...";
			throw new ArchivingException(message, reason, ErrSeverity.WARN,
					desc, "", e);
		}
	}

	public void stateChange(AttributeStateEvent e) {
	}

	public void processEventScalar(ScalarEvent scalarEvent, int try_number) {
		boolean timeCondition = super
				.isDataArchivableTimestampWise(scalarEvent);
		if (!timeCondition) {
			return;
		}

		try {
			boolean doArchive = false;
			if (this.isFirstValue) {
				doArchive = true;
				this.isFirstValue = false;
			} else {
				/*
				 * if ( scalarEvent.getReadValue() == null ) {
				 * super.m_logger.trace(ILogger.LEVEL_WARNING,
				 * "StringScalar/processEventScalar/scalarEvent.getReadValue() == null/for attribute/"
				 * +scalarEvent.getAttribute_complete_name()); } if (
				 * getLastValue(scalarEvent) == null ) {
				 * super.m_logger.trace(ILogger.LEVEL_WARNING,
				 * "StringScalar/processEventScalar/getLastValue(scalarEvent) == null/for attribute/"
				 * +scalarEvent.getAttribute_complete_name()); }
				 */

				ModesCounters mc = getModeCounter(scalarEvent
						.getAttribute_complete_name());
				if (mc == null)
					this.m_logger.trace(ILogger.LEVEL_ERROR,
							"Attribute Counters unknown");
				else
					doArchive = doArchiveEvent(mc, scalarEvent.getData_type(),
							scalarEvent.getReadValue(),
							getLastValue(scalarEvent), scalarEvent
									.getAttribute_complete_name());
			}

			if (doArchive) {
				((FileTools) filesNames.get(scalarEvent
						.getAttribute_complete_name()))
						.processEventScalar(scalarEvent);
			}
			/*
			 * if ( scalarEvent.getReadValue() != null &&
			 * getLastValue(scalarEvent) != null ) { boolean valueCondition =
			 * modeHandler.isDataArchivable(scalarEvent.getData_type() ,
			 * scalarEvent.getReadValue() , getLastValue(scalarEvent));
			 *
			 * if ( valueCondition ) { ( ( FileTools )
			 * filesNames.get(scalarEvent.getAttribute_complete_name())
			 * ).processEventScalar(scalarEvent); } } else { ( ( FileTools )
			 * filesNames.get(scalarEvent.getAttribute_complete_name())
			 * ).processEventScalar(scalarEvent); }
			 */

			setLastValue(scalarEvent, scalarEvent.getReadValue());
		} catch (Exception e) {
			try_number--;
			if (try_number > 0) {
				Util.out2
						.println("StringScalar.processEventScalar : \r\n\ttry "
								+ (try_number) + "failed...");
				processEventScalar(scalarEvent, try_number);
			}
		}
		checkGC();
	}

	protected void setLastValue(ScalarEvent scalarEvent, Object lastValue) {
		super.setLastTimestamp(scalarEvent);
		if (lastValue != null) {
			lastValueHashtable.put(scalarEvent.getAttribute_complete_name(),
					lastValue);
		}
	}

	protected Object getLastValue(ScalarEvent scalarEvent) {
		return lastValueHashtable.get(scalarEvent.getAttribute_complete_name());
	}

	protected void printException(String cause, int cause_value, String name,
			DevFailed devFailed) {
		String message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : " + cause;
		String reason = "Failed while executing StringScalar.stringScalarChange()...";
		String desc = (cause_value != -1) ? cause + " (" + cause_value
				+ ") not supported !! [" + name + "]"
				: "Unable to retrieve the attribute data type";
		Util.out2.println((devFailed == null) ? (new ArchivingException(
				message, reason, ErrSeverity.PANIC, desc, "").toString())
				: (new ArchivingException(message, reason, ErrSeverity.PANIC,
						desc, "", devFailed)).toString());
	}

	public abstract void stringScalarChange(StringScalarEvent e);

	protected abstract int getWritableValue();

	public void errorChange(ErrorEvent errorEvent) {
		int tryNumber = DEFAULT_TRY_NUMBER;

		String errorMess = this.getClass().getSimpleName()
				+ ".errorChange : Unable to read the attribute named "
				+ errorEvent.getSource().toString();
		Util.out3.println(errorMess);
		super.m_logger.trace(ILogger.LEVEL_ERROR, errorMess);

		try {
			processEventScalar(getNullValueScalarEvent(errorEvent,
					TangoConst.Tango_DEV_STRING, getWritableValue()), tryNumber);
		} catch (Exception e) {
			super.m_logger
					.trace(
							ILogger.LEVEL_ERROR,
							this.getClass().getSimpleName()
									+ ".errorChange : during processEventScalar creation execp : "
									+ e);
			e.printStackTrace();
		}
	}
}
