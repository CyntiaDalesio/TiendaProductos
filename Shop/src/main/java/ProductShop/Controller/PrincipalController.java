package ProductShop.Controller;

import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PrincipalController {

    @Autowired
    private ProductService productservice;

    @GetMapping("/")
    public String principal(ModelMap model) {
        List<Product> products = productservice.listarProduct();
        model.put("products", products);
        return "index.html";
    }
    
    @PostMapping("/searchcat")
    public String SearchCat(ModelMap model, Category category){
         List<Product> products = productservice.searchbycat(category);
        model.put("products", products);
        return "index.html";
    }
    
//@PostMapping("/searchname")
//    public String SearchName(ModelMap model, String name){
//         List<Product> products = productservice.searchbyname(name);
//        model.put("products", products);
//        return "index.html";
//    }
}


