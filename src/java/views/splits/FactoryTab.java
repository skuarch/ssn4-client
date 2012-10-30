package views.splits;

import controllers.global.ControllerNotifications;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

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
     * updateUI with thread.
     */
    @Override
    public void updateUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                SwingUtilities.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        FactoryTab.super.updateUI();
                    }
                });
            }
        }).start();
    } // end updateUI
    
} // end class
