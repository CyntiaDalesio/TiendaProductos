
package ProductShop.Repository;

import ProductShop.Entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface PhotoRepository extends JpaRepository <Photo, String>{
}
