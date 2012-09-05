package controllers;

import controllers.global.ControllerNotifications;

/**
 *
 * @author skuarch
 */
public abstract class Controller {    
    
    protected static final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();
    
    //==========================================================================
    public Controller(){
    } // end Controller
    
    //==========================================================================
    public abstract void setupInterface();   
    
    //==========================================================================
    public abstract void setVisible(boolean flag);
    
    //==========================================================================
    public abstract void destroyCurrentThread();

} // end class
