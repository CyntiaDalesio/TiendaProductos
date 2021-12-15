package ProductShop.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class PurchaseDetails implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idDetails;
    private Double priceUnit;
    private Double subtotal;
    private Integer cantity;
    @OneToOne
    private Product product;

    public PurchaseDetails() {
    }

    public PurchaseDetails(Double priceUnit, Integer cantity, Product product) {
        this.priceUnit = priceUnit;
        this.cantity = cantity;
        this.product = product;
    }

    public String getIdDetails() {
        return idDetails;
    }

    public void setIdDetails(String idDetails) {
        this.idDetails = idDetails;
    }

    public Double getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Double priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getCantity() {
        return cantity;
    }

    public void setCantity(Integer cantity) {
        this.cantity = cantity;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(Double subtotal) {
        this.subtotal = subtotal;
    }
    
    
    

}
