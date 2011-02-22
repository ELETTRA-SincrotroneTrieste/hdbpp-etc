package fr.soleil.mambo.components.view;

import java.util.Vector;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

import fr.soleil.mambo.actions.view.listeners.ExpressionTreeListener;
import fr.soleil.mambo.components.renderers.ExpressionTreeRenderer;
import fr.soleil.mambo.data.view.ExpressionAttribute;
import fr.soleil.mambo.models.ExpressionTreeModel;

public class ExpressionTree extends JTree
{

    private static ExpressionTree instance = null;

    public static ExpressionTree getInstance()
    {
        if (instance == null)
        {
            instance = new ExpressionTree();
        }
        return instance;
    }

	private TreePath[] lastSelectionsPath;
	private TreePath[] currentSelectionsPath;

    protected ExpressionTree ()
    {
        super();
        this.setModel(ExpressionTreeModel.getInstance());
        this.setCellRenderer(ExpressionTreeRenderer.getInstance());
        this.addTreeSelectionListener(ExpressionTreeListener.getInstance());
        
        this.lastSelectionsPath = null;
        this.currentSelectionsPath = null;

    }

//    public Vector getListOfAttributesToSet ()
//    {
//    	saveCurrenteSelection();
//    	
//    	TreePath[] selectedPath = this.currentSelectionsPath;
//    
//    	return treePathToVector(selectedPath);
//        
//    }
//
	public Vector getLastListOfAttributesToSet() {
		
		TreePath[] selectedPath = this.lastSelectionsPath;
	    
    	return treePathToVector(selectedPath);
    
	}

    private Vector treePathToVector(TreePath[] selectedPath) {
    	// TODO Auto-generated method stub
    	
        if ( selectedPath == null || selectedPath.length == 0 )
    	{
            return null;
        }

        Vector attributes = new Vector();
        for ( int i = 0 ; i < selectedPath.length ; i++ )
        {
            TreePath currentSelectedTreePath = selectedPath[ i ];
            if (currentSelectedTreePath.getPathCount() == 1) continue;
            DefaultMutableTreeNode currentSelectedNode = ( DefaultMutableTreeNode ) currentSelectedTreePath.getLastPathComponent();

            if ( ! currentSelectedNode.isRoot() )
            {
                ExpressionAttribute attr = (ExpressionAttribute)ExpressionTreeModel.getInstance().getExpressionAttributes().get( currentSelectedNode.getUserObject() );
                if (attr != null) attributes.add( attr );
            }
        }

        return attributes;
    }
	
	
    public Vector getSelectedAttributes()
    {
    	saveCurrenteSelection();
    	
        TreePath[] selectedPath = currentSelectionsPath;
        
        if ( selectedPath == null || selectedPath.length == 0 )
        {
            return null;
        }

        Vector attributes = new Vector();
        for ( int i = 0 ; i < selectedPath.length ; i++ )
        {
            TreePath currentSelectedTreePath = selectedPath[ i ];
            if (currentSelectedTreePath.getPathCount() == 1) continue;
            DefaultMutableTreeNode currentSelectedNode = ( DefaultMutableTreeNode ) currentSelectedTreePath.getLastPathComponent();

            if ( ! currentSelectedNode.isRoot() )
            {
                ExpressionAttribute attr = (ExpressionAttribute)ExpressionTreeModel.getInstance().getExpressionAttributes().get( currentSelectedNode.getUserObject() );
                if (attr != null) attributes.add( attr );
            }
        }

        return attributes;
    }

    public ExpressionAttribute getSelectedAttribute()
    {
        TreePath selectedPath = this.getSelectionPath();
        if (selectedPath == null || selectedPath.getPathCount() == 1)
        {
            return null;
        }
        ExpressionAttribute attr = null;
        DefaultMutableTreeNode selectedNode = ( DefaultMutableTreeNode ) selectedPath.getLastPathComponent();
        if ( ! selectedNode.isRoot() )
        {
            String attributeName = (String)selectedNode.getUserObject();
            attr = (ExpressionAttribute)ExpressionTreeModel.getInstance().getExpressionAttribute( attributeName );
        }
        selectedNode = null;
        selectedPath = null;
        return attr;
    }
    
    public void saveCurrenteSelection(){
    	this.lastSelectionsPath = this.currentSelectionsPath;
        currentSelectionsPath = this.getSelectionPaths();
    }
}
