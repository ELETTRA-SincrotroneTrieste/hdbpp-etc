/*	Synchrotron Soleil
 *
 *   File          :  StateScalar_RO.java
 *
 *   Project       :  TdbArchiver_CVS
 *
 *   Description   :
 *
 *   Author        :  SOLEIL
 *
 *   Original      :  10 mars 2006
 *
 *   Revision:  					Author:
 *   Date: 							State:
 *
 *   Log: StateScalar_RO.java,v
 *
 */
package TdbArchiver.Collector.scalar;

import TdbArchiver.Collector.TdbModeHandler;
import fr.esrf.Tango.AttrDataFormat;
import fr.esrf.Tango.AttrWriteType;
import fr.esrf.Tango.DevFailed;
import fr.esrf.tangoatk.core.DevStateScalarEvent;
import fr.esrf.tangoatk.core.IDevStateScalar;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.GlobalConst;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.TangoStateTranslation;
import fr.soleil.hdbtdbArchivingApi.ArchivingTools.Tools.ScalarEvent;

public class StateScalar_RO extends StateScalar {

	public StateScalar_RO(TdbModeHandler _modeHandler, String currentDsPath,
			String currentDbPath) {
		super(_modeHandler, currentDsPath, currentDbPath);
	}

	public void devStateScalarChange(DevStateScalarEvent event) {
		int tryNumber = DEFAULT_TRY_NUMBER;
		ScalarEvent scalarEvent = new ScalarEvent();
		scalarEvent.setAttribute_complete_name(((IDevStateScalar) event
				.getSource()).getName());

		try {
			scalarEvent.setData_format(AttrDataFormat._SCALAR);
			scalarEvent.setWritable(AttrWriteType._READ);
			scalarEvent.setData_type(((IDevStateScalar) event.getSource())
					.getAttribute().getType());

			scalarEvent.setTimeStamp(event.getTimeStamp());

			String value = ((IDevStateScalar) event.getSource()).getValue();
			scalarEvent.setValue(TangoStateTranslation.getTangoDevState(value));
			processEventScalar(scalarEvent, tryNumber);

		} catch (DevFailed devFailed) {
			printException(GlobalConst.DATA_TYPE_EXCEPTION,
					AttrDataFormat._SCALAR, scalarEvent
							.getAttribute_complete_name(), devFailed);
		}
	}

	protected int getWritableValue() {
		return AttrWriteType._READ;
	}
	/*
	 * public void errorChange(ErrorEvent errorEvent) { int tryNumber =
	 * DEFAULT_TRY_NUMBER; Util.out3.println("StateScalar_RO.errorChange : " +
	 * "Unable to read the attribute named " + ( (IDevStateScalar)
	 * errorEvent.getSource() ).getName()); Object value = null; ScalarEvent
	 * scalarEvent = new ScalarEvent(); scalarEvent.setAttribute_complete_name(
	 * ((IDevStateScalar)errorEvent.getSource()).getName() );
	 * scalarEvent.setWritable(AttrWriteType._READ);
	 * scalarEvent.setTimeStamp(errorEvent.getTimeStamp());
	 * scalarEvent.setValue(value); processEventScalar(scalarEvent , tryNumber);
	 * }
	 */
}
