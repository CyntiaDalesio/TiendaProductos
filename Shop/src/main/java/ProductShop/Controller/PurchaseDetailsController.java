package ProductShop.Controller;

import ProductShop.Entity.PurchaseDetails;
import ProductShop.Service.PurchaseDetailsService;
import ProductShop.Service.PurchaseService;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseDetailsController {
    
    @Autowired
    private PurchaseDetailsService purchaseDetService;
    @Autowired
    private PurchaseService purchaseService;
    
    
    
    
}