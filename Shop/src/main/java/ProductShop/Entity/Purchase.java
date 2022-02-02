package ProductShop.Entity;

import ProductShop.Enums.PaymentMethod;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    private Integer purchaseCode;
    private Double total;
    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne
    private Usuario usuario;
    @OneToOne
    private PurchaseDetails purchaseDetail;
    
    public Purchase() {
//        this.purchaseCode = purchaseCode + 1; 
    }
    
    public Purchase(Integer purchaseCode, Double total, PaymentMethod paymentMethod, Date date, Usuario usuario, PurchaseDetails purchaseDetail) {
        this.purchaseCode = purchaseCode + 1;
        this.total = total;
        this.paymentMethod = paymentMethod;
        this.date = date;
        this.usuario = usuario;
        this.purchaseDetail = purchaseDetail;
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

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public PurchaseDetails getPurchaseDetail() {
        return purchaseDetail;
    }

    public void setPurchaseDetail(PurchaseDetails purchaseDetail) {
        this.purchaseDetail = purchaseDetail;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getPurchaseCode() {
        return purchaseCode;
    }

    public void setPurchaseCode(Integer purchaseCode) {
        this.purchaseCode = purchaseCode;
    }

}
