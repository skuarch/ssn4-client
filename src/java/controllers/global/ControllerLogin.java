package controllers.global;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.SwingWorker;
import model.autentication.Autenticate;
import model.beans.CurrentUser;
import model.beans.Users;
import views.frames.Login;

/**
 *
 * @author skuarch
 */
public class ControllerLogin extends Controller {

    private Login login = null;
    private SwingWorker sw = null;

    //==========================================================================
    public ControllerLogin() {
        login = new Login();
    } // end ControllerLogin

    //==========================================================================
    @Override
    public void setupInterface() {

        sw = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {

                try {

                    login.setMessage("welcome to sispro sniffer network");
                    addListeners();

                } catch (Exception e) {
                    NOTIFICATIONS.error("Error creating window", e);
                }

                return null;

            }
        };
        sw.execute();

    } // end setupInterface  

    //==========================================================================
    private void addListeners() {

        try {

            login.getLoginButton().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    
                    authentication();

                }
            });

        } catch (Exception e) {
            NOTIFICATIONS.error("Imposible add listeners", e);
        }

    } // end addListeners

    //==========================================================================
    private void authentication() {
        
        login.setMessage("wait...");
        login.setEnabled(false);
        
        String userName = login.getUser();
        char[] password = login.getPassword();
        ArrayList arrayList = null;
        String message = null;
        boolean flag = false;
        CurrentUser user = CurrentUser.getInstance();
        Users tmpUsers = null;
        ControllerMainFrame cmf = ControllerMainFrame.getInstance();
        
        
        try {
            
            arrayList = new Autenticate().doLogin(userName, password);

            flag = (Boolean) arrayList.get(0);

            if (!flag) {
                message = (String) arrayList.get(1);
                login.setMessage(message);
                login.setUser("");
                login.setPassword("");
            } else {

                message = (String) arrayList.get(1);
                login.setMessage(message);
                login.setVisible(false);

                //create user
                tmpUsers = (Users) arrayList.get(2);
                user.setName(userName);
                user.setLevel(tmpUsers.getLevel());

                cmf.setupInterface();
                cmf.setVisible(true);
            }           
            
        } catch (Exception ex) {
            NOTIFICATIONS.error("Unexpected error", ex);
        } finally {
            login.setEnabled(true);
        }

    } // end authentication

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        login.setVisible(flag);
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {

        try {
            login = null;
            sw = null;
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        }

    } // end destroyCurrentThread

    //==========================================================================
    @Override
    protected void finalize() throws Throwable {

        try {
            NOTIFICATIONS.information("clean up ControllerLogin", false);
            destroyCurrentThread();
        } catch (Exception e) {
            NOTIFICATIONS.error("Unexpected error", e);
        } finally {
            super.finalize();
        }

    } // end finalize
} // end class
