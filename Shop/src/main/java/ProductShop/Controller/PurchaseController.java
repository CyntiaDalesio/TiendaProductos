package ProductShop.Controller;

import ProductShop.Entity.Purchase;
import ProductShop.Entity.PurchaseDetails;
import ProductShop.Service.PurchaseDetailsService;
import ProductShop.Service.PurchaseService;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseController {

    @Autowired
    private PurchaseDetailsService purchaseDetService;
    @Autowired
    private PurchaseService purchaseService;

    @GetMapping("/purchase")
    public String details() {
        return "purchase.html";
    }

    @PostMapping("/detail")
    public String createDetail(ModelMap model, @RequestParam String idUser, @RequestParam String idProduct, @RequestParam String paymentMethod, @RequestParam Integer quantity) throws ErrorServicio {
        try {
            purchaseDetService.createDetailsPurchase(idProduct, idUser, quantity, paymentMethod);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("El detalle no fue creado");
        }
        return "purchase.html";
    }

    @GetMapping("/detailpurchase")
    public String showDetail(ModelMap model) {
        List<Purchase> purchase = purchaseService.showPurchase();
        model.put("purchase", purchase);
        return "purchase.html";
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
