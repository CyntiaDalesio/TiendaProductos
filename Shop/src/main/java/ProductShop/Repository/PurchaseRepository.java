package ProductShop.Repository;

import ProductShop.Entity.Purchase;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface PurchaseRepository extends JpaRepository<Purchase,String> {
    

}
