package ProductShop.Repository;

import ProductShop.Entity.Purchase;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,String> {
    
    @Query("Select p from Purchase p WHERE p.usuario.id = :idUser ORDER BY p.purchaseCode")
    public List<Purchase> findByIdUser(@Param("idUser") String idUser);
    
    @Query("SELECT p FROM Purchase p ORDER BY p.purchaseCode")
    public List<Purchase> findAllOrderByCodPurchase();
    
    @Query("SELECT purchaseCode FROM Purchase ORDER BY purchaseCode DESC")
    public List<Integer> findOrderByCodPurchase();
    
    @Query("Select p from Purchase p WHERE p.usuario.username LIKE %:name% ORDER BY purchaseCode ASC")
    public List<Purchase> findByClient(@Param("name") String name);

    @Query("Select p from Purchase p WHERE p.purchaseDetail.product.Name LIKE %:articulo%")
    public List<Purchase> findByArticulo(@Param("articulo") String articulo);
}
