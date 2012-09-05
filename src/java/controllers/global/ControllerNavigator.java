package controllers.global;

import controllers.Controller;
import java.awt.Component;
import views.splits.Navigator;

/**
 *
 * @author skuarch
 */
public class ControllerNavigator extends Controller {

    private static final Navigator navigator = new Navigator();

    //==========================================================================
    private ControllerNavigator() {        
    }

    //==========================================================================
    public static ControllerNavigator getInstance() {
        return ControllerNavigatorHolder.INSTANCE;
    }

    //==========================================================================
    private static class ControllerNavigatorHolder {
        private static final ControllerNavigator INSTANCE = new ControllerNavigator();
    }

    //==========================================================================
    public Navigator getNavigator() {
        return navigator;
    }

    //==========================================================================
    public void addTab(Component component) {
        
        if(component == null || component.getName().length() < 1){
            NOTIFICATIONS.error("Imposible add new tab", new Exception("component is null or doesn't have name"));
            return;
        }
        
        try {
            
            navigator.add(component);
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add new tab", e);
        }
        
    } // end addTab

    //==========================================================================
    @Override
    public void setupInterface() {
        
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
