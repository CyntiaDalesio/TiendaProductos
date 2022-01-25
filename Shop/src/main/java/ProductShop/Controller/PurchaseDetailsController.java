package ProductShop.Controller;

import ProductShop.Entity.PurchaseDetails;
import ProductShop.Service.PurchaseDetailsService;
import ProductShop.Service.PurchaseService;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class PurchaseDetailsController {
    
    @Autowired
    private PurchaseDetailsService purchaseDetService;
    @Autowired
    private PurchaseService purchaseService;
    
    
    @GetMapping("/purchase")
    public String details(){
        return "purchase.html";
    }
    
    @PostMapping("/detail")
    public String createDetail(ModelMap model, @RequestParam String idUser, @RequestParam String idProduct, @RequestParam Integer quantity) throws ErrorServicio{
        try {
            purchaseDetService.createDetailsPurchase(idProduct, idUser, quantity, idUser);
        } catch (ErrorServicio e) {
            throw new ErrorServicio("El detalle no fue creado");
        }
        return "purchase.html";
    }  
    
    @GetMapping("/detailpurchase")
    public String showDetail(ModelMap model) {
        List <PurchaseDetails> purchaseDetails= purchaseDetService.showDetail();
        model.put("purchaseDetails", purchaseDetails);
        return "purchase.html";
    }
    
}