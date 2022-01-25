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
import java.util.List;
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
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PurchaseService purchaseService;

    @Transactional
    public PurchaseDetails createDetailsPurchase(String idProduct, String idUser, Integer quantity, String payMethod) throws ErrorServicio {

        validateNull(idProduct, idUser, payMethod, quantity);

        PurchaseDetails purchaseDetails = new PurchaseDetails();

        purchaseDetails.setProduct(validateProduct(idProduct));
        purchaseDetails.setUser(validateUser(idUser));
//        purchaseDetails.setPurchase(purchaseService);
        if (validateStock(quantity, purchaseDetails.getProduct().getStock())) {
            throw new ErrorServicio("No hay stock disponible");
        } else {
            purchaseDetails.setCantity(quantity);
        }
        purchaseDetails.setPriceUnit(purchaseDetails.getProduct().getPrice());
        purchaseDetails.setSubtotal(calculateSubtotal(purchaseDetails.getPriceUnit(), purchaseDetails.getCantity()));

        return purchaseDetailsRepository.save(purchaseDetails);
    }

    @Transactional
    public PurchaseDetails modifyDetail(String idDetail, String idProduct, String idUser, Integer cantity, String payMethod) throws ErrorServicio {

        validateNullDetail(idDetail);

        Optional<PurchaseDetails> optionalDetail = purchaseDetailsRepository.findById(idDetail);
        if (optionalDetail.isPresent()) {
            PurchaseDetails purchaseDetails = optionalDetail.get();
            purchaseDetails.setProduct(validateProduct(idProduct));
            purchaseDetails.setUser(validateUser(idUser));
            purchaseDetails.setCantity(cantity);
            purchaseDetails.setPriceUnit(purchaseDetails.getProduct().getPrice());
            purchaseDetails.setSubtotal(calculateSubtotal(purchaseDetails.getPriceUnit(), purchaseDetails.getCantity()));
            return purchaseDetailsRepository.save(purchaseDetails);
        } else {
            throw new Error("El detalle no existe");
        }
    }

    @Transactional
    public void deleteDetail(String idDetail) throws ErrorServicio {

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

    public Boolean validateStock(Integer quantity, Integer availableStock) {
        Boolean stock = true;
        if (quantity > availableStock) {
            return stock = false;
        } else {
            return stock;
        }
    }

    public List<PurchaseDetails> showDetail() {
        return purchaseDetailsRepository.findAll();
    }

    public Optional<PurchaseDetails> findById(String idDetails) {
        return purchaseDetailsRepository.findById(idDetails);
    }

    public void validateNull(String idProduct, String idUser, String payMethod, Integer cantity) {
        try {
            if (idUser.isEmpty()) {
                throw new Exception("El Usuario no puede ser null");
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

    public void validateNullDetail(String idDetail) {
        if (idDetail.isEmpty()) {
            throw new Error("El detalle no puede ser null");
        }
    }

    public Product validateProduct(String idProduct) {

        Optional<Product> optionalProduct = productRepository.findById(idProduct);

        if (optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            return product;
        } else {
            throw new Error("El producto no existe");
        }
    }

    public Usuario validateUser(String idUser) {

        Optional<Usuario> optionalUser = userRepository.findById(idUser);

        if (optionalUser.isPresent()) {
            Usuario user = optionalUser.get();
            return user;
        } else {
            throw new Error("El Usuario no existe");
        }
    }

}
