package ProductShop.Controller;

import ProductShop.Entity.Product;
import ProductShop.Entity.Purchase;
import ProductShop.Entity.PurchaseDetails;
import ProductShop.Entity.Usuario;
import ProductShop.Service.ProductService;
import ProductShop.Service.PurchaseDetailsService;
import ProductShop.Service.PurchaseService;
import ProductShop.Service.UserService;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseController {
    @Autowired
    private UserService userService;
    @Autowired
    private PurchaseDetailsService purchaseDetService;
    @Autowired
    private PurchaseService purchaseService;
    @Autowired
    private ProductService productService;

    @GetMapping("/purchase/{idProduct}")
    public String details(ModelMap model,@PathVariable String idProduct) throws ErrorServicio {
        try{
        Product product = productService.findProductById(idProduct);
        model.addAttribute("producto", product);
        }catch(Exception e){
            throw new ErrorServicio("producto no econtrado");
        }
        return "purchaseProduct.html";
    }

    @PostMapping("/purchase")
    public String createDetail(ModelMap model, @RequestParam String idProduct, @RequestParam String paymentMethod, @RequestParam Integer cantity) throws ErrorServicio {
        try {
            Usuario user = userService.obtenerUsuarioSesion();
            purchaseDetService.createDetailsPurchase(idProduct, user.getIdUser(), cantity, paymentMethod);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("El detalle no fue creado");
        }
        return "purchaseProduct.html";
    }

    @GetMapping("/detailpurchase")
    public String showDetail(ModelMap model) {
        List<Purchase> purchase = purchaseService.showPurchase();
        model.put("purchase", purchase);
        return "purchaseProduct.html";
    }

    @PostMapping("detail/finished")
    public String purchaseFinish(ModelMap model, String idPurchase, String idPurchaseDetails) throws ErrorServicio {
        Optional<Purchase> PO = purchaseService.findById(idPurchase);
        if (PO.isPresent()) {
            model.put("exito", "compra realizada con exito");
            purchaseDetService.decreaseStock(idPurchaseDetails);
        } else {
            throw new ErrorServicio("La compra no fue creado");
        }

        return "index.html";
    }

    @PostMapping("detail/canceled")
    public String purchaseCanceled(ModelMap model, String idPurchase) throws ErrorServicio {
        Optional<Purchase> PO = purchaseService.findById(idPurchase);
        if (PO.isPresent()) {
            model.put("cancel", "la compra ha sido cancelada");
            Purchase purchase = PO.get();
            purchaseDetService.deleteDetail(PO.get().getPurchaseDetail().getIdDetails());
            purchaseService.deletePurchase(purchase.getId());
        }
        return "index.html";
    }

}
