package ProductShop.Service;

import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Repository.ProductRepository;
import ProductShop.errores.ErrorServicio;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

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
public List<Product> listarProductBaja(Boolean availableStock) {

        return productrepository.findByAvailableStockFalse(availableStock);
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
       List<Product> productos = productrepository.findByCategory(category);
        List<Product> p = new ArrayList();
        if(productos.iterator().hasNext()){
            for (Product producto : productos) {
                producto.getAvailableStock();
                if(producto.getAvailableStock() == true){
                    p.add(producto);
                }
            }
        }
    return p;}
    
    public List<Product> searchbyname(String Name){
        System.out.println(Name);
      
      
       
        return  productrepository.findByName(Name);
    }
    
    public List<Product> searchbyprice(Double Price){
        
    return productrepository.findByPrice(Price);
    }
    public List<Product> searchbycode (Integer CodeProduct){
        
        return productrepository.findByCodeProduct(CodeProduct);
        
    }
    public void DeleteProduct (Product product){
       product.setStock(0);
       product.setAvailableStock(false);
       productrepository.save(product);
    }
}