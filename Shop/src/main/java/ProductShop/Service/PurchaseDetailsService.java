package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Entity.PurchaseDetails;
import ProductShop.Entity.Usuario;
import ProductShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProductShop.Repository.PurchaseDetailsRepository;
import ProductShop.Repository.PurchaseRepository;
import ProductShop.Repository.UserRepository;
import ProductShop.errores.ErrorServicio;
import java.util.Optional;
import javax.transaction.Transactional;

@Service
public class PurchaseDetailsService {

    @Autowired
    private PurchaseDetailsRepository purchaseDetailsRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private PurchaseRepository purchaseRepository;

    @Transactional
    public PurchaseDetails createDetailsPurchase(String idProduct, String idPurchase, Integer cantity, String payMethod) throws ErrorServicio {

        validateNull(idProduct, idPurchase, payMethod, cantity);

        PurchaseDetails purchaseDetails = new PurchaseDetails();

        purchaseDetails.setProduct(validateProduct(idProduct));
        purchaseDetails.setPurchase(validatePurchase(idPurchase));
        purchaseDetails.setCantity(cantity);
        purchaseDetails.setPriceUnit(purchaseDetails.getProduct().getPrice());
        purchaseDetails.setSubtotal(calculateSubtotal(purchaseDetails.getPriceUnit(), purchaseDetails.getCantity()));

        return purchaseDetailsRepository.save(purchaseDetails);
    }
    
    @Transactional
    public PurchaseDetails modifyDetail(String idDetail, String idProduct, String idPurchase, Integer cantity, String payMethod) throws ErrorServicio {
        
        validateNullDetail(idDetail);       

        Optional<PurchaseDetails> optionalDetail = purchaseDetailsRepository.findById(idDetail);
        if (optionalDetail.isPresent()) {
            PurchaseDetails purchaseDetails = optionalDetail.get();            
            purchaseDetails.setProduct(validateProduct(idProduct));
            purchaseDetails.setPurchase(validatePurchase(idPurchase));
            purchaseDetails.setCantity(cantity);
            purchaseDetails.setPriceUnit(purchaseDetails.getProduct().getPrice());
            purchaseDetails.setSubtotal(calculateSubtotal(purchaseDetails.getPriceUnit(), purchaseDetails.getCantity()));
            return purchaseDetailsRepository.save(purchaseDetails);
        } else {
            throw new Error("El detalle no existe");
        }
    }
    
    @Transactional
    public void deleteDetail(String idDetail) throws ErrorServicio{
        
        validateNullDetail(idDetail);       

        Optional<PurchaseDetails> optionalDetail = purchaseDetailsRepository.findById(idDetail);
        if (optionalDetail.isPresent()) {
            PurchaseDetails purchaseDetails = optionalDetail.get();        
            purchaseDetailsRepository.delete(purchaseDetails);
        } else {
            throw new Error("El detalle no existe");
        }
    }

    public Double calculateSubtotal(Double priceUnit, Integer cantity) {
        Double subtotal = 0.00;
        subtotal = priceUnit * cantity;
        return subtotal;
    }

    public void validateNull(String idProduct, String idPurchase, String payMethod, Integer cantity) {
        try {
            if (idPurchase.isEmpty()) {
                throw new Exception("La Compra no puede ser null");
            }
            if (idProduct.isEmpty()) {
                throw new Exception("El Producto no puede ser null");
            }
            if (payMethod.isEmpty()) {
                throw new Exception("El Metodo de Pago no puede ser null");
            }
            if (cantity == 0) {
                throw new Exception("La Cantidad no puede ser 0");
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }
    
    public void validateNullDetail(String idDetail){
        if (idDetail.isEmpty()) {
            throw new Error("El detalle no puede ser null");
        }
    }

    public Product validateProduct(String idProduct){

        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        } else {
            throw new Error("El producto no existe");
        }
    }
    
    public Purchase validatePurchase(String idPurchase){

        Optional<Purchase> optionalPurchase = purchaseRepository.findById(idPurchase);

        if (optionalPurchase.isPresent()) {
            Purchase purchase = optionalPurchase.get();
            return purchase;
        } else {
            throw new Error("La Compra no existe");
        }
    }
    
    
    
    
}
