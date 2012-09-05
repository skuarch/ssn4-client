package model.beans;

import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name = "collectors")
public class Collectors implements Serializable {

    @Id
    @Column(name="collector_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;    
    @Column(name = "collector_name")
    private String collectorName;
    @Column(name = "collector_type")
    private int collectorType;
    @Column(name = "collector_ip")
    private String collectorIp;
    @Column(name = "collector_is_active")
    private int collectorIsActive;    
    
    //==========================================================================
    public Collectors(){

    } // end Collectors

    public String getCollectorIp() {
        return collectorIp;
    }

    public void setCollectorIp(String collectorIp) {
        this.collectorIp = collectorIp;
    }

    public int getCollectorIsActive() {
        return collectorIsActive;
    }

    public void setCollectorIsActive(int collectorIsActive) {
        this.collectorIsActive = collectorIsActive;
    }

    public String getCollectorName() {
        return collectorName;
    }

    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }

    public int getCollectorType() {
        return collectorType;
    }

    public void setCollectorType(int collectorType) {
        this.collectorType = collectorType;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    
} // end class
