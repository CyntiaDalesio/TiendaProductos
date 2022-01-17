package ProductShop.Repository;

import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface ProductRepository extends JpaRepository <Product, String>{
    
//    @Query("Select p from Product p WHERE p.Category = '%:Category%'")
//    public List<Product> findByCategory(@Param("Category") Category category); 
    
//  @Query("Select * from Product p WHERE p.Name = '%:Name%'")
//    public List<Product> findByName(@Param("Name") String Name);       
// 
//   @Query("Select MAX(CodeProduct) from Product p WHERE p.CodeProduct)
//    public List<Product> findLastCodeProduct(@Param("CodeProduct") Integer CodeProduct);
////    public ArrayList<Product> findByPrice(Double price);
    @Query("Select p from Product p WHERE p.CodeProduct = :CodeProduct")
    public List<Product> findByCodeProduct(@Param("CodeProduct") Integer CodeProduct);
    
}