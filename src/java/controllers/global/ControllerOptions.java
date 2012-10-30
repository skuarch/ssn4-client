package controllers.global;

import controllers.Controller;
import views.dialogs.Options;

/**
 *
 * @author skuarch
 */
public class ControllerOptions extends Controller {
    
    private Options options = new Options(null, true);
    private Thread thread = null;

    //==========================================================================
    @Override
    public void setupInterface() {
        
        try {
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
        
    } // end setupInterface
    
    

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        options.setVisible(true);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        options = null;
    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {
        
        try {
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("error trying clean", e);
        } finally {
            super.finalize();
        }
    }
} // end 

