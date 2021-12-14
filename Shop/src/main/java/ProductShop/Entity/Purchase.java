
package ProductShop.Entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
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
    @Temporal(TemporalType.DATE)
    private Date date;
    
    private String code;

    public Purchase(String Id, Integer total, Date date, String code) {
        this.Id = Id;
        this.total = total;
        this.date = date;
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    

    
    
    
}
