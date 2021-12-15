package ProductShop.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProductShop.Repository.PurchaseDetailsRepository;

@Service
public class PurchaseDetailsService {
    
    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;
    
        
    
}
