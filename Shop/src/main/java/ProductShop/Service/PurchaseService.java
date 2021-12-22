package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Entity.Usuario;
import ProductShop.Repository.ProductRepository;
import ProductShop.Repository.PurchaseRepository;
import ProductShop.Repository.UserRepository;
import java.util.Date;
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
    
    @Transactional
    public Purchase createPurchase(String idProduct, Integer quantity,String idUsuario) {
        Purchase purchase = new Purchase();
        
        Optional<Usuario> optionalU = userRepository.findById(idUsuario);
        try{
        if(optionalU.isPresent()){
            purchase.setUsuario(optionalU.get());
        }
        }catch(Exception e){
            throw new Error("no se ha encontrado el usuario");
        }
        
        Optional<Product> optionalP = productRepository.findById(idProduct);
        try{
            if(optionalP.isPresent()){
            purchase.setProduct(optionalP.get());
        }
        }
        catch(Exception e){
            throw new Error("no se ha encontrado el producto");
        }
        
        
        purchase.setQuantity(quantity);
        purchase.setDate(new Date());
        purchase.setTotal(purchase.getQuantity() * purchase.getProduct().getPrice());
        purchase.getPaymentMethod();

        return purchase;
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
