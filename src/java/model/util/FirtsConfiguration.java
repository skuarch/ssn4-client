package model.util;

import model.beans.Configuration;
import model.dao.DAO;

/**
 *
 * @author skuarch
 */
public class FirtsConfiguration {

    //==========================================================================
    public static Configuration getFirtsConfiguration() throws Exception {

        Configuration configuration = null;

        try {
            configuration = (Configuration) new DAO().get(1, Configuration.class);
        } catch (Exception e) {
            throw e;
        }

        return configuration;
    } // end getFirtsConfiguration
    
} // end class
