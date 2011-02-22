package TdbArchiver.Collector.spectrum;

import TdbArchiver.Collector.TdbCollector;
import TdbArchiver.Collector.TdbModeHandler;
import TdbArchiver.Collector.Tools.FileTools;
import fr.esrf.Tango.ErrSeverity;
import fr.esrf.TangoDs.Util;
import fr.esrf.tangoatk.core.AttributeStateEvent;
import fr.esrf.tangoatk.core.ConnectionException;
import fr.esrf.tangoatk.core.ErrorEvent;
import fr.esrf.tangoatk.core.IStringSpectrum;
import fr.esrf.tangoatk.core.IStringSpectrumListener;
import fr.esrf.tangoatk.core.StringSpectrumEvent;
import fr.soleil.commonarchivingapi.ArchivingTools.Diary.ILogger;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.GlobalConst;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ArchivingException;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.AttributeLightMode;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.SpectrumEvent_RW;

public class StringSpectrum_RW extends TdbCollector implements
		IStringSpectrumListener {
	/**
	 *
	 */
	private static final long serialVersionUID = -2205892304287017636L;

	public StringSpectrum_RW(TdbModeHandler _modeHandler, String currentDsPath,
			String currentDbPath) {
		super(_modeHandler, currentDsPath, currentDbPath);
	}

	 public void addSource(AttributeLightMode attributeLightMode)
			throws ArchivingException {
		try {
			synchronized (attributeList) {
				/*
				 * while ( ( IStringSpectrum )
				 * attributeList.get(attributeLightMode
				 * .getAttribute_complete_name()) == null ) {
				 */
				IStringSpectrum attribute = (IStringSpectrum) attributeList
						.add(attributeLightMode.getAttribute_complete_name());
				attribute.addListener(this);
				attribute.addErrorListener(this);
				Util.out4.println("\t The attribute named "
						+ attributeLightMode.getAttribute_complete_name()
						+ " was hired to the Collector list...");
				String table_name = super.dbProxy
						.getDataBase()
						.getDbUtil()
						.getTableName(
								attributeLightMode.getAttribute_complete_name());
				FileTools myFile = new FileTools(table_name, attributeLightMode
						.getData_format(), attributeLightMode.getWritable(),
						attributeLightMode.getMode().getTdbSpec()
								.getExportPeriod(), super.m_logger, true,
						super.dbProxy, super.m_currentDsPath,
						super.m_currentDbPath);
				myFile.initialize();
				filesNames.put(attributeLightMode.getAttribute_complete_name(),
						myFile);

				if (attributeList.size() == 1) {
					startCollecting();
				}
				// }
				if (attributeList.get(attributeLightMode
						.getAttribute_complete_name()) == null) {
					super.m_logger.trace(ILogger.LEVEL_WARNING,
							"addSource/The first add test failed for attribute|"
									+ attributeLightMode
											.getAttribute_complete_name());
				}
			}
		} catch (ConnectionException e) {
			super.m_logger.trace(ILogger.LEVEL_WARNING, e);

			String message = GlobalConst.ARCHIVING_ERROR_PREFIX + " : "
					+ "Failed adding '"
					+ attributeLightMode.getAttribute_complete_name()
					+ "' as source";
			String reason = GlobalConst.TANGO_COMM_EXCEPTION;
			String desc = "Failed while executing StringSpectrum_RW.addSource() method...";
			throw new ArchivingException(message, reason, ErrSeverity.WARN,
					desc, "", e);
		} catch (Exception e) {
			super.m_logger.trace(ILogger.LEVEL_WARNING,
					"Unexpected exception during addSource:");
			super.m_logger.trace(ILogger.LEVEL_WARNING, e);
		}
	}

	synchronized public void removeSource(String attributeName)
			throws ArchivingException {
		// System.out.println("StringSpectrum_RW.removeSource");
		try {
			synchronized (attributeList) {
				/*
				 * while ( ( IStringSpectrum ) attributeList.get(attributeName)
				 * != null ) {
				 */
				IStringSpectrum attribute = (IStringSpectrum) attributeList
						.get(attributeName);
				if (attribute != null) {
					attribute.removeListener(this);
					attribute.removeErrorListener(this);

					this.attributeList.remove(attributeName);
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
			String desc = "Failed while executing StringSpectrum_RW.removeSource() method...";
			throw new ArchivingException(message, reason, ErrSeverity.WARN,
					desc, "", e);
		}
	}

	public void errorChange(ErrorEvent errorEvent) {
		int tryNumber = DEFAULT_TRY_NUMBER;
		Util.out3.println("StringSpectrum_RW.errorChange : "
				+ "Unable to read the attribute named "
				+ errorEvent.getSource().toString());
		String[] value = null;
		SpectrumEvent_RW spectrumEvent_rw = new SpectrumEvent_RW();
		spectrumEvent_rw
				.setAttribute_complete_name(((IStringSpectrum) errorEvent
						.getSource()).getName());
		spectrumEvent_rw.setTimeStamp(errorEvent.getTimeStamp());
		spectrumEvent_rw.setValue(value);
		processEventSpectrum(spectrumEvent_rw, tryNumber);
	}

	public void stringSpectrumChange(StringSpectrumEvent event) {
		int tryNumber = DEFAULT_TRY_NUMBER;
		String[] value = event.getValue();
		SpectrumEvent_RW spectrumEvent_rw = new SpectrumEvent_RW();
		spectrumEvent_rw.setAttribute_complete_name(((IStringSpectrum) event
				.getSource()).getName());
		spectrumEvent_rw.setDim_x(((IStringSpectrum) event.getSource())
				.getXDimension());
		spectrumEvent_rw.setTimeStamp(event.getTimeStamp());
		spectrumEvent_rw.setValue(value);

		processEventSpectrum(spectrumEvent_rw, tryNumber);

	}

	public void stateChange(AttributeStateEvent event) {
	}

	public void processEventSpectrum(SpectrumEvent_RW spectrumEvent_rw,
			int try_number) {
		Util.out4.println("StringSpectrum_RW.processEventSpectrum");

		boolean timeCondition = super
				.isDataArchivableTimestampWise(spectrumEvent_rw);
		if (!timeCondition) {
			return;
		}

		try {
			((FileTools) filesNames.get(spectrumEvent_rw
					.getAttribute_complete_name()))
					.processEventSpectrum(spectrumEvent_rw);
			super.setLastTimestamp(spectrumEvent_rw);
		} catch (Exception e) {
			Util.out2.println("ERROR !! " + "\r\n" + "\t Origin : \t "
					+ "StringSpectrum_RW.processEventSpectrum" + "\r\n"
					+ "\t Reason : \t " + e.getClass().getName() + "\r\n"
					+ "\t Description : \t " + e.getMessage() + "\r\n"
					+ "\t Additional information : \t " + "" + "\r\n");
			e.printStackTrace();
		}
		checkGC();
	}
}
