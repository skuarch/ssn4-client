package controllers.ssn;

import controllers.Controller;
import controllers.global.ControllerLogin;
import controllers.global.ControllerSplashScreen;
import javax.annotation.Resource;
import javax.jms.Topic;
import javax.jms.TopicConnectionFactory;
import javax.sql.DataSource;
import javax.swing.JFrame;
import javax.swing.UIManager;
import views.dialogs.EventViewer;

/**
 *
 * @author skuarch
 */
public class ControllerMain extends Controller {

    @Resource(mappedName = "jms_connection_factory_ssn_4")
    private static TopicConnectionFactory topicConnectionFactory;
    @Resource(mappedName = "jms_topic_ssn_4")
    private static Topic topic;
    @Resource(mappedName = "jdbc/ssn_4")
    private static DataSource dataSource;

    //==========================================================================
    public static DataSource getDataSource() {
        return dataSource;
    }

    //==========================================================================
    public static Topic getTopic() {
        return topic;
    }

    //==========================================================================
    public static TopicConnectionFactory getTopicConnectionFactory() {
        return topicConnectionFactory;
    }

    //==========================================================================
    public ControllerMain() {
    } // end Main

    //==========================================================================
    public static void main(String[] args) {

        try {
            new ControllerMain().setupInterface();
        } catch (Exception e) {
            e.printStackTrace();
        }

    } // end main
    //==========================================================================
    /**
     * This method displays a message before the program ends.
     */
    private Thread shutdownThread = new Thread() {

        //======================================================================
        @Override
        public void run() {
            //on exit            
            EventViewer.getInstance().appendInfoTextConsole("** program finished **");
        } // end run
    }; //end shutdownThread

    //==========================================================================
    @Override
    public void setupInterface() {

        ControllerSplashScreen css = null;
        ControllerLogin cl = null;

        try {

            Runtime.getRuntime().addShutdownHook(new ControllerMain().shutdownThread);

            //setting theme
            JFrame.setDefaultLookAndFeelDecorated(true);

            for (UIManager.LookAndFeelInfo laf : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(laf.getName())) {
                    UIManager.setLookAndFeel(laf.getClassName());
                }
            }

            //show splashscreen
            css = new ControllerSplashScreen();
            css.setupInterface();

            //show login
            cl = new ControllerLogin();
            cl.setupInterface();
            cl.setVisible(true);

        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating interface", e);
        } finally {
            css = null;
            cl = null;
        }

    } // end setupInterface

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end stopThread

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        throw new UnsupportedOperationException("Not supported yet.");
    } // end setVisible
} // end class
