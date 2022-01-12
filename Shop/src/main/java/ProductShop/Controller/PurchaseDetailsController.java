package ProductShop.Controller;

import ProductShop.Service.PurchaseDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PurchaseDetailsController {
    
    @Autowired
    private PurchaseDetailsService purchaseDetService;
    
    
    @GetMapping("/purchaseDetail")
    public String loadDetail(ModelMap model) {
        
        return "purchaseDetail.html";
    }    
    
    
}