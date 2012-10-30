package controllers.global;

import controllers.Controller;
import controllers.sniffer.ControllerSearchIPAddress;
import controllers.sniffer.ControllerTreeCollectors;
import controllers.sniffer.ControllerTreeViews;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import model.beans.Configuration;
import model.common.ModelConfiguration;
import model.util.ValidationUtilities;
import model.util.ViewUtilities;
import views.dialogs.EventViewer;
import views.dialogs.Options;
import views.frames.MainFrame;
import views.panels.LogoPanel;

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

        sw = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {

                ControllerTreeViews ctv = null;
                ControllerTreeCollectors ctc = null;

                try {

                    mainFrame.getjSplitPaneMain().setRightComponent(new LogoPanel());
                    mainFrame.getjSplitPaneMain().setDividerLocation(200);
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
                            setLogoPanel();
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
                    
                    //----------------------------------------------------------
                    mainFrame.getjMenuItemOptions().addActionListener(new ActionListener() {

                        @Override
                        public void actionPerformed(ActionEvent e) {
                            new Options(null, true).setVisible(true);
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
        Thread controlNavigatorThread = null;
        ControllerNavigator controllerNavigator = null;

        try {

            treeCollectors = ViewUtilities.getJtreeFromJTabPane(mainFrame.getjTabbedPaneCollectors());
            if (!treeCollectors.isEnabled()) {
                //if tree collectors doesn't enabled is because it is checking the connectivity
                return;
            }

            treeViews = ViewUtilities.getJtreeFromJTabPane(mainFrame.getjTabbedPaneTreeViews());
            view = ViewUtilities.getSelectedJTree(treeViews);
            job = ViewUtilities.getSelectedJTree(treeCollectors);
            collector = ViewUtilities.getOneBefore(treeCollectors.getSelectionPath());

            if (!ValidationUtilities.validateClickTree(view, collector, job)) {
                return;
            }

            //aqui me quede ******
            //llamar a controlNavigators !!!!!!
            new TabsDriver().addTabNavigator(view, job);

            /*controllerNavigator = ControllerNavigator.getInstance();
             JPanel panel = new JPanel();
             panel.setName(collector);
             controllerNavigator.addTab(collector, panel);
             mainFrame.getjSplitPaneMain().setRightComponent(controllerNavigator.getNavigator());*/

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end clickTrees

    //==========================================================================
    public void setLogoPanel() {
        mainFrame.getjSplitPaneMain().setRightComponent(new LogoPanel());
    }

    //==========================================================================
    public JFrame getMainFrame() {
        return mainFrame;
    }

    //==========================================================================
    public void setRightComponent(final Component component) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        mainFrame.getjSplitPaneMain().setRightComponent(component);
                    }
                });
            }
        }).start();
    } // end setRightComponent

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

