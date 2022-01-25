
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
    @GeneratedValue(generator ="uuid")
    @GenericGenerator(name ="uuid",strategy = "uuid2")
    private String Id;
    
    private Integer total;
    
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    
    @Temporal(TemporalType.DATE)
    private Date date;
    
//    mi entidad tiene un total, un ENUM de método de pago, un DOUBLE total, un date , una cantidad, 
//    un OnetoOne de producto,un OnetoOne de usuario(que lo podría sacar) y un OnetoOne de detalle de la compra

    
    //@OneToOne
    //private Detail detail;

    public Purchase(String Id, Integer total, Date date/*,Detail detail*/,PaymentMethod paymentMethod) {
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

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
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
}
