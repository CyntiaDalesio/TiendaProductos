package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Entity.PurchaseDetails;
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
    private UserRepository userRepository;

    @Transactional
    public Purchase createPurchase(String paymentMehtod, Double priceUnit) {
        Purchase purchase = new Purchase();
        purchase.setDate(new Date());
        purchase.setPaymentMethod(PaymentMethod.valueOf(paymentMehtod));

        return purchaseRepository.save(purchase);

    }

    public void deletePurchase(String idPurchase) {
        Optional<Purchase> optionalPruchase = purchaseRepository.findById(idPurchase);
        if (optionalPruchase.isPresent()) {
            Purchase purchase = optionalPruchase.get();
            purchaseRepository.delete(purchase);
        } else {
            throw new Error("La compra no existe");
        }

    }

    public List<Purchase> showPurchase() {
        return purchaseRepository.findAll();
    }

    public Optional<Purchase> findById(String idPurchase) {
        return purchaseRepository.findById(idPurchase);
    }

    public List<Purchase> showPurchaseByIdUser(String idUsuario) {
        return purchaseRepository.findByIdUser(idUsuario);
    }

    public List<Purchase> showPurchaseByCodPurchase() {
        return purchaseRepository.findAllOrderByCodPurchase();
    }
    
    public List<Purchase> showPurchaseByCliente(String name) {
        return purchaseRepository.findByClient(name);
    }
    
    public List<Purchase> showPurchaseByArticulo(String articulo) {
        return purchaseRepository.findByArticulo(articulo);
    }
}
