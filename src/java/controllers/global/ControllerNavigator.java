package controllers.global;

import controllers.Controller;
import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingWorker;
import model.util.JTabPaneUtilities;
import views.splits.Navigator;

/**
 * Controller for Navigator.
 *
 * @author skuarch
 */
public class ControllerNavigator extends Controller {

    private static final Navigator navigator = new Navigator();

    //==========================================================================
    /**
     * create a instance.
     */
    private ControllerNavigator() {
    } // end ControllerNavigator

    //==========================================================================
    public static ControllerNavigator getInstance() {
        return ControllerNavigatorHolder.INSTANCE;
    } // end ControllerNavigator

    //==========================================================================
    private static class ControllerNavigatorHolder {

        private static final ControllerNavigator INSTANCE = new ControllerNavigator();
    } // end ControllerNavigatorHolder

    //==========================================================================
    /**
     * return the instance of Navigator.
     *
     * @return Navigator
     */
    public Navigator getNavigator() {
        return navigator;
    } // end getNavigator

    //==========================================================================
    /**
     * add tab in navigator.
     *
     * @param component Component
     */
    public void addTab(final String string, final Component component) {

        if (string == null || string.length() < 1) {
            NOTIFICATIONS.error("Imposible add new tab", new Exception("string is null"));
            return;
        }

        if (component == null || component.getName().length() < 1) {
            NOTIFICATIONS.error("Imposible add new tab", new Exception("component is null or doesn't have name"));
            return;
        }

        //----------------------------------------------------------------------
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                JPanel panelTitle = null;

                try {

                    panelTitle = JTabPaneUtilities.getPanelTitle(string, getCloseLabel(string));
                    
                    navigator.addTab(string, component);
                    navigator.setTabComponentAt(navigator.getTabCount() - 1, panelTitle);
                    navigator.setSelectedIndex(navigator.getTabCount() - 1);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Imposible add new tab", e);
                }

                return null;
            }
        }.execute();

    } // end addTab

    //==========================================================================
    /**
     * close all tabs in navigator.
     */
    public void closeTabs() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {
                    navigator.removeAll();
                } catch (Exception e) {
                    NOTIFICATIONS.error("Error closing tabs", e);
                }

                return null;
            }
        }.execute();
    } // end closeTabs

    //==========================================================================
    /**
     * label for close tab.
     *
     * @param nameComponent String
     * @return JLabel
     */
    private JLabel getCloseLabel(final String nameComponent) {

        JLabel closeLabel = null;

        try {

            closeLabel = new JLabel(new ImageIcon(getClass().getResource("/views/images/close.png")));

            closeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    closeTab(nameComponent);
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating close label", e);
        }

        return closeLabel;
    }

    //==========================================================================
    /**
     * close tab by name.
     *
     * @param tabName String
     */
    public void closeTab(String nameComponent) {
        System.out.println("closing tab");
    } // end closeTab

    //==========================================================================
    @Override
    public void setupInterface() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end setupInterface

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end setVisible

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end destroyCurrentThread
} // end class
