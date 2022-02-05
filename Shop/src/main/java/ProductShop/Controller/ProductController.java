package ProductShop.Controller;

import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Repository.ProductRepository;
import ProductShop.Service.ProductService;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller

public class ProductController {
@Autowired
private ProductRepository productRepository;

    public ProductController() {
    }
    @Autowired
    private ProductService productservice;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("/modifyproduct/{idProduct}")
    public String Index(@PathVariable String idProduct, ModelMap model) {

        Product product = productservice.findProductById(idProduct);

        model.put("product", product);

        return "modify";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("addproduct")
    public String addProduct() {
        return "addProduct.html";

    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping("/addproduct")
    public String SaveProduct(MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, String category, Integer Stock, Photo photo) throws ErrorServicio {
        productservice.CreateProduct(archivo, CodeProduct, Name, Price, TradeMark, category, Stock);
        //MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @PostMapping("/modifyproduct/{idProduct}")
    public String ModifyProduct(MultipartFile archivo, @PathVariable String idProduct, String Name, Double Price, String TradeMark, String category, Integer Stock, Photo photo, Integer codeProduct) throws ErrorServicio {
        productservice.ModifyProduct(archivo, idProduct, Name, Price, TradeMark, category, Stock, codeProduct);
        return "redirect:/";
    }
//
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/delete/{idProduct}")
    public String DeleteProduct(@PathVariable String idProduct) {
     
       Product product = productservice.findProductById(idProduct);
  
        productservice.DeleteProduct(product);
        return "redirect:/";
    }
  @GetMapping("/searchbaja/addproduct")
     public String SearchBaja2(ModelMap model, Boolean availableStock) {
        List<Product> products = productservice.listarProductBaja(availableStock);
        model.put("products", products);
        return "redirect:/addproduct";
            
}
    @GetMapping("/searchcode/addproduct")
    public String SearchCode2(ModelMap model, Integer CodeProduct) {
        List<Product> products = productservice.searchbycode(CodeProduct);
        model.put("products", products);
        return "redirect:/addproduct";
    }
    @GetMapping("/searchname/addproduct")
    public String SearchName2(ModelMap model, String Name) {
        List<Product> products = productservice.searchbyname(Name);
        model.put("products", products);
        return "redirect:/addproduct";
    }
}
