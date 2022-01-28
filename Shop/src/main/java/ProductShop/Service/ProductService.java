package ProductShop.Service;

import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Repository.ProductRepository;
import ProductShop.errores.ErrorServicio;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productrepository;
    @Autowired
    private PhotoService photoService;

    @Transactional
    public Product CreateProduct(MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, String category, Integer Stock) throws ErrorServicio {

        Product product = new Product();
        product.setCodeProduct(CodeProduct);
        if (CodeProduct == null) {
            product.setCodeProduct(CodeProduct);
        }
        product.setName(Name);
        product.setStock(Stock);
        product.setPrice(Price);
        product.setTradeMark(TradeMark);
        product.setCategory(Category.valueOf(category));
        Photo photo = photoService.save(archivo);
        product.setPhoto(photo);
        if (Stock > 0) {
            product.setAvailableStock(true);
        } else {
            product.setAvailableStock(false);
        }
        return productrepository.save(product);

    }

    @Transactional

    public void ModifyProduct(MultipartFile archivo, String idProduct, String Name, Double Price, String TradeMark, String category, Integer Stock, Integer CodeProduct) throws ErrorServicio {

        Optional<Product> answer = productrepository.findById(idProduct);
        if (answer.isPresent()) {
            Product product = answer.get();

            product.setCodeProduct(CodeProduct);

            product.setName(Name);
            product.setPrice(Price);
            product.setStock(Stock);

            if (product.getStock()>0) {
                product.setAvailableStock(Boolean.TRUE);
            }
            product.setTradeMark(TradeMark);
            product.setCategory(Category.valueOf(category));

            String idPhoto = null;
            
            if (archivo != null) {
                if (product.getPhoto() != null) {
                    idPhoto = product.getPhoto().getId();

                    Photo photo = photoService.updatePhoto(idPhoto, archivo);
                    product.setPhoto(photo);
                   
                    product.getPhoto();
                } else {
                    Photo photo = photoService.save(archivo);
                    product.setPhoto(photo);

                }
            }
        

        if (Stock > 0) {
            product.setAvailableStock(true);
        } else {
            product.setAvailableStock(false);
        }
        productrepository.save(product);
    }
}


    public List<Product> listarProduct() {

        return productrepository.findByAvailableStockTrue();
    }

   public List<Product> listarProductAll() {

        return productrepository.findAll();
    }



    public Product findProductById(String idProduct) {

        Optional<Product> answer = productrepository.findById(idProduct);
        if (answer.isPresent()) {
            Product product = answer.get();
            return product;
        } else {
            return null;
        }
    }
    public List<Product> searchbycat(Category category){
        
        return productrepository.findByCategory(category);
    }
//    public List<Product> searchbyname(String name){
//        
//        return productrepository.findByName(name);
//    }
}