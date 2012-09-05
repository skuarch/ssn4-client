package controllers.global;

import controllers.Controller;
import model.dao.DAO;
import model.jms.JMSProccessor;
import views.dialogs.EventViewer;
import views.dialogs.SearchIpAddress;
import views.frames.MainFrame;
import views.splashscreen.SplashScreen;

/**
 *
 * @author skuarch
 */
public class ControllerSplashScreen extends Controller {

    private SplashScreen ss = null;
    private JMSProccessor jMSProccessor = null;
    private EventViewer eventViewer = null;
    private ControllerLogin cl = null;   
    private MainFrame mainFrame = null;
    private SearchIpAddress searchIpAddress = null;

    //==========================================================================
    public ControllerSplashScreen() {
        ss = new SplashScreen();
    } // end ControllerSplashScreen

    //==========================================================================
    @Override
    public void setupInterface() {
        
        try {

            ss.setVisible(true);
            loader();
            ss.setVisible(false);

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible show splash screen", e);
        }

    } // end setupInterface
    
    //==========================================================================
    private void loader() throws Exception {

        try {

            new DAO().getAll("Collectors");

            jMSProccessor = new JMSProccessor();
            jMSProccessor.send("test message", "test message", "test message", "test message", "test message", "test message");           

            mainFrame = new MainFrame();
            mainFrame.setVisible(false);
            
            searchIpAddress = new SearchIpAddress(null, false);
            searchIpAddress.setVisible(false);
            
            eventViewer = EventViewer.getInstance();
            eventViewer.setVisible(false);
            
            cl = new ControllerLogin();
            cl.setupInterface();
            cl.setVisible(false);

        } catch (Exception e) {
            NOTIFICATIONS.error("Somethig is wrong in loader,<br>-Please report to administrator", e);
        } finally {
            jMSProccessor = null;
            eventViewer = null;            
            cl = null;
            mainFrame = null;
        }

    } // end loader

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        try {
            ss = null;
            jMSProccessor = null;
            eventViewer = null;            
            cl = null;
            mainFrame = null;
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }
    }

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {

        try {
            NOTIFICATIONS.information("clean up ControllerSplashScreen", false);
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        } finally {
            super.finalize();
        }

    } // end finalize
    
} // end class
