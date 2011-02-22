package fr.soleil.bensikin.containers.sub.dialogs;

import java.awt.event.WindowEvent;
import javax.swing.JDialog;

import fr.soleil.bensikin.containers.BensikinFrame;
import fr.soleil.bensikin.tools.Messages;

public class WaitingDialog extends JDialog {

	protected static WaitingDialog instance;
	protected static String firstPart = Messages
			.getMessage("DIALOGS_WAITING_LOADING_TITLE");
	protected static String secondPart = Messages
			.getMessage("DIALOGS_WAITING_WAIT_TITLE");

	public static void openInstance() {
		if (instance == null) {
			instance = new WaitingDialog();
		}
		instance.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		instance.setTitle(firstPart + " " + secondPart);
		int myWidth = 500;
		int myHeight = 0;
		int relativeX = BensikinFrame.getInstance().getX()
				+ BensikinFrame.getInstance().getWidth() - myWidth;
		relativeX /= 2;
		int relativeY = BensikinFrame.getInstance().getY()
				+ BensikinFrame.getInstance().getHeight() - myHeight;
		relativeY /= 2;
		instance.setBounds(relativeX, relativeY, myWidth, myHeight);
		instance.setVisible(true);
	}

	public static void closeInstance() {
		if (instance == null) {
			instance = new WaitingDialog();
		}
		firstPart = Messages.getMessage("DIALOGS_WAITING_LOADING_TITLE");
		secondPart = Messages.getMessage("DIALOGS_WAITING_WAIT_TITLE");
		instance.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		instance.processWindowEvent(new WindowEvent(instance,
				WindowEvent.WINDOW_CLOSING));
	}

	protected WaitingDialog() {
		super(BensikinFrame.getInstance(), firstPart + " " + secondPart);
		this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		repaint();
	}

	/**
	 * Changes the first part of the message (default : "loading...")
	 * 
	 * @param _firstPart
	 *            The String that replaces the first part of the message
	 */
	public static void changeFirstMessage(String _firstPart) {
		firstPart = _firstPart;
	}

	/**
	 * Changes the second part of the message (default :
	 * "(Please Wait: It may take a while)")
	 * 
	 * @param _secondPart
	 *            The String that replaces the second part of the message
	 */
	public static void changeSecondMessage(String _secondPart) {
		secondPart = _secondPart;
	}

}
