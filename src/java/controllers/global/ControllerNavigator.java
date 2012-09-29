package controllers.global;

import javax.swing.SwingWorker;
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
     * return a instance this instance is unique.
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
    
} // end class
