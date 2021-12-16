
package ProductShop.Entity;

import ProductShop.Enums.PaymentMethod;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class Purchase {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String Id;

    private Double total;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Temporal(TemporalType.DATE)
    private Date date;
    
    private String code;

    private Integer quantity;

    @OneToOne
    private Product product;

    
    //agreagar cuando iniciemos carrito
    //@OneToOne
    //private Detail detail;
    
    
    public Purchase(String Id, Double total, Date date/*,Detail detail*/, PaymentMethod paymentMethod) {
        this.Id = Id;
        this.total = total;
        this.date = date;
        //this.detail=detail;
        this.paymentMethod = paymentMethod;
    }

    public Purchase() {
    }

    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    /*public void getDetail(){
      return detail;
    }
    
    public void setDetail(Detail detail){
    this.detail=detail;
    }
     */
    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    
    
    public Integer getQuantity() {
        return quantity;
    }

    
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    
    public Product getProduct() {
        return product;
    }

    
    public void setProduct(Product product) {
        this.product = product;
    }

    
    public String getCode() {
        return code;
    }

    
    public void setCode(String code) {
        this.code = code;
    }
}
