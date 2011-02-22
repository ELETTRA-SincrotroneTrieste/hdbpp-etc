package fr.soleil.bensikin.components.context.detail;

import javax.swing.JTable;

import fr.soleil.bensikin.models.ContextDetailPrintTableModel;
import fr.soleil.bensikin.tools.GUIUtilities;

public class ContextDetailPrintTable extends JTable {

    public ContextDetailPrintTable () {
        super();
        this.setAutoCreateColumnsFromModel(true);
        this.setRowHeight(20);
        this.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        this.setModel( new ContextDetailPrintTableModel() );
    }

    public String toUserFriendlyString() {
        StringBuffer buffer = new StringBuffer();
        int rowCount = getModel().getRowCount();
        int columnCount = getModel().getColumnCount();
        for (int row = 0; row < rowCount; row++) {
            for (int column = 1; column < columnCount; column++) {
                buffer.append( getModel().getValueAt(row, column) );
                if (column < columnCount - 1) {
                    buffer.append(GUIUtilities.TANGO_DELIM);
                }
            }
            if (row < rowCount - 1) {
                buffer.append(GUIUtilities.CRLF);
            }
        }
        return buffer.toString();
    }

}
