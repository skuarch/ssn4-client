package controllers.global;

import controllers.sniffer.ControllerTreeCollectors;
import controllers.sniffer.ControllerTreeViews;
import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import model.util.JTabPaneUtilities;
import model.util.ValidationUtilities;
import views.splits.SubNavigator;

/**
 *
 * @author skuarch
 */
public class ControllerTabs implements Runnable {

    private static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    private static final ControllerNavigator CN = ControllerNavigator.getInstance();
    private static final ControllerTreeViews CTV = new ControllerTreeViews();
    private static final ControllerTreeCollectors CTC = new ControllerTreeCollectors();
    private static final ControllerMainFrame CMF = ControllerMainFrame.getInstance();
    private String view = null;
    private String collector = null;
    private String job = null;

    //==========================================================================
    public ControllerTabs(String view, String collector, String job) {
        this.view = view;
        this.collector = collector;
        this.job = job;
    } // end controllerTabs

    //==========================================================================
    @Override
    public void run() {

        JPanel panel = null;
        SubNavigator sn = null;
        Component tmpComponent = null;

        try {

            if (!ValidationUtilities.validateClickTree(view, collector, job)) {
                return;
            }

            System.out.println("view: " + view + " collector: " + collector + " job: " + job);
            
            
            // crear el hashmap                       

            //checar si el navegador tiene algo
            //si no tiene nada se le agrega un subnavigator            
            if (CN.getNavigator().getTabCount() < 1) {
                CMF.setRightComponent(CN.getNavigator());
                sn = new SubNavigator();
                sn.setName(job);
                System.out.println("1");
            } else {
                sn = (SubNavigator) CN.getComponent(job, CN.getNavigator());
                System.out.println("2");
            }            

            sn.setName(job);
            sn.addTab(view, new JPanel());
            CN.addTab(CN.getNavigator(), job, sn, CN.getCloseLabel(job, CN.getNavigator()));

        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end run

    //==========================================================================
    /**
     * search if the component exists in navigator if the component doesn't
     * exist this method return null.
     *
     * @param nameComponent String
     * @return Component
     * @throws Exception
     */
    public Component getComponent(String nameComponent, JTabbedPane jTabbedPane) throws Exception {

        Component component = null;

        try {

            component = JTabPaneUtilities.getComponent(jTabbedPane, nameComponent);

        } catch (Exception e) {
            NOTIFICATIONS.error("Error getting the component", e);
        }

        return component;

    } // end getComponent
} // end class
