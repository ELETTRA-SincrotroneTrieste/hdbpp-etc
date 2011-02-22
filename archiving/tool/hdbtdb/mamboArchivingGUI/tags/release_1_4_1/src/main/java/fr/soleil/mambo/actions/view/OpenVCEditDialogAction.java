//+======================================================================
// $Source: /cvsroot/tango-cs/tango/tools/mambo/actions/view/OpenVCEditDialogAction.java,v $
//
// Project:      Tango Archiving Service
//
// Description:  Java source code for the class  OpenVCEditDialogAction.
//						(Claisse Laurent) - 5 juil. 2005
//
// $Author: ounsy $
//
// $Revision: 1.14 $
//
// $Log: OpenVCEditDialogAction.java,v $
// Revision 1.14  2007/08/24 12:51:43  ounsy
// WaitingDialog should allways close now
//
// Revision 1.13  2007/08/24 09:23:00  ounsy
// Color index reset on new VC (Mantis bug 5210 part1)
//
// Revision 1.12  2007/05/10 14:48:16  ounsy
// possibility to change "no value" String in chart data file (default is "*") through vc option
//
// Revision 1.11  2007/01/11 14:05:46  ounsy
// Math Expressions Management (warning ! requires atk 2.7.0 or greater)
//
// Revision 1.10  2006/12/06 12:35:13  ounsy
// better error and waiting dialog management
//
// Revision 1.9  2006/08/29 14:04:06  ounsy
// waiting dialog becomes static
//
// Revision 1.8  2006/07/18 10:23:16  ounsy
// Less time consuming by setting tree expanding on demand only
//
// Revision 1.7  2006/06/28 12:25:47  ounsy
// do not expand VCPossibleAttributesTree
//
// Revision 1.6  2006/05/15 09:24:56  ounsy
// waiting dialog added
//
// Revision 1.5  2006/04/05 13:41:54  ounsy
// small bug correction : does not reduce max stack in case of cancel after new
//
// Revision 1.4  2006/01/24 12:51:02  ounsy
// Bug of the new VC replacing the former selected VC corrected
//
// Revision 1.3  2005/12/15 10:49:49  ounsy
// minor changes
//
// Revision 1.2  2005/11/29 18:27:07  chinkumo
// no message
//
// Revision 1.1.2.3  2005/09/26 07:52:25  chinkumo
// Miscellaneous changes...
//
// Revision 1.1.2.2  2005/09/14 15:41:20  chinkumo
// Second commit !
//
//
// copyleft :	Synchrotron SOLEIL
//					L'Orme des Merisiers
//					Saint-Aubin - BP 48
//					91192 GIF-sur-YVETTE CEDEX
//
//-======================================================================
package fr.soleil.mambo.actions.view;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import fr.soleil.mambo.components.view.OpenedVCComboBox;
import fr.soleil.mambo.containers.sub.dialogs.WaitingDialog;
import fr.soleil.mambo.containers.view.dialogs.AttributesPlotPropertiesTab;
import fr.soleil.mambo.containers.view.dialogs.ExpressionTab;
import fr.soleil.mambo.containers.view.dialogs.VCEditDialog;
import fr.soleil.mambo.data.view.ViewConfiguration;
import fr.soleil.mambo.models.VCPossibleAttributesTreeModel;


public class OpenVCEditDialogAction extends AbstractAction 
{
    private boolean isNew;
    
    /**
     * @param name
     */
    public OpenVCEditDialogAction ( String name , boolean _isNew )
    {
        super.putValue ( Action.NAME , name  );
        super.putValue ( Action.SHORT_DESCRIPTION , name  );
        
        this.isNew = _isNew;
    }
    
    /* (non-Javadoc)
     * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    public void actionPerformed ( ActionEvent actionEvent ) 
    {
        System.gc();
        ViewConfiguration selectedVC = ViewConfiguration.getSelectedViewConfiguration ();

        WaitingDialog.openInstance();

        try
        {
            boolean needsNewVc = this.isNew || selectedVC == null;
            boolean needsStackCleaning = false;
            ViewConfiguration VCToPush = needsNewVc ? new ViewConfiguration () : selectedVC;
            if (needsNewVc) {
                needsStackCleaning = ( OpenedVCComboBox.getInstance().getStackSize() == OpenedVCComboBox.getInstance().getMaxSize() );
                if (needsStackCleaning)
                {
                    OpenedVCComboBox.getInstance().setMaxSize(OpenedVCComboBox.getInstance().getMaxSize() + 1);
                }
                VCToPush.push ();
                AttributesPlotPropertiesTab.getInstance().getPropertiesPanel().reset();
            }
            else {
                VCToPush.pushLight ();
            }
            
            VCPossibleAttributesTreeModel model = VCPossibleAttributesTreeModel.getInstance();
            model.reload();
            VCEditDialog dialog = VCEditDialog.getInstance ();
            dialog.setNewVC(needsNewVc);
            ExpressionTab.getInstance().reset();

            dialog.resetTabbedPane();
            WaitingDialog.closeInstance();
            dialog.setVisible(true);
            if (needsStackCleaning)
            {
                OpenedVCComboBox.getInstance().setMaxSize(OpenedVCComboBox.getInstance().getMaxSize() - 1);
            }
        }
        catch(Throwable t)
        {
            t.printStackTrace();
            WaitingDialog.closeInstance();
        }
        System.gc();
    }

}
