package model.util;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

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

            //close button
            panelTitle.add(closeLabel);

            //adding label            
            panelTitle.add(new JLabel(title));

        } catch (Exception e) {
            throw e;
        }

        return panelTitle;
    } // end getTabTitle

    //==========================================================================
    /**
     * close tab by name.
     *
     * @param jTabbedPane JTabbedPane
     * @param nameComponent String
     * @throws Exception
     */
    public static void closeTab(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("nameComponent is null");
        }

        Component[] components = null;

        try {

            components = jTabbedPane.getComponents();

            for (int i = 0; i < components.length; i++) {

                if (components[i].getName() == null) {
                    continue;
                }

                if (components[i].getName().equalsIgnoreCase(nameComponent)) {
                    System.out.println("close " + nameComponent + " position " + i);
                    jTabbedPane.remove(jTabbedPane.getComponent(i));
                }
            }

        } finally {
            components = null;
        }

    } // end closeTab

    //==========================================================================
    public static Component getComponent(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("the nameComponent is null");
        }

        Component component = null;
        Component[] components = null;

        try {

            components = jTabbedPane.getComponents();

            if (components == null || components.length < 1) {
                return null;
            }

            for (Component c : components) {

                if (c == null || c.getName() == null || c.getName().length() < 1) {
                    continue;
                }
                System.out.println(c.getName() + " == " + nameComponent);
                if (c.getName().equalsIgnoreCase(nameComponent)) {
                    System.out.println("a huevo");
                    component = c;
                    break;
                }

            }

        } finally {
            components = null;
        }

        return component;

    } // end getComponent

    //==========================================================================
    /**
     * return the position of the component, if the component doesn't exist
     * return -1
     *
     * @param jTabbedPane JTabbedPane
     * @param nameComponent String
     * @return int
     * @throws Exception
     */
    public static int getComponentNum(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("the nameComponent is null");
        }

        Component[] components = null;
        int numComponent = -1;

        try {

            components = jTabbedPane.getComponents();

            for (int i = 0; i < components.length; i++) {
                if (components[i].getName() == null) {
                    continue;
                }

                if (components[i].getName().equalsIgnoreCase(nameComponent)) {
                    numComponent = i;
                    break;
                }
            }

        } finally {
            components = null;
        }

        return numComponent;

    } // end getComponentNum

    //==========================================================================
    /**
     * return a num with the name of components.
     *
     * @param jTabbedPane
     * @param nameComponent
     * @return
     * @throws Exception
     */
    public static int componentsSameName(JTabbedPane jTabbedPane, String nameComponent) throws Exception {

        if (jTabbedPane == null) {
            throw new NullPointerException("jTabbedPane is null");
        }

        if (nameComponent == null || nameComponent.length() < 1) {
            throw new NullPointerException("the nameComponent is null");
        }

        Component[] components = null;
        int nComponent = 0;
        String tmpName = null;

        try {

            components = jTabbedPane.getComponents();

            for (int i = 0; i < components.length; i++) {

                tmpName = components[i].getName();

                if (tmpName == null) {
                    continue;
                }

                if (tmpName.contains(nameComponent)) {
                    nComponent++;
                }

                tmpName = null;
            }

        } finally {
            components = null;
            tmpName = null;
        }

        return nComponent;

    } // end componentsSameName
} // end class
