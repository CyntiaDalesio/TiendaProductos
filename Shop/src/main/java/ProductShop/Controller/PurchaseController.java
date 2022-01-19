
package ProductShop.Controller;

import ProductShop.Repository.PurchaseRepository;
import ProductShop.Service.PurchaseService;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class PurchaseController {
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @Autowired
    private PurchaseService purchaseService;
    
    
    
    
    
}
