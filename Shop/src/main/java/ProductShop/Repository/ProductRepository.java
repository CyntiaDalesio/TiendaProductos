package ProductShop.Repository;

import ProductShop.Entity.Product;
import ProductShop.Enums.Category;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    @Query("Select p from Product p WHERE p.Name LIKE %:Name% AND p.availableStock = true")
    public List<Product> findByName(@Param("Name") String Name);

    public List<Product> findByCategory(Category category);

    @Query("Select p from Product p WHERE p.CodeProduct = :CodeProduct")
    public List<Product> findByCodeProduct(@Param("CodeProduct") Integer CodeProduct);

    public List<Product> findByAvailableStockTrue();

   @Query("Select p from Product p WHERE p.availableStock = false")
    public List<Product> findByAvailableStockFalse(@Param("availableStock") Boolean availableStock);

    @Query("Select p from Product p WHERE p.Price <= :Price AND p.availableStock = true ORDER BY Price ASC ")
    public List<Product> findByPrice(@Param("Price") Double Price);

}
