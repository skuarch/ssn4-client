package views.splits;

import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;

/**
 *
 * @author skuarch
 */
public abstract class FactoryTab extends JTabbedPane {

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
