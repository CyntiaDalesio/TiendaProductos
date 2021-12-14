package ProductShop.Service;

import ProductShop.Entity.Product;
import ProductShop.Repository.ProductRepository;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productrepository;
    

    @Transactional
    public Product CreateProduct(String idProduct, Integer CodeProduct, String Name, Double Price, String TradeMark, String Category, Integer Stock){
        Product product = new Product(idProduct,CodeProduct,Name,Price,TradeMark,Category,Stock);
        if (Stock > 0){
        product.setAvailableStock(true);}
        else {
           product.setAvailableStock(false);
        }
        return productrepository.save(product);
        
    }
    
    
}