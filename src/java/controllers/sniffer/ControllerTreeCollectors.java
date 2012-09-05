package controllers.sniffer;

import controllers.Controller;
import controllers.global.ControllerMainFrame;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import model.common.ModelTrees;
import views.panels.PanelTreeCollectors;

/**
 *
 * @author skuarch
 */
public class ControllerTreeCollectors extends Controller {

    private PanelTreeCollectors panelTreeCollectors = new PanelTreeCollectors();

    //==========================================================================
    @Override
    public void setupInterface() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    
                    createJTreeCollectors();      
                    
                } catch (Exception e) {
                    NOTIFICATIONS.error("Error creating tree collectors", e);
                }
                
                return null;
            }
        }.execute();
    }

    //==========================================================================
    public void createJTreeCollectors() {

        DefaultMutableTreeNode nodeWait = new DefaultMutableTreeNode("wait");

        final JTree jTree = panelTreeCollectors.getjTreeCollectors();
        final ModelTrees mt = new ModelTrees();
        
        try {
            
            jTree.setModel(new DefaultTreeModel(nodeWait));
            jTree.setEnabled(false);
            jTree.setModel(new ModelTrees().getCollectorsModel());     
            
            //add listener jtree
            jTree.addMouseListener(new MouseAdapter() {
                //--------------------------------------------------------------
                @Override
                public void mousePressed(MouseEvent e) {
                    ControllerMainFrame.getInstance().clickTrees();
                }
            });
            
            panelTreeCollectors.getjTextFieldSearch().getDocument().addDocumentListener(new DocumentListener() {

                @Override
                public void insertUpdate(DocumentEvent e) {
                    mt.fireDocumentChangeEvent(panelTreeCollectors.getjTextFieldSearch(),jTree);
                }

                @Override
                public void removeUpdate(DocumentEvent e) {
                    mt.fireDocumentChangeEvent(panelTreeCollectors.getjTextFieldSearch(), jTree);
                }

                @Override
                public void changedUpdate(DocumentEvent e) {
                    //mt.fireDocumentChangeEvent(panelTreeViews.getjTextFieldSearch(), panelTreeViews.getjTreeViews());
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating collectors tree", e);
        } finally{
            panelTreeCollectors.getjTreeCollectors().setEnabled(true);
        }

    } // end createJTreeCollectors
    
    //==========================================================================
    public JPanel getPanelTreeCollectors(){
        return panelTreeCollectors;
    }

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
