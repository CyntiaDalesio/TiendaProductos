package ProductShop.Entity;

import java.io.Serializable;
import java.util.Locale.Category;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
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
    @Enumerated(EnumType.STRING)
    private Category category;
    
    private Integer Stock;
    private Boolean AvailableStock;
    @OneToOne
    private Photo photo;
    
//    @OneToOne
//    private PurchaseDetails detail;
    
    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" + "idProduct=" + getIdProduct() + ", CodeProduct=" + getCodeProduct() + ", Name=" + getName() + ", Price=" + getPrice() + ", TradeMark=" + getTradeMark() +  ", Stock=" + getStock() + ", AvailableStock=" + getAvailableStock() + '}';
    }

    public Product(Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock, Photo photo) {
        this.CodeProduct = CodeProduct;
        this.Name = Name;
        this.Price = Price;
        this.TradeMark = TradeMark;
        this.category = category;
        this.Stock = Stock;
        this.photo = photo;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
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

    public Photo getPhoto() {
        return photo;
    }

    public void setPhoto(Photo photo) {
        this.photo = photo;
    }
//
//    public PurchaseDetails getDetail() {
//        return detail;
//    }
//
//    public void setDetail(PurchaseDetails detail) {
//        this.detail = detail;
//    }
//    
   
}