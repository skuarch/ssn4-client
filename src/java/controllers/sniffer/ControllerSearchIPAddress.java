package controllers.sniffer;

import controllers.Controller;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.DefaultComboBoxModel;
import views.dialogs.SearchIpAddress;

/**
 *
 * @author skuarch
 */
public class ControllerSearchIPAddress extends Controller {

    private SearchIpAddress searchIpAddress = null;
    private DefaultComboBoxModel modelCollectors = null;
    private DefaultComboBoxModel modelJobs = null;

    //==========================================================================
    public ControllerSearchIPAddress() {
        searchIpAddress = new SearchIpAddress(null, false);
    } // end ControllerSearchIPAddress

    //==========================================================================
    @Override
    public void setVisible(boolean flag) {
        searchIpAddress.setVisible(flag);
    } // end showWindow

    //==========================================================================
    @Override
    public void setupInterface() {
        
        try {
            
            searchIpAddress.setTitle("Search IP Address");
            addListeners();
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating interface", e);
        }
        
    } // end setupInterface
    
    //==========================================================================
    private void addListeners(){
    
        try {            
            
            searchIpAddress.getjButtonClose().addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent e) {
                    searchIpAddress.setVisible(false);
                }
            });
            
        } catch (Exception e) {
            NOTIFICATIONS.error("Error creating interface", e);
        }
        
    }

    //==========================================================================
    @Override
    public void destroyCurrentThread() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
    
} // end class

/*
 * 

    //==========================================================================
    public void addDefaultActionListenerComboCollectors() throws Exception {

        comboCollectors.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                String selected = comboCollectors.getSelectedItem().toString();

                try {

                    if (selected.equalsIgnoreCase("no jobs")) {
                        return;
                    }

                    if (selected.equalsIgnoreCase("choose a collector") || selected.toString().equalsIgnoreCase("loading")) {

                        //please select a collector
                        comboJobs.setEnabled(false);
                        setStringModel(comboJobs, new String[]{"please select a collector"});

                    } else {

                        loadJobs();

                    }

                } catch (Exception ex) {
                    new Thrower().exception(ex);
                }

            }
        });

    } // end addDefaultActionListenerComboCollectors    

    //==========================================================================
    public void addDefaultActionListenerComboJobs() throws Exception {

        if (comboCollectors.isEnabled() && comboJobs.isEnabled()) {
            searchIpAddress.setEnableButtonSearch(true);
        }

    } // end addDefaultActionListenerComboJobs

    //==========================================================================
    public void loadCollectors() throws Exception {

        //while the comboCollector is loading jobs the comboJobs isn't enable
        comboJobs.setEnabled(false);
        comboCollectors.setEnabled(false);

        SwingWorker sw = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {

                ArrayList<Collectors> collectors = null;

                try {

                    collectors = new ModelCollectors().getActivesCollectors();
                    modelCollectors = new DefaultComboBoxModel();

                    if (collectors != null && collectors.size() > 0) {

                        modelCollectors.addElement("choose a collector");

                        for (int i = 0; i < collectors.size(); i++) {
                            modelCollectors.addElement(collectors.get(i).getCollectorName());
                        }

                        comboCollectors.setEnabled(true);

                    } else {
                        modelCollectors.addElement("no collectors");
                    }

                    comboCollectors.setModel(modelCollectors);

                } catch (Exception e) {
                    throw e;
                }

                return null;
            }
        };

        sw.execute();

    } // end loadCollectors    

    //==========================================================================
    private void loadJobs() {

        //while the comboJobs is loading jobs the comboCollectors isn't enable
        comboCollectors.setEnabled(false);
        comboJobs.setEnabled(false);

        SwingWorker sw = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {

                String[] jobs = null;

                try {

                    selectedCollector = comboCollectors.getSelectedItem().toString();

                    if (!selectedCollector.equalsIgnoreCase("no collectors") || selectedCollector.equalsIgnoreCase("choose a collector") || selectedCollector.equalsIgnoreCase("loading")) {

                        comboJobs.setEnabled(false);
                        setStringModel(comboJobs, new String[]{"loading"});

                        //get jobs
                        jobs = new ModelJobs().getJobs(selectedCollector);

                        if (jobs == null || jobs[0].equalsIgnoreCase("no jobs")) {
                            comboJobs.setEnabled(false);
                            modelJobs = new DefaultComboBoxModel(jobs);
                            comboJobs.setModel(modelJobs);
                        } else {
                            modelJobs = new DefaultComboBoxModel(jobs);
                            comboJobs.setModel(modelJobs);
                            comboJobs.setEnabled(true);
                        }
                    }

                } catch (Exception e) {
                    throw e;
                } finally {
                    comboCollectors.setEnabled(true);
                }

                return null;
            }
        };

        sw.execute();

    } // end loadJobs

    //==========================================================================
    public void setEnableButtonSearch(boolean flag) {
        searchIpAddress.setEnableButtonSearch(flag);
    } // end setEnableButtonSearch

    //==========================================================================
    public void setEnableComboJobs(boolean flag) {
        comboJobs.setEnabled(flag);
    } // end setEnableComboJobs

    //==========================================================================
    public void setEnableComboCollectors(boolean flag) {
        comboCollectors.setEnabled(flag);
    } // end setEnableComboCollectors

    //==========================================================================
    public void setStringModel(final JComboBox comboBox, final String[] stringModel) throws Exception {

        SwingWorker sw = new SwingWorker<Void, Void>() {

            @Override
            protected Void doInBackground() throws Exception {
                comboBox.setModel(new DefaultComboBoxModel(stringModel));
                return null;
            }
        };
        sw.execute();

    } // end setModel
 */