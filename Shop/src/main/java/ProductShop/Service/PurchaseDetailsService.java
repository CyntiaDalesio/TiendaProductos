package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Entity.PurchaseDetails;
import ProductShop.Entity.Usuario;
import ProductShop.Enums.PaymentMethod;
import ProductShop.Repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ProductShop.Repository.PurchaseDetailsRepository;
import ProductShop.Repository.PurchaseRepository;
import ProductShop.Repository.UserRepository;
import ProductShop.errores.ErrorServicio;
import java.util.Date;
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
    @Autowired
    private ProductService productService;
    
    @Transactional
    public void createDetailsPurchase(String idProduct, String idUser, Integer quantity, String payMethod) throws ErrorServicio {
        
        validateNull(idProduct, idUser, payMethod, quantity);
        PurchaseDetails purchaseDetails = new PurchaseDetails();
        Purchase purchase = new Purchase();
        
        List<Integer> listCodPurchase = purchaseRepository.findOrderByCodPurchase();
        if (listCodPurchase.isEmpty()) {
            purchase.setPurchaseCode(1);
        } else {
            for (int i = 0; i < listCodPurchase.size(); i++) {
                purchase.setPurchaseCode(listCodPurchase.get(0) + 1);
            }
        }
        
        
        purchaseDetails.setProduct(validateProduct(idProduct));
        purchase.setUsuario(validateUser(idUser));
        if (validateStock(quantity, purchaseDetails.getProduct().getStock())) {
            purchaseDetails.setCantity(quantity);
        } else {
            throw new ErrorServicio("No hay stock disponible");
        }
        purchaseDetails.setPriceUnit(purchaseDetails.getProduct().getPrice());
        purchaseDetails.setSubtotal(calculateSubtotal(purchaseDetails.getPriceUnit(), quantity));
        purchase.setPaymentMethod(PaymentMethod.valueOf(payMethod));
        System.out.println("Pasando PM");
        purchase.setDate(new Date());
        purchase.setTotal(purchaseDetails.getSubtotal());
        System.out.println("Pasando Set sub total");
        purchase.setPurchaseDetail(purchaseDetails);
        
        purchaseDetailsRepository.save(purchaseDetails);
        purchaseRepository.save(purchase);
    }
    
    @Transactional
    public PurchaseDetails modifyDetail(String idDetail, String idProduct, String idUser, Integer cantity, String payMethod) throws ErrorServicio {
        
        validateNullDetail(idDetail);
        
        Optional<PurchaseDetails> optionalDetail = purchaseDetailsRepository.findById(idDetail);
        if (optionalDetail.isPresent()) {
            PurchaseDetails purchaseDetails = optionalDetail.get();
            purchaseDetails.setProduct(validateProduct(idProduct));
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
    
    @Transactional
    public void decreaseStock(String idProduct, Integer cantity) {
        Optional<Product> productOptional = productRepository.findById(idProduct);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setStock(product.getStock() - cantity);
            if ((product.getStock()) == 0) {
                product.setAvailableStock(Boolean.FALSE);
            }
            productRepository.save(product);
        } else {
            throw new Error("El producto no existe");
        }
        
    }
    
    public Product showStock(String idPurchaseDetails) {
        Product product = new Product();
        try {
            Optional<PurchaseDetails> optionalDetail = purchaseDetailsRepository.findById(idPurchaseDetails);
            if (optionalDetail.isPresent()) {
                PurchaseDetails purchaseDetails = optionalDetail.get();
                Optional<Product> productOptional = productRepository.findById(purchaseDetails.getProduct().getIdProduct());
                if (productOptional.isPresent()) {
                    product = productOptional.get();
                    return product;
                }
            }
        } catch (Exception e) {
            return product;
        }
        return product;
        
    }
    
}
