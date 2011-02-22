//+======================================================================
// $Source: /cvsroot/tango-cs/tango/tools/mambo/containers/view/ViewAttributesTreePanel.java,v $
//
// Project:      Tango Archiving Service
//
// Description:  Java source code for the class  ViewAttributesTreePanel.
//						(Claisse Laurent) - 5 juil. 2005
//
// $Author: ounsy $
//
// $Revision: 1.5 $
//
// $Log: ViewAttributesTreePanel.java,v $
// Revision 1.5  2008/04/09 10:45:46  achouri
// add DateRangeBox and ActionPanel
//
// Revision 1.4  2007/01/10 14:28:18  ounsy
// minor changes (look&feel)
//
// Revision 1.3  2007/01/09 16:25:48  ounsy
// look & feel with "expand all" buttons in main frame
//
// Revision 1.2  2005/11/29 18:27:07  chinkumo
// no message
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
package fr.soleil.mambo.containers.view;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ItemEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import fr.soleil.mambo.Mambo;
import fr.soleil.mambo.actions.view.VCAttributesRecapTreeExpandAllAction;
import fr.soleil.mambo.components.view.VCAttributesRecapTree;
import fr.soleil.mambo.containers.view.dialogs.DateRangeBox;
import fr.soleil.mambo.data.view.ViewConfiguration;
import fr.soleil.mambo.data.view.ViewConfigurationData;
import fr.soleil.mambo.models.VCAttributesTreeModel;
import fr.soleil.mambo.tools.GUIUtilities;


public class ViewAttributesTreePanel extends JPanel
{
    private static ViewAttributesTreePanel instance = null;
    private JScrollPane scrollPane;
    private VCAttributesRecapTree tree;
    private final static ImageIcon expandAllIcon = new ImageIcon( Mambo.class.getResource( "icons/expand_all.gif" ) );
    private JButton expandAllButton;

    private DateRangeBox dateRangeBox;

	private ViewActionPanel viewActionPanel;

    /**
     * @return 8 juil. 2005
     */
    public static ViewAttributesTreePanel getInstance ()
    {
        if ( instance == null )
        {
            instance = new ViewAttributesTreePanel();
            GUIUtilities.setObjectBackground( instance , GUIUtilities.VIEW_COLOR );
            instance.dateRangeBox.getDateRangeComboBoxListener().itemStateChanged( 
            		new ItemEvent( instance.dateRangeBox.getDateRangeComboBox() , 
            		ItemEvent.ITEM_STATE_CHANGED , 
            		instance.dateRangeBox.getDateRangeComboBoxListener().getLast1h() , 
            		ItemEvent.SELECTED ) );
        }

        return instance;
    }

    /**
     * 
     */
    private ViewAttributesTreePanel ()
    {
        super( new GridBagLayout() );
        initComponents();
        addComponents();
    }

    /**
     * 19 juil. 2005
     */
    private void addComponents ()
    {

        GridBagConstraints treeConstraints = new GridBagConstraints();
        treeConstraints.fill = GridBagConstraints.BOTH;
        treeConstraints.gridx = 0;
        treeConstraints.gridy = 0;
        treeConstraints.weightx = 1;
        treeConstraints.weighty = 1;

        GridBagConstraints dateConstraints = new GridBagConstraints();
        dateConstraints.fill = GridBagConstraints.BOTH;
        dateConstraints.gridx = 0;
        dateConstraints.gridy = 1;
        dateConstraints.weightx = 1;
        dateConstraints.weighty = 0;

        GridBagConstraints actionConstraints = new GridBagConstraints();
        actionConstraints.fill = GridBagConstraints.BOTH;
        actionConstraints.gridx = 0;
        actionConstraints.gridy = 2;
        actionConstraints.weightx = 1;
        actionConstraints.weighty = 0;

        this.add(scrollPane, treeConstraints);
        this.add(dateRangeBox, dateConstraints);
        this.add(viewActionPanel, actionConstraints);
    }

    /**
     * 19 juil. 2005
     */
    private void initComponents ()
    {
        VCAttributesTreeModel model = VCAttributesTreeModel.getInstance();
        tree = VCAttributesRecapTree.getInstance( model );

        expandAllButton = new JButton( new VCAttributesRecapTreeExpandAllAction() );
        expandAllButton.setIcon( expandAllIcon );
        expandAllButton.setMargin( new Insets(0,0,0,0) );
        expandAllButton.setBackground( Color.WHITE );
        expandAllButton.setBorderPainted( false );
        expandAllButton.setFocusable( false );
        expandAllButton.setFocusPainted( false );
        expandAllButton.setHorizontalAlignment( JButton.LEFT );
        expandAllButton.setFont( GUIUtilities.expandButtonFont );
        expandAllButton.setForeground( Color.BLACK );

        scrollPane = new JScrollPane( tree );
        scrollPane.setVerticalScrollBarPolicy( JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setHorizontalScrollBarPolicy( JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED );
        scrollPane.setColumnHeaderView( expandAllButton );

    	dateRangeBox = new DateRangeBox(this);
    	GUIUtilities.setObjectBackground( dateRangeBox , GUIUtilities.VIEW_COLOR );
        GUIUtilities.setObjectBackground( dateRangeBox.getDynamicDateRangeCheckBox() , GUIUtilities.VIEW_COLOR );
        
        ViewConfiguration selectedVC = ViewConfiguration.getSelectedViewConfiguration();
        if( selectedVC != null ){
        	ViewConfigurationData vcData = selectedVC.getData();
        	dateRangeBox.setStartDate( vcData.getStartDate().toString().trim() );
        }
        
        viewActionPanel = ViewActionPanel.getInstance();

    }

	public DateRangeBox getDateRangeBox() {
		return dateRangeBox;
	}

}
