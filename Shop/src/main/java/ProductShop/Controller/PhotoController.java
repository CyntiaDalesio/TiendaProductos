
package ProductShop.Controller;

import ProductShop.Entity.Photo;
import ProductShop.Service.PhotoService;
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

@Controller
public class PhotoController {

    @Autowired
    private PhotoService photoService;
    
    @GetMapping("/photo/{id}")
    public ResponseEntity <byte[]> fotoAutor(@PathVariable String id){
        try{
        Photo photo = photoService.findById(id);
        //compruebo si el autor tiene imagen o no
        if (photo == null) {
                throw new ErrorServicio("El autor no tiene foto asignada");     
        }
        //si existe foto vendra directamente aca
        byte[] foto = photo.getContent();
        
        HttpHeaders headers = new HttpHeaders();
        //coloco all para probar sino la voy a cambiar a jpeg
        headers.setContentType(MediaType.IMAGE_JPEG);
        
        return new ResponseEntity<>(foto, headers, HttpStatus.OK);
        }catch (ErrorServicio ex){
            Logger.getLogger(PhotoController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
}
