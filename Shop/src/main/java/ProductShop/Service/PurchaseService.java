package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Entity.Usuario;
import ProductShop.Enums.PaymentMethod;
import ProductShop.Repository.ProductRepository;
import ProductShop.Repository.PurchaseRepository;
import ProductShop.Repository.UserRepository;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;
    
    
    public void validar (String idProduct,String idUsuario ){
        if (idProduct.isEmpty()) {
            throw new Error("no se ha encontrado el producto");
        }else if(idUsuario.isEmpty()){
            throw new Error("no se ha encontrado el usuario");

        }
    }

    @Transactional
    public Purchase createPurchase(String idProduct, Integer quantity,String paymentMehtod, String idUsuario) {
            Purchase purchase = new Purchase();
        
            validar(idProduct,idUsuario);

            purchase.setProduct(productRepository.getById(idProduct));
            
            purchase.setQuantity(quantity);
            purchase.setDate(new Date());
            purchase.setTotal(purchase.getQuantity() * purchase.getProduct().getPrice());
            purchase.setPaymentMethod(PaymentMethod.valueOf(paymentMehtod));
            
            return purchaseRepository.save(purchase);
            
    }

    public void editPurchase(String id, Integer quantity) {
        Purchase P = purchaseRepository.findById(id).get();

        P.setQuantity(quantity);
        P.setTotal(P.getQuantity() * P.getProduct().getPrice());

    }

    public void cancelPurchase(String id) {
        Purchase P = purchaseRepository.findById(id).get();

        purchaseRepository.delete(P);
    }
    
    
    

}
