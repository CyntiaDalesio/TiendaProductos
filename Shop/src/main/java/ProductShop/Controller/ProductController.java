package ProductShop.Controller;



import ProductShop.Entity.Product;
import ProductShop.Repository.ProductRepository;
import ProductShop.Service.ProductService;
import ProductShop.errores.ErrorServicio;
import java.util.Locale.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

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
    @PostMapping("/addproduct")
      public String SaveProduct(MultipartFile archivo,Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock) throws ErrorServicio{
       productservice.CreateProduct(archivo,CodeProduct,Name,Price,TradeMark,category,Stock);
        return "product/index";}
      
      @PostMapping("/modifyproduct")
      public String ModifyProduct( MultipartFile archivo,String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock) throws ErrorServicio{
          Product product = (Product) productrepository.findByidProduct(idProduct);
          productservice.ModifyProduct(archivo, idProduct, CodeProduct, Name, Price, TradeMark, category, Stock);
        return "product/index";
        
         
      }
@PostMapping("/deleteproduct")
public String DeleteProduct(String idProduct){
    productservice.DeleteProduct(idProduct);
return "product/index";}
}