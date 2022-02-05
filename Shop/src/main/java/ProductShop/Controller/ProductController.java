package ProductShop.Controller;

import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Service.ProductService;
import ProductShop.errores.ErrorServicio;
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
    private ProductService productservice;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("/modifyproduct/{idProduct}")
    public String Index(@PathVariable String idProduct, ModelMap model) throws ErrorServicio {

        Product product = productservice.findProductById(idProduct);

        model.put("product", product);

        return "modify";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
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
   

}

