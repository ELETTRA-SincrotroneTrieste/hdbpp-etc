// +======================================================================
// $Source:
// /cvsroot/tango-cs/tango/tools/mambo/actions/view/listeners/DateRangeComboBoxListener.java,v
// $
//
// Project: Tango Archiving Service
//
// Description: Java source code for the class DateRangeComboBoxListener.
// (Claisse Laurent) - 5 juil. 2005
//
// $Author$
//
// $Revision$
//
// $Log$
// Revision 1.1 2009/11/24 17:22:47 soleilarc
// * preparing multi view
//
// Revision 1.3 2008/04/09 10:45:46 achouri
// no message
//
// Revision 1.2 2005/11/29 18:27:07 chinkumo
// no message
//
// Revision 1.1.2.2 2005/09/14 15:41:20 chinkumo
// Second commit !
//
//
// copyleft : Synchrotron SOLEIL
// L'Orme des Merisiers
// Saint-Aubin - BP 48
// 91192 GIF-sur-YVETTE CEDEX
//
// -======================================================================
package fr.soleil.mambo.actions.view.listeners;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.Timestamp;
import java.util.Hashtable;

import fr.soleil.mambo.containers.view.dialogs.DateRangeBox;
import fr.soleil.mambo.tools.Messages;

public class DateRangeComboBoxInnerListener implements ActionListener,
        ItemListener {

    static final long               DAY              = 86400000;
    static final long               HOUR             = 3600000;

    private String                  defaultSelection = "---";
    private String                  last1h;
    private String                  last4h;
    private String                  last8h;
    private String                  last1d;
    private String                  last3d;
    private String                  last7d;
    private String                  last30d;

    private Hashtable<String, Long> corresp;
    private DateRangeBox            dateRangeBox;

    /**
     * 
     */
    public DateRangeComboBoxInnerListener(DateRangeBox dateRangeBox) {
        super();

        last1h = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_1H");
        last4h = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_4H");
        last8h = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_8H");
        last1d = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_1D");
        last3d = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_3D");
        last7d = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_7D");
        last30d = Messages
                .getMessage("DIALOGS_EDIT_VC_GENERAL_DATES_RANGE_LAST_30D");

        corresp = new Hashtable<String, Long>();
        corresp.put(last1h, new Long(1 * HOUR));
        corresp.put(last4h, new Long(4 * HOUR));
        corresp.put(last8h, new Long(8 * HOUR));
        corresp.put(last1d, new Long(1 * DAY));
        corresp.put(last3d, new Long(3 * DAY));
        corresp.put(last7d, new Long(7 * DAY));
        corresp.put(last30d, new Long(30 * DAY));

        this.dateRangeBox = dateRangeBox;
    }

    @Override
    public void actionPerformed(ActionEvent event) {
        updateDateRange((String) dateRangeBox.getDateRangeComboBox()
                .getSelectedItem());
    }

    @Override
    public void itemStateChanged(ItemEvent event) {
        if (event.getStateChange() == ItemEvent.SELECTED) {
            updateDateRange((String) event.getItem());
        }
    }

    private void updateDateRange(String selected) {
        if (selected != null) {
            if (defaultSelection.equals(selected)) {
                dateRangeBox.setStartDate("");
                dateRangeBox.setEndDate("");
            }
            else {
                long now = System.currentTimeMillis();
                long endDate_l = now;
                Long delta = corresp.get(selected);
                long delta_l = delta.longValue();
                long startDate_l = now - delta_l;
                Timestamp startDate = new Timestamp(startDate_l);
                Timestamp endDate = new Timestamp(endDate_l);
                dateRangeBox.setStartDate(startDate.toString());
                dateRangeBox.setEndDate(endDate.toString());
                // Removes the listener before updating selection
                // --> no loop risk
                dateRangeBox.setDateRangeComboBoxSelectedItem(selected);
            }
        }
    }

    /**
     * @return Returns the last1d.
     */
    public String getLast1d() {
        return last1d;
    }

    /**
     * @return Returns the last30d.
     */
    public String getLast30d() {
        return last30d;
    }

    /**
     * @return Returns the last3d.
     */
    public String getLast3d() {
        return last3d;
    }

    /**
     * @return Returns the last1h.
     */
    public String getLast1h() {
        return last1h;
    }

    /**
     * @return Returns the last4h.
     */
    public String getLast4h() {
        return last4h;
    }

    /**
     * @return Returns the last7d.
     */
    public String getLast7d() {
        return last7d;
    }

    /**
     * @return Returns the last8h.
     */
    public String getLast8h() {
        return last8h;
    }

    /**
     * @return Returns the defaultSelection.
     */
    public String getDefaultSelection() {
        return defaultSelection;
    }

}
