package ProductShop.Entity;


import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity

public class Product implements Serializable {
   @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name= "uuid",strategy = "uuid2")
    private String idProduct;

    private Integer CodeProduct;
    private String Name;
    private Double Price;
    private String TradeMark;
    private String Category;
    private Integer Stock;
    private Boolean AvailableStock;

    public Product() {
    }

    public Product(String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, String Category, Integer Stock) {
        this.idProduct = idProduct;
        this.CodeProduct = CodeProduct;
        this.Name = Name;
        this.Price = Price;
        this.TradeMark = TradeMark;
        this.Category = Category;
        this.Stock = Stock;
    }

    public String getIdProduct() {
        return idProduct;
    }

    public void setIdProduct(String idProduct) {
        this.idProduct = idProduct;
    }

    public Integer getCodeProduct() {
        return CodeProduct;
    }

    public void setCodeProduct(Integer CodeProduct) {
        this.CodeProduct = CodeProduct;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public Double getPrice() {
        return Price;
    }

    public void setPrice(Double Price) {
        this.Price = Price;
    }

    public String getTradeMark() {
        return TradeMark;
    }

    public void setTradeMark(String TradeMark) {
        this.TradeMark = TradeMark;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public Integer getStock() {
        return Stock;
    }

    public void setStock(Integer Stock) {
        this.Stock = Stock;
    }

    public Boolean getAvailableStock() {
        return AvailableStock;
    }

    public void setAvailableStock(Boolean AvailableStock) {
        this.AvailableStock = AvailableStock;
    }
    
   
}

