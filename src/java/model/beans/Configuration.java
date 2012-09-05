package model.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "configuration")
public class Configuration implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuration")
    private long id;
    @Column(name = "jws_path")
    private String JWSPath;
    @Column(name = "help_path")
    private String HelpPath;
    @Column(name = "project_name")
    private String projectName;
    @Column(name = "jms_time_wait_message")
    private int jmsTimeWaitMessage;
    @Column(name = "jms_time_wait_connectivity")
    private int jmsTimeWaitConnectivity;
    @Column(name = "window_title")
    private String windowTitle;

    //==========================================================================
    public Configuration() {
    } // end Configuration

    //==========================================================================
    public String getHelpPath() {
        return HelpPath;
    }

    //==========================================================================
    public void setHelpPath(String HelpPath) {
        this.HelpPath = HelpPath;
    }

    //==========================================================================
    public String getJWSPath() {
        return JWSPath;
    }

    //==========================================================================
    public void setJWSPath(String JWSPath) {
        this.JWSPath = JWSPath;
    }

    //==========================================================================
    public long getId() {
        return id;
    }

    //==========================================================================
    public void setId(long id) {
        this.id = id;
    }

    //==========================================================================
    public int getJmsTimeWaitConnectivity() {
        return jmsTimeWaitConnectivity;
    }

    //==========================================================================
    public void setJmsTimeWaitConnectivity(int jmsTimeWaitConnectivity) {
        this.jmsTimeWaitConnectivity = jmsTimeWaitConnectivity;
    }

    //==========================================================================
    public int getJmsTimeWaitMessage() {
        return jmsTimeWaitMessage;
    }

    //==========================================================================
    public void setJmsTimeWaitMessage(int jmsTimeWaitMessage) {
        this.jmsTimeWaitMessage = jmsTimeWaitMessage;
    }

    //==========================================================================
    public String getProjectName() {
        return projectName;
    }

    //==========================================================================
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    //==========================================================================
    public String getWindowTitle() {
        return windowTitle;
    }

    //==========================================================================
    public void setWindowTitle(String windowTitle) {
        this.windowTitle = windowTitle;
    }
} // end class
