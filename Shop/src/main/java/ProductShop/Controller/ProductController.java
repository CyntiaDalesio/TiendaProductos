package ProductShop.Controller;



import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Repository.ProductRepository;
import ProductShop.Service.ProductService;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class ProductController {
    
    @Autowired
        private ProductService productservice;
     @Autowired
    private ProductRepository productrepository;
     
    @GetMapping("/product")
    public String Index(){
        return "modify";
        
    }
    @PostMapping("/addproduct")
    public String SaveProduct(MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock) throws ErrorServicio {
        productservice.CreateProduct(archivo, CodeProduct, Name, Price, TradeMark, category, Stock);
        return "product/index";
    }

    @PostMapping("/modifyproduct")
    public String ModifyProduct(MultipartFile archivo, String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock) throws ErrorServicio {
      productrepository.findByidProduct(idProduct);
        productservice.ModifyProduct(archivo, idProduct, CodeProduct, Name, Price, TradeMark, category, Stock);
        return "product/index";
    }
}
        
//      @PostMapping("/searchbyname")
//      public String SearchByName(String Name) {
//            productservice.SearchByName(Name);
//          return "/searchproductname";
//      }
//      @PostMapping("/searchbycategory")
//      public String SearchByCategory(Category category) {
//      productservice.SearchByCategory(category);
//      return "/product/index";
//      }
      
        
//        
      

      


