//+======================================================================
// $Source: /cvsroot/tango-cs/tango/tools/bensikin/bensikin/containers/sub/dialogs/BensikinErrorDialog.java,v $
//
// Project:      Tango Archiving Service
//
// Description:  Java source code for the class  BensikinErrorDialog.
//						(GIRARDOT Raphael) - nov. 2005
//
// $Author: ounsy $
//
// $Revision: 1.2 $
//
// $Log: BensikinErrorDialog.java,v $
// Revision 1.2  2005/12/14 16:27:29  ounsy
// minor changes
//
// Revision 1.1  2005/11/29 18:25:08  chinkumo
// no message
//
//
// copyleft :	Synchrotron SOLEIL
//					L'Orme des Merisiers
//					Saint-Aubin - BP 48
//					91192 GIF-sur-YVETTE CEDEX
//
//-======================================================================
package fr.soleil.bensikin.containers.sub.dialogs;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

import fr.soleil.bensikin.tools.SpringUtilities;


/**
 * @author SOLEIL
 */
public class BensikinErrorDialog extends JDialog
{
    String message;

    /**
     * Constructor of the error dialog. No parent.
     * @param title title of the dialog
     * @param message message of the dialog
     */
    public BensikinErrorDialog ( String title , String message )
    {
        super( ( Frame ) null , title , true );
        this.message = message;
        initialize();
    }
    /**
     * Constructor of the error dialog. Frame as parent.
     * @param frame the parent frame
     * @param title title of the dialog
     * @param message message of the dialog
     */
    public BensikinErrorDialog ( Frame frame, String title , String message )
    {
        super( frame , title , true );
        this.message = message;
        initialize();
    }
    /**
     * Constructor of the error dialog. JDialog as parent.
     * @param dialog the parent JDialog
     * @param title title of the dialog
     * @param message message of the dialog
     */
    public BensikinErrorDialog ( JDialog dialog, String title , String message )
    {
        super( dialog , title , true );
        this.message = message;
        initialize();
    }
    
    private void initialize() {
        int nbComponents = 0;
        String [] messages = message.split("\n");
        JPanel panel = new JPanel();
        panel.setLayout(new SpringLayout());

        JLabel[] fields = new JLabel[ messages.length ];
        this.setContentPane( panel );
        for ( nbComponents = 0 ; nbComponents < messages.length ; nbComponents++ )
        {
            JLabel label = new JLabel( messages[ nbComponents ] ,
                                       JLabel.CENTER );
            fields[ nbComponents ] = label;
            panel.add( fields[ nbComponents ] );
        }
        JButton ok = new JButton( "ok" );
        ok.setOpaque(false);
        ok.addActionListener( new BensikinErrorDialogListener( this ) );
        panel.add( ok );
        nbComponents++;
        SpringUtilities.makeCompactGrid( panel ,
                nbComponents , 1 , // rows, cols
                0 , 0 , // initX, initY
                0 , 0 , // xPad, yPad
                true );       // every component same size
        Dimension size = panel.getPreferredSize();
        size.height += 30;
        size.width += 10;
        this.setSize( size );
        panel.setBackground(new Color(255,255,150));
        panel.setOpaque(true);
        panel.repaint();
        this.repaint();
    }

}

class BensikinErrorDialogListener implements ActionListener
{

    private BensikinErrorDialog dialog;

    public BensikinErrorDialogListener ( BensikinErrorDialog error )
    {
        dialog = error;
    }

    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed ( ActionEvent arg0 )
    {
        dialog.dispose();
        dialog.setVisible( false );
    }

}