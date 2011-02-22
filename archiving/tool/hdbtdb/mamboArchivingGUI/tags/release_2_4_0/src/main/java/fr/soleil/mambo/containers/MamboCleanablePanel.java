package fr.soleil.mambo.containers;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import fr.soleil.mambo.logs.ILogger;
import fr.soleil.mambo.logs.LoggerFactory;
import fr.soleil.mambo.tools.Messages;

public abstract class MamboCleanablePanel extends JPanel {

    private static final long serialVersionUID = -2545021518904028083L;

    public abstract void clean();

    public abstract void lightClean();

    public abstract void loadPanel();

    public abstract String getName();

    public abstract String getFullName();

    public void outOfMemoryErrorManagement() {
        String msg = String.format(Messages.getMessage("DIALOGS_VIEW_MEMORY_ERROR"), getName());
        JOptionPane.showMessageDialog(MamboFrame.getInstance(), msg, Messages
                .getMessage("DIALOGS_VIEW_MEMORY_ERROR_TITLE"), JOptionPane.ERROR_MESSAGE);
        LoggerFactory.getCurrentImpl().trace(ILogger.LEVEL_ERROR, msg);
    }

}
