package model.common;

import model.beans.ClusterSniffer;
import model.beans.Configuration;
import model.jms.JMSProccessor;
import model.util.ClusterUtilities;

/**
 *
 * @author skuarch
 */
public class Connectivity {

    //==========================================================================
    public Connectivity() throws Exception {
    } // end Connectivity    

    //==========================================================================
    public boolean requestConnectivity(String host) throws Exception {

        if (host == null || host.length() < 1) {
            throw new NullPointerException("host is empty or null");
        }

        Configuration configuration = null;
        boolean flag = false;
        int time = 0;
        Object object = null;
        ClusterSniffer clusterSniffer = new ClusterSniffer();

        try {
            
            configuration = new ModelConfiguration().getInitialConfiguration();
            time = configuration.getJmsTimeWaitConnectivity();
            object = new JMSProccessor().sendReceive("connectivity", host, "srs", time, ClusterUtilities.getHashMap(clusterSniffer));

            if (object != null) {
                flag = (Boolean) object;
            }

        } catch (Exception e) {
            throw e;
        } finally {
            configuration = null;
            return flag;
        }

    } // end requestConnectivity
    
} // end class
