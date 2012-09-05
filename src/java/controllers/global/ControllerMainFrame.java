package controllers.global;

import controllers.Controller;
import controllers.sniffer.ControllerSearchIPAddress;
import controllers.sniffer.ControllerTreeCollectors;
import controllers.sniffer.ControllerTreeViews;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JTree;
import javax.swing.SwingWorker;
import model.beans.Configuration;
import model.common.ModelConfiguration;
import model.util.ViewUtilities;
import views.dialogs.EventViewer;
import views.frames.MainFrame;

/**
 * controller for handler MainFrame window.
 *
 * @author skuarch
 */
public class ControllerMainFrame extends Controller {

    private MainFrame mainFrame = null;
    private SwingWorker<Void, Void> sw = null;
    private Configuration configuration = null;

    //==========================================================================    
    /**
     * create the instance and set
     * <code>configuration</code>.
     */
    private ControllerMainFrame() {

        try {

            mainFrame = new MainFrame();
            configuration = new ModelConfiguration().getInitialConfiguration();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end ControllerMainFrame

    //==========================================================================
    /**
     * return the instance of this class.
     *
     * @return ControllerMainFrame
     */
    public static ControllerMainFrame getInstance() {
        return NewControllerMainFrameHolder.INSTANCE;
    } // end MainFrame

    //==========================================================================
    /**
     * singleton, this method is only for keep one instance
     */
    private static class NewControllerMainFrameHolder {

        private static final ControllerMainFrame INSTANCE = new ControllerMainFrame();
    } // end NewMainFrameHolder

    //==========================================================================
    /**
     * this method is for to configure all. this method must be launched after
     * create the instance
     */
    @Override
    public void setupInterface() {

        mainFrame.getjSplitPaneMain().setDividerLocation(200);

        sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                ControllerTreeViews ctv = null;
                ControllerTreeCollectors ctc = null;

                try {

                    mainFrame.setTitle("SSN [ " + configuration.getWindowTitle() + " ]");

                    //add tree views
                    ctv = new ControllerTreeViews();
                    ctv.setupInterface();
                    mainFrame.getjTabbedPaneTreeViews().add(ctv.getPanelTreeViews());

                    //add tree collectors
                    ctc = new ControllerTreeCollectors();
                    ctc.setupInterface();
                    mainFrame.getjTabbedPaneCollectors().add(ctc.getPanelTreeCollectors());

                    //add listeners to all menu and buttons
                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error creating main window", e);
                }

                return null;
            }
        };

        sw.execute();

    } // end setupInterface

    //==========================================================================
    /**
     * each button needs a listener, this method is for that.
     */
    private void addListeners() {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    //------------------------------------------------------------------
                    mainFrame.getjMenuItemExit().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });

                    //------------------------------------------------------------------
                    mainFrame.getjMenuItemEventViewer().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EventViewer.getInstance().setVisible(true);
                        }
                    });

                    //------------------------------------------------------------------
                    mainFrame.getjMenuItemSearchIPAddress().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ControllerSearchIPAddress csipa = new ControllerSearchIPAddress();
                            csipa.setupInterface();
                            csipa.setVisible(true);
                        }
                    });

                    //------------------------------------------------------------------
                    mainFrame.getjMenuItemModelerChains().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                        }
                    });

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error adding listeners.<br>-Please contact with the administrator for more details", e);
                }

                return null;
            }
        }.execute();

    } // end addListeners

    //==========================================================================
    public void clickTrees(){
        
        JTree treeViews = null;
        String view = null;
        JTree treeCollectors = null;
        String collector = null;
        ControllerNavigator controllerNavigator = null;
        
        try {
            
            treeViews = ViewUtilities.getJtreeFromJTabPane(mainFrame.getjTabbedPaneTreeViews());
            view = ViewUtilities.getSelectedJTree(treeViews);
            
            treeCollectors = ViewUtilities.getJtreeFromJTabPane(mainFrame.getjTabbedPaneCollectors());
            if(!treeCollectors.isEnabled()){
                return;
            }
            collector = ViewUtilities.getSelectedJTree(treeCollectors);
            
            if(view == null || collector == null){
                return;
            }           
            
            
            //aqui me quede
            controllerNavigator = ControllerNavigator.getInstance();
            JPanel panel = new JPanel();
            panel.setName(view);
            controllerNavigator.addTab(panel);
            mainFrame.getjSplitPaneMain().setRightComponent(controllerNavigator.getNavigator());
            
        } catch (Exception e) {
            NOTIFICATIONS.error("unexpected error", e);
        }
    
    } // end clickTrees

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        mainFrame.setVisible(flag);
    } // end setVisible

    //==========================================================================
    /**
     * set null all.
     */
    @Override
    public void destroyCurrentThread() {
        mainFrame = null;
        sw = null;
        configuration = null;
    } // end destroyCurrentThread

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {

        try {
            NOTIFICATIONS.information("clean up ControllerMainFrame", false);
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        } finally {
            super.finalize();
        }

    } // end finalize
} // end class

