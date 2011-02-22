/*
 * Synchrotron Soleil File : VCVariationEditCopyValidateAction.java Project :
 * mambo Description : Author : SOLEIL Original : 13 d�c. 2005 Revision: Author:
 * Date: State: Log: VCVariationEditCopyValidateAction.java,v
 */
package fr.soleil.mambo.actions.view;

import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JDialog;
import javax.swing.JTextArea;

import fr.soleil.archiving.gui.tools.GUIUtilities;

/**
 * @author SOLEIL
 */
public class VCVariationEditCopyValidateAction extends AbstractAction {

    private static final long serialVersionUID = -9016534543699419825L;
    private final JTextArea text;
    private final JDialog dialog;

    public VCVariationEditCopyValidateAction(final String name, final JTextArea copyText,
	    final JDialog _dialog) {
	super();
	putValue(Action.NAME, name);
	putValue(Action.SHORT_DESCRIPTION, name);
	text = copyText;
	dialog = _dialog;
    }

    @Override
    public void actionPerformed(final ActionEvent arg0) {
	final Toolkit toolkit = Toolkit.getDefaultToolkit();
	final Clipboard clipboard = toolkit.getSystemClipboard();
	final StringSelection stringSelection = new StringSelection(text.getText().replaceAll("\n",
		GUIUtilities.CRLF));
	clipboard.setContents(stringSelection, stringSelection);
	dialog.setVisible(false);
    }
}
