package ProductShop.Repository;

import ProductShop.Entity.Product;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository <Product, String>{

//    public List<Product> findByCategory();     
//    
//    public List<Product> findByName(String name);       
//    
    public List<Product> findByidProduct(String idProduct);
}