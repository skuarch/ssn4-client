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
import model.util.CategoriesUtilities;
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

                    //----------------------------------------------------------
                    /**
                     * exit the program.
                     */
                    mainFrame.getjMenuItemExit().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            System.exit(0);
                        }
                    });

                    //----------------------------------------------------------
                    /**
                     * close all tabs.
                     */
                    mainFrame.getjMenuItemCloseAllTabs().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            //close tabs in navigator
                            closeAllTabs();
                        }
                    });

                    //----------------------------------------------------------
                    mainFrame.getjMenuItemEventViewer().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            EventViewer.getInstance().setVisible(true);
                        }
                    });

                    //----------------------------------------------------------
                    mainFrame.getjMenuItemSearchIPAddress().addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            ControllerSearchIPAddress csipa = new ControllerSearchIPAddress();
                            csipa.setupInterface();
                            csipa.setVisible(true);
                        }
                    });

                    //----------------------------------------------------------
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
    /**
     * close tabs in navigator.
     */
    private void closeAllTabs() {
        ControllerNavigator.getInstance().closeAllTabs();
    } // end closeAllTabs

    //==========================================================================
    /**
     * when some tree is clicked this method is called.<br/> check if tree
     * collectors is enabled, validate if is a categorie and add the tab.
     */
    public void clickTrees() {

        JTree treeViews = null;
        String view = null;
        JTree treeCollectors = null;
        String collector = null;
        String job = null;
        ControllerNavigator controllerNavigator = null;

        try {

            treeCollectors = ViewUtilities.getJtreeFromJTabPane(mainFrame.getjTabbedPaneCollectors());
            if (!treeCollectors.isEnabled()) {
                //if tree collectors doesn't enabled is because it is checking the connectivity
                return;
            }

            treeViews = ViewUtilities.getJtreeFromJTabPane(mainFrame.getjTabbedPaneTreeViews());
            view = ViewUtilities.getSelectedJTree(treeViews);
            collector = ViewUtilities.getSelectedJTree(treeCollectors);

            if (!validateClick(view, collector)) { //aqui se tiene que validar el job
                return;
            }

            //aqui me quede ******
            controllerNavigator = ControllerNavigator.getInstance();
            JPanel panel = new JPanel();
            panel.setName(view);
            controllerNavigator.addTab(view, panel);
            mainFrame.getjSplitPaneMain().setRightComponent(controllerNavigator.getNavigator());

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end clickTrees

    //==========================================================================    
    /**
     * validate if everything is ok when someone do click in the trees.
     *
     * @param view String
     * @param collector String
     * @return boolean
     */
    private boolean validateClick(String view, String collector) {

        boolean flag = true;

        try {

            if (view == null || collector == null) {
                return false;
            }

            //validate if the collector is equals to "servers"
            if (collector.equalsIgnoreCase("servers")) {
                //"servers" doesn't work, I need a collector name
                return false;
            }

            //validate if is a categorie
            if (CategoriesUtilities.exitsCategorie(view)) {
                //this means that the selection in tree view is a category.
                return false;
            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

        return flag;

    } // end validateClick

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
    } // end destroyCurrentThread
} // end class

