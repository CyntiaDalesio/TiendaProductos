package ProductShop.Controller;

import ProductShop.Entity.Product;
import ProductShop.Service.ProductService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

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

}
