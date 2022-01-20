package ProductShop.Controller;

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
    public String details(){
        return "purchase.html";
    }
    
    @PostMapping("/detail")
    public String createDetail(ModelMap model, @RequestParam String idUser, @RequestParam String idProduct,@RequestParam String paymentMethod, @RequestParam Integer quantity) throws ErrorServicio{
        try {
            purchaseDetService.createDetailsPurchase(idProduct, idUser, quantity, paymentMethod);
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
    
    @PostMapping("detail/finished")
    public String purchaseFinish(ModelMap model,String idPurchaseDetails){
        Optional<PurchaseDetails> PDO = purchaseDetService.findById(idPurchaseDetails);
        if (PDO.isPresent()) {
            model.put("exito", "compra realizada con exito");
        }else{
            model.put("error", "compra no realizada");
        }
        //Regresa al index porque al finalizar la compra, aparecera un pop out verde que diga "Compra realizada con exito" con un link al lado que rediriga al detalle de la compra(Muy de mas)
        return "index.html";
    }
    
}