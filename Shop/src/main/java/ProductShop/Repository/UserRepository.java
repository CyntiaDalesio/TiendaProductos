
package ProductShop.Repository;

import ProductShop.Entity.Usuario;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usuario, String>{

    
     //public List<User> findByNombre(String nombre);

    public Usuario findByUsername(String username);
    
    
}
