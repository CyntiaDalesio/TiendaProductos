package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Repository.ProductRepository;
import ProductShop.Repository.PurchaseRepository;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    public void createPurchase(String idProduct, Integer quantity) {
        Purchase P = new Purchase();
        Optional<Product> optionalP = productRepository.findById(idProduct);
        P.setProduct(optionalP.get());
        P.setQuantity(quantity);
        P.setDate(new Date());
        P.setTotal(P.getQuantity() * P.getProduct().getPrice());
        P.getPaymentMethod();

    }

    public void editPurchase(String id, Integer quantity) {
        Purchase P = purchaseRepository.findById(id).get();

        P.setQuantity(quantity);

    }

    public void cancelPurchase(String id) {
        Purchase P = purchaseRepository.findById(id).get();

        purchaseRepository.delete(P);
    }

}
