package controllers.global;

import javax.swing.SwingWorker;
import views.splits.SubNavigator;

/**
 * controller for view SubNavigator
 *
 * @author skuarch
 */
public class ControllerSubNavigator extends FabricTabs {

    private SubNavigator subNavigator = new SubNavigator();

    //==========================================================================
    /**
     * create a instance.
     */
    public ControllerSubNavigator() {
    }

    //==========================================================================
    public SubNavigator getSubNavigator() {
        return subNavigator;
    }

    //==========================================================================
    @Override
    public void closeAllTabs() {
        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                try {
                    subNavigator.removeAll();
                } catch (Exception e) {
                    NOTIFICATIONS.error("Error closing tabs", e);
                }

                return null;
            }
        }.execute();
    } // end closeAllTabs
} // end class

