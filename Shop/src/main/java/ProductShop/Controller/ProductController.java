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
        return "modify";}

      @GetMapping("addproduct")
    public String addProduct(){
        return "addProduct.html";
        
    }
    
    @PostMapping("/addproduct")
      public String SaveProduct(MultipartFile archivo,Integer CodeProduct, String Name, Double Price, String TradeMark, String category, Integer Stock,Photo photo) throws ErrorServicio{
       productservice.CreateProduct(archivo,CodeProduct,Name,Price,TradeMark,category,Stock);
       //MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock
        return "redirect:/";}
      
      @PostMapping("/modifyproduct")
      public String ModifyProduct(MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock, Photo photo) throws ErrorServicio{
          productservice.ModifyProduct(archivo, CodeProduct, Name, Price, TradeMark, category, Stock);
        return "product/index";
    }}


   

        
//      @PostMapping("/findbyname")
//      public String SearchByName(String Name) {
//            productrepository.findByName(Name);
//          return "index";
//      }
//      @PostMapping("/findbycategory")
//      public String FindByCategory(Category category) {
//      productrepository.findByCategory(category);
//      return "index";
      
      
        
//        
      

      


