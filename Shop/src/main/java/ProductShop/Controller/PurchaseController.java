
package ProductShop.Controller;

import ProductShop.Repository.PurchaseRepository;
import ProductShop.Service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PurchaseController {
    
    @Autowired
    private PurchaseRepository purchaseRepository;
    
    @Autowired
    private PurchaseService purchaseService;
    
    
    
}
