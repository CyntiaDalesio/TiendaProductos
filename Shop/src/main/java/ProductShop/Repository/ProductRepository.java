package ProductShop.Repository;

import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository <Product, String>{

//    public ArrayList<Product> findByCategory(Category category);     
  
//    public ArrayList<Product> findByName(String name);       
// 
    public List<Product> findByidProduct(String idProduct);
    
//    public ArrayList<Product> findByPrice(Double price);
}