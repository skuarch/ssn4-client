package model.util;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTree;
import javax.swing.JViewport;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.tree.TreePath;
import model.beans.Collectors;
import model.common.ModelCollectors;

/**
 *
 * @author skuarch
 */
public class ViewUtilities {

    //==========================================================================
    public static JButton getShutdownButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/shutdown.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getShutdownButton

    //==========================================================================
    public static JButton getCleanButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/clear.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getClearButton

    //==========================================================================
    public static JButton getOnTopButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/onTop.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getOnTopButton

    //==========================================================================
    public static JButton getRefreshButton() {
        JButton button = new JButton();
        button.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/refresh.png")));
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        return button;
    } // end getRefreshButton

    //==========================================================================
    public static JButton getFloatButton() {
        JButton buttonFloat = new JButton();
        buttonFloat.setPreferredSize(new Dimension(10, 10));
        buttonFloat.setBackground(Color.blue);
        return buttonFloat;
    } // end getFloatButton

    //==========================================================================
    public static JButton getNextButton() {
        JButton next = new JButton();
        next.setPreferredSize(new Dimension(25, 25));
        next.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/next.png")));
        return next;
    } // end getNextButton

    //==========================================================================
    public static JButton getBackButton() {
        JButton back = new JButton();
        back.setPreferredSize(new Dimension(25, 25));
        back.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/back.png")));
        return back;
    } // end getBackButton

    //==========================================================================
    public static JButton getOpenButton() {
        JButton open = new JButton();
        open.setPreferredSize(new Dimension(18, 18));
        open.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/newWindow.png")));
        return open;
    } // end getOpenButton    

    //==========================================================================
    public static JLabel getWhiteLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.BLUE);
        return label;
    } // end getWhiteLabel

    //==========================================================================
    public static JButton getExportExcelButton() {
        JButton export = new JButton("");
        export.setToolTipText("export to excel");
        export.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/table-export.png")));
        return export;
    }

    //==========================================================================
    public static JButton getTableButton() {
        JButton table = new JButton("");
        table.setToolTipText("show table");
        table.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/table.png")));
        return table;
    }

    //==========================================================================
    public static JButton getDetailButton() {
        JButton detail = new JButton("");
        detail.setToolTipText("details");
        detail.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/detail.png")));
        return detail;
    }

    //==========================================================================
    public static JButton getPdfButton() {
        JButton pdf = new JButton("");
        pdf.setToolTipText("pdf");
        pdf.setIcon(new ImageIcon(ViewUtilities.class.getResource("/views/images/pdf.png")));
        return pdf;
    } // end getPdfButton

    //==========================================================================
    public static JList getListLineChart() {
        JList list = new JList();
        list.setLayoutOrientation(JList.VERTICAL_WRAP);
        list.setVisibleRowCount(-1);
        list.setBackground(Color.WHITE);
        list.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        return list;
    } // end getListVertical

    //==========================================================================
    public static JList getListBarChart() {
        JList list = new JList();
        list.setVisibleRowCount(-1);
        list.setBackground(Color.WHITE);
        return list;
    } // end getListHorinzontal

    //==========================================================================
    public static DefaultComboBoxModel getDefaultComboBoxModelCollectors() throws Exception {

        DefaultComboBoxModel defaultComboBoxModel = null;
        String[] collectors = new String[]{"no collectors"};
        ArrayList<Collectors> servers = null;

        try {

            servers = new ModelCollectors().getActivesCollectors();

            if (servers.size() > 0) {

                collectors = new String[servers.size() + 1];
                collectors[0] = "select a collector";

                for (int i = 1; i < collectors.length; i++) {
                    collectors[i] = servers.get(i - 1).getCollectorName();
                }

            } else {

                collectors = new String[]{"no collectors"};

            }

        } catch (Exception e) {
            throw e;
        }

        return defaultComboBoxModel = new DefaultComboBoxModel(collectors);

    } // end getComboBoxServers

    //==========================================================================
    public static JTree getJtreeFromJTabPane(JTabbedPane jTabbedPane) throws Exception {

        if(jTabbedPane == null || jTabbedPane.getComponents().length < 1){
            throw new Exception("jTabbedPane is null or doesn't have components");
        }
        
        JPanel panel = null;
        JScrollPane scrollPane = null;
        JViewport viewport = null;
        JTree jtree = null;

        try {

            panel = (JPanel) jTabbedPane.getComponentAt(0);
            scrollPane = (JScrollPane) panel.getComponent(1);
            viewport = (JViewport) scrollPane.getComponent(0);
            jtree = (JTree) viewport.getComponent(0);

        } catch (Exception e) {
            throw e;
        }

        return jtree;
    } // end getJtreeFromJTabPane
    
    //==========================================================================
    public static String getSelectedJTree(JTree jTree) throws Exception{
    
        if(jTree == null){
            throw new Exception("jTree is null");
        }
        
        String selected = null;
        TreePath treePath = null;
        
        try {
            
            treePath = jTree.getSelectionPath();            
            if(treePath == null){
                return null;
            }else{
                selected = treePath.getLastPathComponent().toString();
            }
            
        } catch (Exception e) {
            throw e;
        }
        
        return selected;
        
    } // end getSelectedJTree
    
} // end class
