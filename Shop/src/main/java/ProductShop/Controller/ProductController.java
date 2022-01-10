package ProductShop.Controller;



import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Repository.ProductRepository;
import ProductShop.Service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller

public class ProductController {
//    @Autowired
//        private Product product;
    @Autowired
        private ProductService productservice;
     @Autowired
    private ProductRepository productrepository;
     
//    @GetMapping("/product")
//    public String Index(){
//        return "product/index";
//        
//    }
      @GetMapping("addproduct")
    public String addProduct(){
        return "addProduct.html";
        
    }
    
    @PostMapping("/addproduct")
      public String SaveProduct(Integer CodeProduct, String Name, Double Price, String TradeMark, String category, Integer Stock,Photo photo){
       productservice.CreateProduct(CodeProduct,Name,Price,TradeMark,category,Stock,photo);
        return "index";}
      
      @PostMapping("/modifyproduct")
      public String ModifyProduct(String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock, Photo photo){
          Product product = (Product) productrepository.findByidProduct(idProduct);
          productservice.ModifyProduct(idProduct, CodeProduct, Name, Price, TradeMark, category, Stock, photo);
        return "product/index";
        
         
      }
@PostMapping("/deleteproduct")
public String DeleteProduct(String idProduct){
    productservice.DeleteProduct(idProduct);
return "product/index";}
}
      

      


