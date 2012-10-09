package controllers.global;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingWorker;
import model.util.JTabPaneUtilities;
import views.splits.Navigator;

/**
 * Controller for Navigator view.
 *
 * @author skuarch
 */
public class ControllerNavigator extends FabricTabs {

    private static final Navigator navigator = new Navigator();

    //==========================================================================
    /**
     * create a instance.
     */
    private ControllerNavigator() {
    } // end ControllerNavigator

    //==========================================================================
    /**
     * return a instance, this instance is unique.
     *
     * @return ControllerNavigator
     */
    public static ControllerNavigator getInstance() {
        return ControllerNavigatorHolder.INSTANCE;
    } // end ControllerNavigator

    //==========================================================================
    private static class ControllerNavigatorHolder {

        private static final ControllerNavigator INSTANCE = new ControllerNavigator();
    } // end ControllerNavigatorHolder

    //==========================================================================
    /**
     *
     * @param component Component
     * @param tabName String with the name of the tab, this name maybe is a job.
     */
    public void addTab(Component component, String tabName) {

        try {
            JLabel label = JTabPaneUtilities.getLabelComponent(component, tabName);
            JPanel panelTitle = JTabPaneUtilities.getPanelTitle(tabName, label);
            super.addTab(navigator, tabName, component, panelTitle);
        } catch (Exception e) {
            NOTIFICATIONS.error("Error adding tab", e);
        }
    }

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
     * close all tabs in navigator.
     */
    @Override
    public void closeAllTabs() {
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

    } // end closeAllTabs

    //==========================================================================    
    public void checkTabs(String tabName) {

        Component component = null;
        JTabbedPane jTabbedPane = null;

        try {

            component = getComponent(tabName, navigator);

            if (component != null) {
                jTabbedPane = (JTabbedPane) component;
                if (jTabbedPane.getTabCount() < 2) {
                    closeTab(tabName, navigator);
                }

            }

        } catch (Exception e) {
            NOTIFICATIONS.error("Error checking tabs", e);
        }
    } // end checkTabs

    //==========================================================================
    /**
     * return the components for jTabbedpane
     *
     * @return Component[] the components of the navigator.
     */
    public Component[] getComponents() {

        Component[] components = null;

        try {

            components = navigator.getComponents();

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexcepted error", e);
        }

        return components;

    } // end getComponents

    //==========================================================================    
    /**
     * close tab in navigatior
     *
     * @param nameComponent String with the name of tab (subNavigator).
     */
    public void closeTab(String nameComponent) {

        try {
            super.closeTab(nameComponent, navigator);
        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible close the tab", e);
        }

    } // end closeTab
    
} // end class
