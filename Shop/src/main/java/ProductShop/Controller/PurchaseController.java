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
import org.springframework.security.access.prepost.PreAuthorize;
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

    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/purchase/{idProduct}")
    public String details(ModelMap model, @PathVariable String idProduct) throws ErrorServicio {
        try {
            Product product = productService.findProductById(idProduct);
            model.put("producto", product);            
        } catch (Exception e) {           
            throw new ErrorServicio("Producto no econtrado");
        }        
        return "purchaseProduct.html";
    }

//    @PostMapping("/purchase")
//    public String createDetail(ModelMap model, @RequestParam String idProduct, @RequestParam String paymentMethod, @RequestParam Integer cantity) throws ErrorServicio {
//        try {
//            Usuario user = userService.obtenerUsuarioSesion();
//            purchaseDetService.createDetailsPurchase(idProduct, user.getIdUser(), cantity, paymentMethod);
//        } catch (ErrorServicio e) {
//            throw new ErrorServicio("El detalle no fue creado");
//        }
//        return "purchaseProduct.html";
//    }
//    @GetMapping("/detailpurchase")
//    public String showDetail(ModelMap model) {
//        List<Purchase> purchase = purchaseService.showPurchase();
//        model.put("purchase", purchase);
//        return "purchaseProduct.html";
//    }
    @PostMapping("/purchase/finished")
    public String purchaseFinished(@RequestParam String idProduct, @RequestParam Integer cantity, @RequestParam String paymentMethod) throws ErrorServicio, InterruptedException {
        try {
            Usuario user = userService.obtenerUsuarioSesion();            
            purchaseDetService.createDetailsPurchase(idProduct, user.getIdUser(), cantity, paymentMethod);
            purchaseDetService.decreaseStock(idProduct, cantity);
            //model.put("exito", "Compra realizada con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorServicio("Error de Sistema");
        }

Thread.sleep(1000);
        return "redirect:/";
    }

    @GetMapping("purchase/canceled")
    public String purchaseCanceled(ModelMap model) {
//        model.put("cancel", "La Compra ha sido cancelada");
        return "index.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_USER')")
    @GetMapping("/purchase/myShopping")
    public String showPurchase(ModelMap model) {
        Usuario user = userService.obtenerUsuarioSesion();
        List<Purchase> shopping = purchaseService.showPurchaseByIdUser(user.getIdUser());
        model.put("compras", shopping);

        return "myShopping.html";
    }
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_SELLER')")
    @GetMapping("/purchase/sales")
    public String showAllSales(ModelMap model) {
        Usuario user = userService.obtenerUsuarioSesion();
        List<Purchase> sales = purchaseService.showPurchaseByCodPurchase();
        model.put("ventas", sales);

        return "sales.html";
    }
    
    @PostMapping("/searchclient/{cliente}")
    public String searchClient(ModelMap model, String cliente) {
        List<Purchase> sales = purchaseService.showPurchaseByCliente(cliente);
        model.put("ventas", sales);
        return "sales.html";
    }
    
    @PostMapping("/searcharticulo/{articulo}")
    public String searchArticulo(ModelMap model, String articulo) {
        List<Purchase> sales = purchaseService.showPurchaseByArticulo(articulo);
        model.put("ventas", sales);
        return "sales.html";
    }
    
}
