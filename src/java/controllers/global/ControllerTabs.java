package controllers.global;

import controllers.Controller;
import java.awt.Component;
import javax.swing.SwingWorker;
import views.splits.Navigator;

/**
 *
 * @author skuarch
 */
public class ControllerTabs extends Controller {

    //==========================================================================
    private ControllerTabs() {
    }

    //==========================================================================
    public static ControllerTabs getInstance() {
        return ControllerTabsHolder.INSTANCE;
    }

    //==========================================================================
    private static class ControllerTabsHolder {

        private static final ControllerTabs INSTANCE = new ControllerTabs();
    }

    //==========================================================================
    public void addTabNavigator(final Component component) {

        if (component == null) {
            NOTIFICATIONS.error("Error adding component, component is null", new NullPointerException());
            return;
        }

        if (component.getName() == null || component.getName().length() < 1) {
            NOTIFICATIONS.error("Error the name of component is null", new NullPointerException());
            return;
        }

        new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                Navigator navigator = new Navigator();

                try {

                    navigator.add(component);

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error adding tab in window", e);
                }
                return null;
            }
        }.execute();

    } // end addTabNavigator 

    //==========================================================================
    @Override
    public void setupInterface() {
        throw new UnsupportedOperationException("Not supported yet.");
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
} // end class
