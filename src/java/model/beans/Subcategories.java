package model.beans;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author skuarch
 */
@Entity
@Table(name="subcategories")
public class Subcategories implements Serializable {
    
    @Id
    @Column(name="subcategorie_id")
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long subcategorieId;
    
    @Column(name="categorie_active")
    private int categorieIsActive;
    
    @Column(name="subcategorie_name")
    private String subcategorieName;
    
    @Column(name="subcategorie_categorie_id")
    private int categorieId;
    
    @Column(name="subcategorie_order")
    private int subcategorieOrder;
    
    @Column(name="subcategorie_chart_type")
    private int subcategorieChartType;
    
    //==========================================================================
    public Subcategories(){    
    } // end Subcategories

    public long getSubcategorieId() {
        return subcategorieId;
    }

    public void setSubcategorieId(long subcategorieId) {
        this.subcategorieId = subcategorieId;
    }

    public int getCategorieIsActive() {
        return categorieIsActive;
    }

    public void setCategorieIsActive(int categorieIsActive) {
        this.categorieIsActive = categorieIsActive;
    }

    public String getSubcategorieName() {
        return subcategorieName;
    }

    public void setSubcategorieName(String subcategorieName) {
        this.subcategorieName = subcategorieName;
    }

    public int getCategorieId() {
        return categorieId;
    }

    public void setCategorieId(int categorieId) {
        this.categorieId = categorieId;
    }

    public int getSubcategorieOrder() {
        return subcategorieOrder;
    }

    public void setSubcategorieOrder(int subcategorieOrder) {
        this.subcategorieOrder = subcategorieOrder;
    }

    public int getSubcategorieChartType() {
        return subcategorieChartType;
    }

    public void setSubcategorieChartType(int subcategorieChartType) {
        this.subcategorieChartType = subcategorieChartType;
    }    
    
} // end class
