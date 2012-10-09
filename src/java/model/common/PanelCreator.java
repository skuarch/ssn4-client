package model.common;

import java.awt.BorderLayout;
import java.util.ArrayList;
import javax.swing.JPanel;
import model.beans.Subcategories;
import model.dao.DAO;

/**
 * create a panels depending of string.
 *
 * @author skuarch
 */
public class PanelCreator {

    //==========================================================================
    public PanelCreator() {
    } // end PanelCreator

    //==========================================================================
    public JPanel getPanel(String string, String panelName) throws Exception {

        JPanel panel = new JPanel();
        ArrayList<Subcategories> scl = null;

        try {
            
            panel.setLayout(new BorderLayout());
            
            if(panelName != null || panelName.length() > 0){
                panel.setName(panelName);
            }
            
            scl = (ArrayList<Subcategories>) new DAO().hsql("from Subcategories where subcategorie_name = '" + string + "'");            
        } finally {
            scl = null;
        }

        return panel;

    } // end getPanel
} // end class
