package controllers.global;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import model.util.JTabPaneUtilities;

/**
 *
 * @author skuarch
 */
public abstract class FabricTabs {

    protected static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();    
    public abstract void closeAllTabs();

    //==========================================================================
    /**
     * add tab with close label in jTabbedPane.
     *
     * @param jTabbedPane
     * @param string
     * @param component
     * @param closeLabel
     */
    public void addTab(final JTabbedPane jTabbedPane, final String string, final Component component, final JLabel closeLabel) {

        if (string == null || string.length() < 1) {
            NOTIFICATIONS.error("Imposible add new tab", new Exception("string is null"));
            return;
        }

        if (component == null || component.getName() == null || component.getName().length() < 1) {
            NOTIFICATIONS.error("Imposible add new tab", new Exception("component is null or doesn't have name"));
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JPanel panelTitle = null;

                try {

                    panelTitle = JTabPaneUtilities.getPanelTitle(string, closeLabel);
                    component.setName(string);
                    jTabbedPane.addTab(string, component);
                    jTabbedPane.setTabComponentAt(jTabbedPane.getTabCount() - 1, panelTitle);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Imposible add new tab", e);
                }
            }
        });

    } // end addTab

    //==========================================================================    
    /**
     * close tab by name.
     *
     * @param tabName String
     */
    public void closeTab(final String nameComponent, final JTabbedPane jTabbedPane) {

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {

                    JTabPaneUtilities.closeTab(jTabbedPane, nameComponent);
                    System.out.println("closing " + nameComponent);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error closing tab", e);
                }

                return null;
            }
        }.execute();

    } // end closeTab

    //==========================================================================
    /**
     * search if the component exists in navigator if the component doesn't
     * exist this method return null.
     *
     * @param nameComponent String
     * @return Component
     * @throws Exception
     */
    public Component getComponent(String nameComponent, JTabbedPane jTabbedPane) {

        Component component = null;

        try {

            component = JTabPaneUtilities.getComponent(jTabbedPane, nameComponent);

        } catch (Exception e) {
            NOTIFICATIONS.error("Error getting the component", e);
        }

        return component;

    } // end getComponent

    //==========================================================================
    /**
     * label for close tab.
     *
     * @param nameComponent String
     * @return JLabel
     */
    public JLabel getCloseLabel(final String nameComponent, final JTabbedPane jTabbedPane) {

        JLabel closeLabel = null;

        try {

            closeLabel = new JLabel(new ImageIcon(getClass().getResource("/views/images/close.png")));

            closeLabel.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FabricTabs.this.closeTab(nameComponent, jTabbedPane);
                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating close label", e);
        }

        return closeLabel;

    } // end getCloseLabel
    
} // end class
