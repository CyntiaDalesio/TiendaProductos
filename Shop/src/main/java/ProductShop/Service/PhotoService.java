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

    @Transactional
    public Photo save(MultipartFile archivo) throws ErrorServicio {

        if (archivo != null) {
            try {
                Photo photo = new Photo();
                photo.setMime(archivo.getContentType());
                photo.setName(archivo.getName());
                photo.setContent(archivo.getBytes());

                return photoRepository.save(photo);

            } catch (IOException e) {
                System.out.println(e.getMessage());

                return null;
            }
        } else {
            return null;
        }
    }
    
       @Transactional
    public Photo findById(String idProduct) throws ErrorServicio {
        System.out.println(idProduct + "aca esta el error");

        Optional<Photo> wer = photoRepository.findById(idProduct);

        if (wer.isPresent() && wer != null) {
            Photo photo = wer.get();
            return photo;
        } else {
            throw new ErrorServicio("no se encontro ningun producto");
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
                return null;
            }
        }
        return null;

    }
}
