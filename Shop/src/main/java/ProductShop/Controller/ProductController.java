package ProductShop.Controller;



import ProductShop.Entity.Photo;
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
     
    @GetMapping("/product")
    public String Index(){
        return "product/index";
        
    }
    @PostMapping("/saveproduct")
      public String SaveProduct(String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock, Photo photo){
       productservice.CreateProduct(idProduct,CodeProduct,Name,Price,TradeMark,category,Stock, photo);
        return "product/index";}

      
}

