//+======================================================================
// $Source: /cvsroot/tango-cs/tango/tools/bensikin/bensikin/containers/snapshot/SnapshotPanel.java,v $
//
// Project:      Tango Archiving Service
//
// Description:  Java source code for the class  SnapshotPanel.
//						(Claisse Laurent) - 16 juin 2005
//
// $Author: ounsy $
//
// $Revision: 1.7 $
//
// $Log: SnapshotPanel.java,v $
// Revision 1.7  2006/01/12 13:53:48  ounsy
// minor changes
//
// Revision 1.6  2006/01/10 13:29:11  ounsy
// minor changes
//
// Revision 1.5  2005/11/29 18:25:08  chinkumo
// no message
//
// Revision 1.1.1.2  2005/08/22 11:58:36  chinkumo
// First commit
//
//
// copyleft :		Synchrotron SOLEIL
//					L'Orme des Merisiers
//					Saint-Aubin - BP 48
//					91192 GIF-sur-YVETTE CEDEX
//
//-======================================================================
package fr.soleil.bensikin.containers.snapshot;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

import fr.soleil.bensikin.tools.GUIUtilities;
import fr.soleil.bensikin.tools.Messages;


/**
 * Contains the snapshot half of the application, ie. a list of snapshots (SnapshotListPanel)
 * and details about the selected snapshots (SnapshotDetailPanel).
 *
 * @author CLAISSE
 */
public class SnapshotPanel extends JPanel
{
	private static final int INITIAL_DETAIL_SPLIT_POSITION = 330;
    private static final int INITIAL_DETAIL_SPLIT_SIZE = 8;
	private static SnapshotPanel snapshotPanelInstance = null;

	/**
	 * Instantiates itself if necessary, returns the instance.
	 *
	 * @return The instance
	 */
	public static SnapshotPanel getInstance()
	{
		if ( snapshotPanelInstance == null )
		{
			snapshotPanelInstance = new SnapshotPanel();
		}

		return snapshotPanelInstance;
	}

	/**
	 * Builds the panel
	 */
	private SnapshotPanel()
	{
		JScrollPane listScrollPane = new JScrollPane(SnapshotListPanel.getInstance());
		JScrollPane detailScrollPane = new JScrollPane(SnapshotDetailPanel.getInstance());

		JSplitPane detailSplit = new JSplitPane(JSplitPane.VERTICAL_SPLIT , false);
		GUIUtilities.setObjectBackground(detailSplit , GUIUtilities.SNAPSHOT_COLOR);
		detailSplit.setOneTouchExpandable(true);
		detailSplit.setDividerLocation(INITIAL_DETAIL_SPLIT_POSITION);
        detailSplit.setDividerSize(INITIAL_DETAIL_SPLIT_SIZE);
		

		GUIUtilities.setObjectBackground(listScrollPane , GUIUtilities.SNAPSHOT_COLOR);
		GUIUtilities.setObjectBackground(detailScrollPane , GUIUtilities.SNAPSHOT_COLOR);
		GUIUtilities.setObjectBackground(listScrollPane.getViewport() , GUIUtilities.SNAPSHOT_COLOR);
		GUIUtilities.setObjectBackground(detailScrollPane.getViewport() , GUIUtilities.SNAPSHOT_COLOR);
		detailSplit.setTopComponent(listScrollPane);
		detailSplit.setBottomComponent(detailScrollPane);

		this.setLayout(new GridLayout(1 , 0));
		this.add(detailSplit);

		String msg = Messages.getMessage("SNAPSHOT_BORDER");
		TitledBorder tb = BorderFactory.createTitledBorder
		        (BorderFactory.createEtchedBorder(EtchedBorder.LOWERED) ,
		         msg ,
		         TitledBorder.DEFAULT_JUSTIFICATION ,
		         TitledBorder.TOP ,
		         GUIUtilities.getTitleFont());
		Border border = ( Border ) ( tb );
		this.setBorder(border);

		GUIUtilities.setObjectBackground(this , GUIUtilities.SNAPSHOT_COLOR);
	}
}
