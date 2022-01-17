/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductShop.Service;

import ProductShop.Entity.Photo;
import ProductShop.Repository.PhotoRepository;
import ProductShop.errores.ErrorServicio;
import java.io.IOException;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PhotoService {

    @Autowired
    private PhotoRepository photoRepository;

    public Photo findById(String id) {

        if (id != null) {
            Optional<Photo> rta = photoRepository.findById(id);
            if (rta.isPresent()) {
                Photo photo = rta.get();

                return photo;
            }
        }
        return null;
    }

    @Transactional
    public Photo save(MultipartFile archivo) throws ErrorServicio {

        Photo photo = new Photo();

        try {

            photo.setMime(archivo.getContentType());
            photo.setName(archivo.getName());
            photo.setContent(archivo.getBytes());

            photoRepository.save(photo);

            return photo;
        } catch (IOException e) {
            System.out.println(e.getMessage());

            return null;
        } catch (NullPointerException ee) {

            System.out.println(ee.getMessage());

            return null;
        }

    }

    @Transactional
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
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
        return null;

    }
}
