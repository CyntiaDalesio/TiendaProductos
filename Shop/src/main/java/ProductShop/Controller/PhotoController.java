package ProductShop.Controller;

import ProductShop.Entity.Product;
import ProductShop.Service.ProductService;
import ProductShop.errores.ErrorServicio;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

@RequestMapping("/photo")

public class PhotoController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{id}")
    public ResponseEntity<byte[]> photo(@PathVariable String id) {
        try {
            Product product = productService.findProductById(id);
           
            if (product.getPhoto() == null) {
             
                throw new ErrorServicio("El producto no tiene foto asignada");
            }
            byte[] photo = product.getPhoto().getContent();
            product.getPhoto().getContent();
            HttpHeaders headers = new HttpHeaders();

            headers.setContentType(MediaType.IMAGE_JPEG);

            return new ResponseEntity<>(photo, headers, HttpStatus.OK);

        } catch (ErrorServicio ex) {
            Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
