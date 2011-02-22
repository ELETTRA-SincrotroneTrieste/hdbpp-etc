// +======================================================================
// $Source:
// /cvsroot/tango-cs/tango/tools/mambo/actions/view/MatchVCAttributesAction.java,v
// $
//
// Project: Tango Archiving Service
//
// Description: Java source code for the class MatchVCAttributesAction.
// (Claisse Laurent) - 5 juil. 2005
//
// $Author: ounsy $
//
// $Revision: 1.6 $
//
// $Log: MatchVCAttributesAction.java,v $
// Revision 1.6 2006/09/22 09:34:41 ounsy
// refactoring du package mambo.datasources.db
//
// Revision 1.5 2006/08/07 13:03:07 ounsy
// trees and lists sort
//
// Revision 1.4 2006/07/18 10:23:16 ounsy
// Less time consuming by setting tree expanding on demand only
//
// Revision 1.3 2006/05/19 15:03:05 ounsy
// minor changes
//
// Revision 1.2 2005/11/29 18:27:07 chinkumo
// no message
//
// Revision 1.1.2.3 2005/09/15 10:30:05 chinkumo
// Third commit !
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
package fr.soleil.mambo.actions.view;

import java.awt.event.ActionEvent;
import java.util.TreeMap;

import fr.soleil.commonarchivingapi.ArchivingTools.Tools.Criterions;
import fr.soleil.mambo.actions.MatchCAttributesAction;
import fr.soleil.mambo.bean.view.ViewConfigurationBean;
import fr.soleil.mambo.containers.view.dialogs.AttributesTab;
import fr.soleil.mambo.data.view.ViewConfiguration;
import fr.soleil.mambo.data.view.ViewConfigurationAttribute;
import fr.soleil.mambo.datasources.db.attributes.AttributeManagerFactory;
import fr.soleil.mambo.datasources.db.attributes.IAttributeManager;
import fr.soleil.mambo.models.VCAttributesTreeModel;

public class MatchVCAttributesAction extends MatchCAttributesAction {

    private static final long     serialVersionUID = -7491682181284346624L;
    private ViewConfigurationBean viewConfigurationBean;
    private AttributesTab         attributesTab;

    /**
     * @param name
     */
    public MatchVCAttributesAction(String name,
            ViewConfigurationBean viewConfigurationBean,
            AttributesTab attributesTab) {
        super(name);
        this.viewConfigurationBean = viewConfigurationBean;
        this.attributesTab = attributesTab;
    }

    @Override
    public void actionPerformed(ActionEvent arg0) {
        String pattern = attributesTab.getRightRegexp();
        Criterions searchCriterions = super
                .getAttributesSearchCriterions(pattern);

        VCAttributesTreeModel model = attributesTab.getSelectedAttributesTree()
                .getModel();
        IAttributeManager manager = AttributeManagerFactory.getCurrentImpl();

        boolean historic = model.isHistoric();
        model.match(manager, searchCriterions, historic);

        // VCAttributesSelectTree rightTree =
        // VCAttributesSelectTree.getInstance();
        // rightTree.setModel(model);
        //
        // VCAttributesRecapTree recapTree =
        // VCAttributesRecapTree.getInstance();
        // recapTree.setModel(model);

        TreeMap<String, ViewConfigurationAttribute> attrs = model
                .getAttributes();
        ViewConfiguration currentViewConfiguration = viewConfigurationBean
                .getEditingViewConfiguration();
        if (currentViewConfiguration != null) {
            currentViewConfiguration.getAttributes().removeAttributesNotInList(
                    attrs);
        }

        viewConfigurationBean.refreshEditingUI();
        // ViewConfiguration.setCurrentViewConfiguration(currentViewConfiguration);
        // ViewConfiguration
        // .setSelectedViewConfigurationAndPush(currentViewConfiguration);
    }
}
