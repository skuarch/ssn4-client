package model.common;

import model.beans.Configuration;
import model.jms.JMSProccessor;
import model.util.FirtsConfiguration;

/**
 *
 * @author skuarch
 */
public class ModelJobs {

    //==========================================================================
    public ModelJobs() {
    } // end ModelJobs

    //==========================================================================
    public String[] getJobs(String collector) throws Exception {

        String[] jobs = null;
        JMSProccessor jmsp = null;
        Configuration configuration = FirtsConfiguration.getFirtsConfiguration();

        try {

            if (collector == null || collector.length() < 1) {
                jobs = new String[1];
                jobs[0] = "no jobs";
            } else {

                jmsp = new JMSProccessor();
                jobs = (String[]) jmsp.sendReceive("getJobs", collector, "srs", configuration.getJmsTimeWaitMessage(), "mocos");

                if (jobs == null || jobs.length < 1) {
                    jobs = new String[1];
                    jobs[0] = "no jobs";
                }
            }

        } catch (Exception e) {
            throw e;
        }

        return jobs;

    } // end getJobs
} // end class
