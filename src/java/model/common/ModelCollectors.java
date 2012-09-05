package model.common;

import java.util.ArrayList;
import model.beans.Collectors;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class ModelCollectors {

    //==========================================================================
    public ModelCollectors() {
    } // end ModelCollectors

    //==========================================================================
    public ArrayList<Collectors> getCollectors() throws Exception {

        ArrayList<Collectors> collectors = null;

        try {

            collectors = (ArrayList<Collectors>) new DAO().getAll("Collectors");

        } catch (Exception e) {
            throw e;
        }

        return collectors;

    } // end getCollectors

    //==========================================================================
    public ArrayList<Collectors> getActivesCollectors() throws Exception {
        
        ArrayList<Collectors> collectors = null;
        ArrayList<Collectors> activeCollectors = null;

        try {

            collectors = getCollectors();

            if (collectors.size() > 0) {
                activeCollectors = new ArrayList<Collectors>();

                for (int i = 0; i < collectors.size(); i++) {

                    if (collectors.get(i).getCollectorIsActive() == 1) {
                        activeCollectors.add(collectors.get(i));
                    }
                }
            }

        } catch (Exception e) {
            throw e;
        } finally {
            collectors.clear();
            collectors = null;
        }

        return activeCollectors;

    } // end getActivesCollectors
} // end class
