// +======================================================================
// $Source:
// /cvsroot/tango-cs/tango/tools/bensikin/bensikin/actions/context/LoadContextAction.java,v
// $
//
// Project: Tango Archiving Service
//
// Description: Java source code for the class LoadACAction.
// (Claisse Laurent) - 5 juil. 2005
//
// $Author: ounsy $
//
// $Revision: 1.5 $
//
// $Log: LoadContextAction.java,v $
// Revision 1.5 2007/08/24 14:05:20 ounsy
// bug correction with context printing as text
//
// Revision 1.4 2007/08/24 12:52:56 ounsy
// minor changes
//
// Revision 1.3 2006/04/10 08:46:54 ounsy
// Bensikin action now all inherit from BensikinAction for easy rights
// management
//
// Revision 1.2 2005/12/14 16:02:00 ounsy
// new Word-like file management
//
// Revision 1.1 2005/12/14 14:07:17 ounsy
// first commit including the new "tools,xml,lifecycle,profile" sub-directories
// under "bensikin.bensikin" and removing the same from their former locations
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
package fr.soleil.bensikin.actions.context;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.Action;
import javax.swing.JFileChooser;

import fr.soleil.archiving.gui.logs.GUILoggerFactory;
import fr.soleil.bensikin.actions.BensikinAction;
import fr.soleil.bensikin.components.context.ContextFileFilter;
import fr.soleil.bensikin.components.context.detail.ContextAttributesTree;
import fr.soleil.bensikin.containers.BensikinFrame;
import fr.soleil.bensikin.containers.context.ContextActionPanel;
import fr.soleil.bensikin.containers.context.ContextDataPanel;
import fr.soleil.bensikin.data.context.Context;
import fr.soleil.bensikin.data.context.manager.ContextManagerFactory;
import fr.soleil.bensikin.data.context.manager.IContextManager;
import fr.soleil.bensikin.data.snapshot.Snapshot;
import fr.soleil.bensikin.tools.Messages;
import fr.soleil.commonarchivingapi.ArchivingTools.Diary.ILogger;

/**
 * This action can be of 2 type: default (quick load from the default directory
 * and file), or non-default (load from the user-defined directory and file,
 * with prepositionning on the default directory). Loads a context from a file
 * <UL>
 * <LI>if the load isn't a quick load,opens a file chooser dialog specialised in
 * context loading, gets the path to load from from it.
 * <LI>uses the application's IContextManager to load the context.
 * <LI>displays the loaded context.
 * <LI>resets the snapshots display
 * <LI>logs the action's success or failure
 * </UL>
 * 
 * @author CLAISSE
 */
public class LoadContextAction extends BensikinAction {
    final static ILogger logger = GUILoggerFactory.getLogger();

    private static final long serialVersionUID = -8486055826971389966L;
    private final boolean isDefault;

    /**
     * Standard action constructor that sets the action's name, plus sets the
     * isDefault attribute.
     * 
     * @param name
     *            The action name
     * @param _isDefault
     *            True if the load action is a quick load from the working
     *            directory and default file, false otherwise
     */
    public LoadContextAction(final String name, final boolean _isDefault) {
	super.putValue(Action.NAME, name);
	super.putValue(Action.SHORT_DESCRIPTION, name);

	isDefault = _isDefault;
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
     */
    @Override
    public void actionPerformed(final ActionEvent actionEvent) {
	// System.out.println("LoadACAction");
	final IContextManager manager = ContextManagerFactory.getCurrentImpl();

	if (!isDefault) {
	    // open file chooser
	    final JFileChooser chooser = new JFileChooser();
	    final String location = manager.getSaveLocation();
	    if (location == null || "".equals(location.trim())) {
		chooser.setCurrentDirectory(new File(manager.getDefaultSaveLocation()));
	    } else {
		chooser.setCurrentDirectory(new File(location).getParentFile());
	    }
	    final String title = Messages.getMessage("DIALOGS_FILE_CHOOSER_LOAD_CONTEXT_TITLE");
	    chooser.setDialogTitle(title);
	    final ContextFileFilter ACfilter = new ContextFileFilter();
	    chooser.addChoosableFileFilter(ACfilter);

	    final int returnVal = chooser.showOpenDialog(BensikinFrame.getInstance());
	    if (returnVal == JFileChooser.APPROVE_OPTION) {
		final File f = chooser.getSelectedFile();
		String path = f.getAbsolutePath();

		if (f != null) {
		    final String extension = ContextFileFilter.getExtension(f);
		    final String expectedExtension = ACfilter.getExtension();

		    if (extension == null || !extension.equalsIgnoreCase(expectedExtension)) {
			path += ".";
			path += expectedExtension;
		    }
		}
		manager.setNonDefaultSaveLocation(path);
	    } else {
		return;
	    }
	} else {
	    manager.setNonDefaultSaveLocation(null);
	}

	try {
	    final Context selectedContext = manager.loadContext();
	    selectedContext.setContextFile(true);

	    Snapshot.reset(false, true);

	    Context.setSelectedContext(selectedContext);
	    selectedContext.push();

	    // the user can modify the loaded context
	    final ContextDataPanel dataPanel = ContextDataPanel.getInstance();
	    dataPanel.enableInput(false);

	    final String msg = Messages.getLogMessage("LOAD_CONTEXT_ACTION_OK");
	    logger.trace(ILogger.LEVEL_DEBUG, msg);
	} catch (final FileNotFoundException fnfe) {
	    final String msg = Messages.getLogMessage("LOAD_CONTEXT_ACTION_WARNING");
	    logger.trace(ILogger.LEVEL_WARNING, msg);
	    logger.trace(ILogger.LEVEL_WARNING, fnfe);
	    return;
	} catch (final Exception e) {
	    final String msg = Messages.getLogMessage("LOAD_CONTEXT_ACTION_KO");
	    logger.trace(ILogger.LEVEL_ERROR, msg);
	    logger.trace(ILogger.LEVEL_ERROR, e);
	    return;
	}

	ContextAttributesTree.getInstance().expandAll(true);

	ContextActionPanel.getInstance().allowPrint(true);
    }
}
