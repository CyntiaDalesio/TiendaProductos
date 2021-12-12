
package ProductShop.Repository;

import ProductShop.Entity.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String>{

    
     public List<User> findByNombre(String nombre);

    public User findByUsername(String username);
    
    
}
