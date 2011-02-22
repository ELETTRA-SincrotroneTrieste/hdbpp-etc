
package fr.soleil.mambo.models;

import java.sql.Date;

import javax.swing.table.DefaultTableModel;

import fr.esrf.TangoDs.TangoConst;
import fr.soleil.commonarchivingapi.ArchivingTools.Tools.DbData;
import fr.soleil.mambo.tools.Messages;

public class ViewStringStateBooleanSpectrumTableModel extends DefaultTableModel
{

    DbData                             data = null;
    private java.text.SimpleDateFormat genFormatUS;
    int                                data_type;

    public ViewStringStateBooleanSpectrumTableModel (DbData data, int data_type)
    {
        super();
        this.genFormatUS = new java.text.SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss.SSS" );
        this.data_type = data_type;
        if ( data_type != TangoConst.Tango_DEV_STRING
                && data_type != TangoConst.Tango_DEV_STATE
                && data_type != TangoConst.Tango_DEV_BOOLEAN ) return;
        this.data = data;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getColumnCount()
     */
    public int getColumnCount ()
    {
        int col = 0;
        if ( data != null )
        {
            if ( data.getMax_x() > col )
            {
                col = data.getMax_x() + 1;
            }
        }
        return col;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getRowCount()
     */
    public int getRowCount ()
    {
        int length = 0;
        if ( data != null )
        {
            length = data.getData_timed().length;
        }
        return length;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getValueAt(int, int)
     */
    public Object getValueAt (int rowIndex, int columnIndex)
    {
        if ( columnIndex == 0 )
        {
            if ( data != null  && data.getData_timed() != null)
            {
                return genFormatUS.format( new Date( data.getData_timed()[rowIndex].time.longValue() ) );
            }
            return null;
        }
        else
        {
            if ( data != null )
            {
                switch (data_type)
                {
                    case TangoConst.Tango_DEV_STRING:
                    case TangoConst.Tango_DEV_STATE:
                    case TangoConst.Tango_DEV_BOOLEAN:
                        if ( data.getData_timed() != null && data.getData_timed()[rowIndex].value != null && columnIndex - 1 < data.getData_timed()[rowIndex].value.length )
                        {
                            return data.getData_timed()[rowIndex].value[columnIndex - 1];
                        }
                        break;
                    default:
                        return null;
                }
            }
            else
            {
                switch (data_type)
                {
                    case TangoConst.Tango_DEV_STRING:
                    case TangoConst.Tango_DEV_STATE:
                    case TangoConst.Tango_DEV_BOOLEAN:
                    default:
                        return null;
                }
            }
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#getColumnName(int)
     */
    public String getColumnName (int column)
    {
        switch (column)
        {
            case 0:
                return Messages.getMessage( "VIEW_SPECTRUM_DATE" );
            default:
                return "" + column;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see javax.swing.table.TableModel#isCellEditable(int, int)
     */
    public boolean isCellEditable (int rowIndex, int columnIndex)
    {
        return false;
    }

    public void clearData()
    {
        data = null;
        genFormatUS = null;
    }

}
