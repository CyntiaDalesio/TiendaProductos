package ProductShop.Service;

import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Repository.ProductRepository;
import ProductShop.errores.ErrorServicio;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ProductService {
    Scanner sc = new Scanner(System.in);
    
    @Autowired
    private ProductRepository productrepository;
    @Autowired
    private PhotoService photoService;

    @Transactional
    public Product CreateProduct(MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, String category, Integer Stock) throws ErrorServicio {

        Product product = new Product();
        product.setCodeProduct(CodeProduct);
        if (CodeProduct == null){
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

    public Product ModifyProduct(MultipartFile archivo, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock) throws ErrorServicio {
            Product product = (Product) productrepository.findByCodeProduct(CodeProduct);

        product.setCodeProduct(CodeProduct);
        
        product.setName(Name);
        product.setPrice(Price);
        product.setStock(Stock);
        product.setTradeMark(TradeMark);
        product.setCategory(category);
     String idPhoto=null;
        if (product.getPhoto()!=null) {
            idPhoto=product.getPhoto().getId();
        }
        Photo photo= photoService.updatePhoto(idPhoto, archivo);
        product.setPhoto(photo);
        if (Stock > 0) {
            product.setAvailableStock(true);
        } else {
            product.setAvailableStock(false);
        }
        return productrepository.save(product);

    }
    @Transactional
    public void DeleteProduct(String idProduct){
    
     
        
       Optional<Product> answer = productrepository.findById(idProduct);
           if(answer.isPresent()){
    Product product = answer.get();
    productrepository.delete(product);
      
    }else{
               System.out.println("No se encontro el producto");  
        }}}
    
    //Revisar Metodos por fallas
    
//    public Product SearchByName(String Name){
//        Product products =  (Product) productrepository.findByName(Name);
//        return products;
//    }
    
//    public List<Product> SearchByCategory(Category category){
//       Product products =  (Product) productrepository.findByCategory(category);
//  
//        return (List<Product>) products;    }
//    
