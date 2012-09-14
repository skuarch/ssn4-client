package views.splits;

import java.awt.Component;
import javax.swing.JTabbedPane;

/**
 *
 * @author skuarch
 */
public class Navigator extends FactoryTab {
    
    //==========================================================================
    public Navigator(){
        super();
        setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
    }

    //==========================================================================
    @Override
    public void addTab(String title, Component component) {
        super.addTab(title, component);
    }
    
} // end class
