package ProductShop.Controller;

import ProductShop.Entity.Product;
import ProductShop.Entity.Usuario;
import ProductShop.Enums.Category;
import ProductShop.Enums.Role;
import ProductShop.Service.ProductService;
import ProductShop.Service.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/")
    public String principal(ModelMap model) {

        List<Product> products;
        Usuario user = userService.obtenerUsuarioSesion();
        if (user != null && user.getRol() == Role.ADMIN) {

            products = productservice.listarProduct();

        } else {

            products = productservice.listarProduct();
        }
        model.put("usuario", user);
        model.put("products", products);
        return "index.html";
    }

    @PostMapping("/searchcat")
    public String SearchCat(ModelMap model,Category category) {
       
        List<Product> productos =  productservice.searchbycat(category);
       
        model.put("products", productos);
        return "index.html";
    }

    @PostMapping("/searchname/{Name}")
    public String SearchName(ModelMap model, String Name) {
        List<Product> products = productservice.searchbyname(Name);
        model.put("products", products);
        return "index.html";
    }

    @PostMapping("/searchprice/{Price}")
    public String SearchPrice(ModelMap model, Double Price) {
        List<Product> products = productservice.searchbyprice(Price);
        model.put("products", products);
        return "index.html";
    }

    @PostMapping("/searchcode/{CodeProduct}")
    public String SearchCode(ModelMap model, Integer CodeProduct) {
        List<Product> products = productservice.searchbycode(CodeProduct);
        model.put("products", products);
        return "index.html";
    }
    @PostMapping("/searchbaja/{availableStock}")
     public String SearchBaja(ModelMap model, Boolean availableStock) {
        List<Product> products = productservice.listarProductBaja(availableStock);
        model.put("products", products);
        return "index.html";
            
}
   }
