package controllers.global;

import controllers.sniffer.ControllerTreeCollectors;
import controllers.sniffer.ControllerTreeViews;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import model.common.PanelCreator;
import model.util.JTabPaneUtilities;
import views.splits.Navigator;
import views.splits.SubNavigator;

/**
 * this class is for to handle the controllers,
 * <code>ControllerNavigator</code> and
 * <code>ControllerSubNavigator</code>
 *
 * @author skuarch
 */
public class TabsDriver {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private static final ControllerNavigator CN = ControllerNavigator.getInstance();
    private static final ControllerTreeViews CTV = new ControllerTreeViews();
    private static final ControllerTreeCollectors CTC = new ControllerTreeCollectors();
    private static final ControllerMainFrame CMF = ControllerMainFrame.getInstance();

    //==========================================================================
    /**
     * create a instance.
     */
    public TabsDriver() {
    } // end controllerTabs

    //==========================================================================    
    /**
     * add tab in navigator. the view becomes a
     * <code>Subnavigator</code> and job is the name of the tab.
     *
     * @param view String this String will be become *
     * to <code>SubNavigator</code>
     * @param job String this is the name of the tab
     */
    public void addTabNavigator(final String view, final String job) {

        if (view == null || job == null) {
            NOTIFICATIONS.error("view or job are null", new NullPointerException());
            return;
        }

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {

                JPanel panel = null;
                String panelName = null;
                Navigator navigator = CN.getNavigator();
                SubNavigator subNavigator = null;
                ControllerSubNavigator csn = new ControllerSubNavigator();
                JPanel panelTitleNavigator = null;
                JPanel panelTitleSubNavigator = null;

                try {

                    // crear el hashmap                       

                    //checar si el navegador tiene algo
                    //si no tiene nada se le agrega un subnavigator            
                    if (navigator.getTabCount() < 1) {
                        CMF.setRightComponent(navigator);
                        subNavigator = new SubNavigator();
                    } else {

                        subNavigator = (SubNavigator) CN.getComponent(job, navigator);

                        if (subNavigator == null) {
                            subNavigator = new SubNavigator();
                        }

                    }

                    panelName = JTabPaneUtilities.getPanelName(view, subNavigator);
                    panel = new PanelCreator().getPanel(view, panelName);
                    subNavigator.setName(job);
                    csn.setNavigator(subNavigator);
                    panelTitleSubNavigator = JTabPaneUtilities.getPanelTitle(view, csn.getCloseLabel(panelName, subNavigator));
                    panelTitleSubNavigator.add(JTabPaneUtilities.getLabelComponent(panel, job));
                    csn.addTab(subNavigator, panelName, panel, panelTitleSubNavigator);
                    panelTitleNavigator = JTabPaneUtilities.getPanelTitle(job, CN.getCloseLabel(job, navigator));
                    CN.addTab(navigator, job, subNavigator, panelTitleNavigator);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Unexpected error", e);
                }
            }
        });

    } // end addTabNavigator    
} // end class
