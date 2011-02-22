// +======================================================================
// $Source:
// /cvsroot/tango-cs/tango/tools/mambo/components/renderers/VCTreeRenderer.java,v
// $
//
// Project: Tango View Service
//
// Description: Java source code for the class TreeRenderer.
// (Chinkumo Jean) - Mar 3, 2004
//
// $Author: ounsy $
//
// $Revision: 1.5 $
//
// $Log: VCTreeRenderer.java,v $
// Revision 1.5 2006/08/07 13:03:07 ounsy
// trees and lists sort
//
// Revision 1.4 2006/07/28 10:06:45 ounsy
// a bit simplified
//
// Revision 1.3 2006/03/07 14:39:10 ounsy
// spectrum attributes no longer appear in a different police
//
// Revision 1.2 2005/11/29 18:27:45 chinkumo
// no message
//
// Revision 1.1.2.2 2005/09/14 15:41:20 chinkumo
// Second commit !
//
// Revision 1.5 2005/06/24 12:06:33 chinkumo
// Some constants were moved from fr.soleil.TangoView.ViewApi.ConfigConst to
// fr.soleil.TangoView.ViewTools.Tools.GlobalConst.
// This change was reported here.
//
// Revision 1.4 2005/06/14 10:49:05 chinkumo
// Branch (viewGUI_1_0_1-branch_0) and HEAD merged.
//
// Revision 1.3.4.1 2005/06/13 12:16:43 chinkumo
// Changes made to improve the management of exceptions were reported here.
//
// Revision 1.3 2005/02/04 17:00:29 chinkumo
// The icons building strategy was modified.
//
// Revision 1.2 2005/01/26 16:24:51 chinkumo
// Export of the new application source code
//
// Revision 1.1 2004/12/06 16:52:24 chinkumo
// First commit (new architecture).
//
// Revision 1.2 2004/09/01 16:09:34 chinkumo
// Heading was updated.
//
//
// copyleft : Synchrotron SOLEIL
// L'Orme des Merisiers
// Saint-Aubin - BP 48
// 91192 GIF-sur-YVETTE CEDEX
//
// -======================================================================

package fr.soleil.mambo.components.renderers;

import java.awt.Component;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreeNode;

import fr.soleil.hdbtdbArchivingApi.ArchivingManagerApi.HdbArchivingManagerApiRef;
import fr.soleil.hdbtdbArchivingApi.ArchivingManagerApi.TdbArchivingManagerApiRef;
import fr.soleil.mambo.Mambo;
import fr.soleil.mambo.bean.view.ViewConfigurationBean;
import fr.soleil.mambo.data.view.ViewConfiguration;
import fr.soleil.mambo.data.view.ViewConfigurationAttribute;
import fr.soleil.mambo.datasources.db.archiving.ArchivingManagerFactory;

public class VCTreeRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = -5287251286444041049L;
    protected final static ImageIcon rootIcon = new ImageIcon(Mambo.class
	    .getResource("icons/database.gif"));
    protected final static ImageIcon disabledIcon = new ImageIcon(Mambo.class
	    .getResource("icons/bulbDisabled.gif"));
    protected final static ImageIcon enabledHIcon = new ImageIcon(Mambo.class
	    .getResource("icons/bulbEnabled.gif"));
    protected final static ImageIcon enabledTIcon = new ImageIcon(Mambo.class
	    .getResource("icons/bulbEnabledTemp.gif"));
    protected final static ImageIcon enabledBothIcon = new ImageIcon(Mambo.class
	    .getResource("icons/bulbBoth.gif"));

    protected final ImageIcon unknownIcon = new ImageIcon(Mambo.class
	    .getResource("icons/unknown.png"), "unknown state");

    private final ViewConfigurationBean viewConfigurationBean;
    private final boolean isEditingView;

    public VCTreeRenderer(final ViewConfigurationBean viewConfigurationBean,
	    final boolean isEditingView) {
	super();
	this.viewConfigurationBean = viewConfigurationBean;
	this.isEditingView = isEditingView;
    }

    @Override
    public Component getTreeCellRendererComponent(final JTree tree, final Object value,
	    final boolean sel, final boolean expanded, final boolean leaf, final int row,
	    final boolean hasFocus) {

	final JLabel label = (JLabel) super.getTreeCellRendererComponent(tree, value, sel,
		expanded, leaf, row, hasFocus);
	Font font = label.getFont();

	final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	if (node.getLevel() == 4) {
	    final String nameH = getFullAttributeNameFromNode(true, node);
	    final String nameT = getFullAttributeNameFromNode(false, node);
	    ViewConfiguration currentViewConfiguration;
	    if (isEditingView) {
		currentViewConfiguration = viewConfigurationBean.getEditingViewConfiguration();
	    } else {
		currentViewConfiguration = viewConfigurationBean.getViewConfiguration();
	    }
	    if (currentViewConfiguration != null) {
		String name;
		if (currentViewConfiguration.getData().isHistoric()) {
		    name = nameH;
		} else {
		    name = nameT;
		}

		ViewConfigurationAttribute attr = null;
		if (currentViewConfiguration.containsAttribute(name)) {
		    attr = currentViewConfiguration.getAttributes().getAttribute(name);

		    if (attr.isEmpty()) {
			font = new Font("SansSerif", Font.ITALIC, 12);
		    } else {
			font = new Font("SansSerif", Font.BOLD, 12);
		    }
		} else {
		    font = new Font("SansSerif", Font.ITALIC, 12);
		}
	    }

	    boolean iah = false, iat = false;

	    boolean hdbDown = false;
	    boolean tdbDown = false;
	    try {
		iah = ArchivingManagerFactory.getCurrentImpl().isArchived(nameH, true);
	    } catch (final Exception e) {
		hdbDown = true;
	    }
	    try {
		iat = ArchivingManagerFactory.getCurrentImpl().isArchived(nameT, false);
	    } catch (final Exception e) {
		tdbDown = true;
	    }
	    if (iah && iat) { // tdb+hdb
		label.setIcon(enabledBothIcon);
	    } else if (iah) { // hdb only
		label.setIcon(enabledHIcon);
	    } else if (iat) {// tdb only
		label.setIcon(enabledTIcon);
	    } else if (!iah && !iat && hdbDown && tdbDown) { // connection error
		label.setIcon(unknownIcon);
	    } else {// no archiving
		label.setIcon(disabledIcon);
	    }
	} else {
	    if (node.isRoot()) {
		label.setIcon(rootIcon);
	    } else {
		label.setIcon(UIManager.getIcon("Tree.closedIcon"));
	    }
	    font = new Font("SansSerif", Font.PLAIN, 12);
	}

	label.setFont(font);
	label.setToolTipText(label.getText());

	return label;
    }

    public static String getFullAttributeNameFromNode(final boolean historic, final Object value) {
	String name = "";
	final boolean facility = historic ? HdbArchivingManagerApiRef.m_Facility
		: TdbArchivingManagerApiRef.m_Facility;
	final DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
	final TreeNode[] treeNode = node.getPath();
	name = (facility ? "//" + treeNode[0].toString() + "/" : "") + treeNode[1].toString() + "/"
		+ treeNode[2].toString() + "/" + treeNode[3].toString() + "/"
		+ treeNode[4].toString();
	return name;
    }
}
