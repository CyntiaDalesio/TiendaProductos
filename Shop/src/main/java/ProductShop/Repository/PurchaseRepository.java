package ProductShop.Repository;

import ProductShop.Entity.Purchase;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,String> {
    
    @Query("Select p from Purchase p WHERE p.usuario.id = :idUser")
    public List<Purchase> findByIdUser(@Param("idUser") String idUser);

}
