/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductShop.Service;

import ProductShop.Entity.Photo;
import ProductShop.Repository.PhotoRepository;

import egg.prueba.demo.errores.ErrorServicio;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo save(MultipartFile archivo) throws ErrorServicio {

        if (archivo != null) {
            try {
                Photo photo = new Photo();
                photo.setMime(archivo.getContentType());
                photo.setName(archivo.getName());
                photo.setContent(archivo.getBytes());

                return photoRepository.save(photo);

            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }

    public Photo updatePhoto(String idPhoto, MultipartFile archivo) throws ErrorServicio {

        if (archivo != null) {
            try {
                Photo photo = new Photo();

                if (idPhoto != null) {
                    Optional<Photo> rta = photoRepository.findById(idPhoto);
                    if (rta.isPresent()) {  
                        photo = rta.get();
                    }
                }
                photo.setMime(archivo.getContentType());
                photo.setName(archivo.getName());
                photo.setContent(archivo.getBytes());
                return photoRepository.save(photo);
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        return null;

    }
}
