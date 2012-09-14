package model.util;

import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Utilities for JTabPane.
 *
 * @author skuarch
 */
public class JTabPaneUtilities {

    //==========================================================================
    /**
     * create a panel with a close button.<br/> this method is used for to put a
     * <code>JPanel</code> in a tab.
     *
     * @param title String
     * @param closeLabel JLabel
     * @return JPanel
     * @throws Exception
     */
    public static JPanel getPanelTitle(String title, JLabel closeLabel) throws Exception {

        if (title == null || title.equalsIgnoreCase("")) {
            throw new Exception("title is empty or null");
        }

        JPanel panelTitle = new JPanel();

        try {

            panelTitle.setOpaque(false);
            
            panelTitle.setPreferredSize(new Dimension(title.length() * 8, 22));
            
            //close button
            panelTitle.add(closeLabel);

            //adding label            
            panelTitle.add(new JLabel(title));

        } catch (Exception e) {
            throw e;
        }

        return panelTitle;
    } // end getTabTitle
} // end class
