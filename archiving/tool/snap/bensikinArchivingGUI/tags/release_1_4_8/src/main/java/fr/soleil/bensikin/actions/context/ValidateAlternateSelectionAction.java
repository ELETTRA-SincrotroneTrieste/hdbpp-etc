//+======================================================================
//$Source: /cvsroot/tango-cs/tango/tools/bensikin/bensikin/actions/context/ValidateAlternateSelectionAction.java,v $
//
//Project:      Tango Archiving Service
//
//Description:  Java source code for the class  SelectFavoriteContextAction.
//						(Claisse Laurent) - 16 juin 2005
//
//$Author: ounsy $
//
//$Revision: 1.2 $
//
//$Log: ValidateAlternateSelectionAction.java,v $
//Revision 1.2  2006/04/10 08:46:54  ounsy
//Bensikin action now all inherit from BensikinAction for easy rights management
//
//Revision 1.1  2005/12/14 16:50:44  ounsy
//added methods necessary for alternate attribute selection
//
//Revision 1.1.1.2  2005/08/22 11:58:34  chinkumo
//First commit
//
//
//copyleft :		Synchrotron SOLEIL
//					L'Orme des Merisiers
//					Saint-Aubin - BP 48
//					91192 GIF-sur-YVETTE CEDEX
//
//-======================================================================
package fr.soleil.bensikin.actions.context;

import java.awt.event.ActionEvent;

import javax.swing.Action;

import fr.soleil.bensikin.actions.BensikinAction;
import fr.soleil.bensikin.components.context.detail.AttributesSelectTable;
import fr.soleil.bensikin.containers.context.ContextDetailPanel;
import fr.soleil.bensikin.models.AttributesSelectTableModel;



/**
* Makes the selected (in favorites) context the current context.
* <UL>
* <LI> Loads the context with the selected id via Context.findContext
* <LI> Displays this context
* <LI> Logs the action's success or failure
* </UL>
*
* @author CLAISSE
*/
public class ValidateAlternateSelectionAction extends BensikinAction
{
	 /**
	  * Standard action constructor that sets the action's name.
	  *
	  * @param name The action name, which is also the context's id
	  */
	 public ValidateAlternateSelectionAction ( String name )
	 {
	     this.putValue( Action.NAME , name );
	 }
	
	 /* (non-Javadoc)
	  * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	  */
	 public void actionPerformed ( ActionEvent arg0 )
	 {
	     this.removeNotSelectedAttributes ();
	 }
	 
	 /**
     * 
     */
    private void removeNotSelectedAttributes() 
    {
        ContextDetailPanel editPanel = ContextDetailPanel.getInstance ();
        if ( ! editPanel.isAlternateSelectionMode () )
        {
            //nothing to do
            return;
        }
        
        AttributesSelectTableModel model = AttributesSelectTableModel.getInstance ();
        AttributesSelectTable table = AttributesSelectTable.getInstance ();
        
        table.applyChange ();
        model.removeNotSelectedRows ();
    }
}
