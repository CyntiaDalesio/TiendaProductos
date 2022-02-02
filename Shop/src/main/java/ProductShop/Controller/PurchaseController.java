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
            System.out.println(idProduct + " Metodo Get controlador");
        } catch (Exception e) {
            System.out.println("Adentro del catch");
            throw new ErrorServicio("Producto no econtrado");
        }
        System.out.println("luego del catch");
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



   // @PostMapping("/purchase/finished/{idProduct}")
   // public String purchaseFinish(@RequestParam String idProduct, @RequestParam Integer cantity, @RequestParam String paymentMethod) throws ErrorServicio {



    @PostMapping("/purchase/finished")
    public String purchaseFinished(@RequestParam String idProduct, @RequestParam Integer cantity, @RequestParam String paymentMethod) throws ErrorServicio {

        try {
            System.out.println(idProduct + " Metodo POST en el controlador antes de crear la compra");

            Usuario user = userService.obtenerUsuarioSesion();

            System.out.println("Payment Method= "+paymentMethod);  
            System.out.println("Cantidad= "+cantity);  
            System.out.println("Cantidad= "+cantity);  

            System.out.println("Payment Method= " + paymentMethod);
            System.out.println("Cantidad= " + cantity);
            System.out.println("Cantidad= " + cantity);

            purchaseDetService.createDetailsPurchase(idProduct, user.getIdUser(), cantity, paymentMethod);
            purchaseDetService.decreaseStock(idProduct, cantity);
            System.out.println(idProduct + " Metodo POST en el controlador luego de crear la compra");
//            model.put("exito", "Compra realizada con éxito");
        } catch (Exception e) {
            e.printStackTrace();
            throw new ErrorServicio("Error de Sistema");
        }
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
    
    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")    
    @GetMapping("/purchase/sales")
    public String showAllSales(ModelMap model) {
        Usuario user = userService.obtenerUsuarioSesion();
        List<Purchase> sales = purchaseService.showPurchaseByCodPurchase();
        model.put("ventas", sales);

        return "sales.html";
    }
}
