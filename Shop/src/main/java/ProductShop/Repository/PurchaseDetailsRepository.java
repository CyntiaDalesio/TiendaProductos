package ProductShop.Repository;

import ProductShop.Entity.PurchaseDetails;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PurchaseDetailsRepository extends JpaRepository<PurchaseDetails, String>{
    

    
    
    
}
