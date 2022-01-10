package ProductShop.Service;

import ProductShop.Entity.Photo;
import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import ProductShop.Repository.ProductRepository;
import java.util.Scanner;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    Scanner sc = new Scanner(System.in);
    
    @Autowired
    private ProductRepository productrepository;
    

    @Transactional
    public Product CreateProduct(Integer CodeProduct, String Name, Double Price, String TradeMark, String category, Integer Stock, Photo photo){
        Product product = new Product(CodeProduct,Name,Price,TradeMark,Category.valueOf(category),Stock,photo);
        
        if (Stock > 0){
        product.setAvailableStock(true);}
        else {
           product.setAvailableStock(false);
        }
        return productrepository.save(product);
        
    }
    @Transactional
    public Product ModifyProduct(String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, Category category, Integer Stock, Photo photo){
      Product product = (Product) productrepository.findByidProduct(idProduct);
       product.setCodeProduct(CodeProduct);
       product.setName(Name);
       product.setPrice(Price);
       product.setStock(Stock);
       product.setTradeMark(TradeMark);
       product.setCategory(category);
       product.setPhoto(photo);
      
       if (Stock > 0){
        product.setAvailableStock(true);}
        else {
           product.setAvailableStock(false);
        }
        return productrepository.save(product);
        
    }
    @Transactional
    public void DeleteProduct(String idProduct){
     String respuesta= null;
     if (respuesta.equals("no")){
         System.out.println("Ingrese el id del producto");
         idProduct = sc.next();
     }
     
        do{
        Product product = (Product) productrepository.findByidProduct(idProduct);
     product.toString();
        System.out.println("Desea eliminar el producto(si/no)");
       respuesta = sc.next();
        if (respuesta.equalsIgnoreCase("si")){
        productrepository.deleteById(idProduct);
      
    }else{
            System.out.println("Ingrese el id, del producto que desea eliminar");
            String id = sc.next();
            productrepository.delete(product);
        }
        }while(respuesta.equalsIgnoreCase("no")); }
        }