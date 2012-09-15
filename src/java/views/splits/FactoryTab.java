package views.splits;

import controllers.global.ControllerNotifications;
import java.awt.Component;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

/**
 *
 * @author skuarch
 */
public abstract class FactoryTab extends JTabbedPane {

    protected final ControllerNotifications NOTIFICATIONS = new ControllerNotifications();

    //==========================================================================    
    public FactoryTab() {
        super();
    } // end FactoryTab

    //==========================================================================
    /**
     * updateUI with a thread.
     */
    @Override
    public void updateUI() {
        new Thread(new Runnable() {
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    public void run() {
                        FactoryTab.super.updateUI();
                    }
                });
            }
        }).start();
    } // end updateUI
    
} // end class
