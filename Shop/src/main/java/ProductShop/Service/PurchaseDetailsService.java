package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.PurchaseDetails;
import ProductShop.Entity.Usuario;
import ProductShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProductShop.Repository.PurchaseDetailsRepository;
import ProductShop.Repository.UserRepository;
import java.util.Optional;

@Service
public class PurchaseDetailsService {

    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    public PurchaseDetails createDetailsPurchase(String idUser, String idProduct, Integer cantity, String payMethod) {

        PurchaseDetails purchaseDetails = new PurchaseDetails();
        
        try {
            Optional<Product> optionalProduct = productRepository.findById(idProduct);
            if (optionalProduct.isPresent()) {
                purchaseDetails.setProduct(optionalProduct.get());
            }
        } catch (Exception e) {
            throw new Error("El producto no existe");
        }
        
        purchaseDetails.setCantity(cantity);
        purchaseDetails.setPriceUnit(purchaseDetails.getProduct().getPrice());
        purchaseDetails.setSubtotal(Double.NaN);

        return purchaseDetails;
    }

    public void validate(String idUser, String idProduct) {

        

    }

}
