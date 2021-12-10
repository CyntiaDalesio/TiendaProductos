
package ProductShop.Entity;

import ProductShop.Enums.Role;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.annotations.GenericGenerator;

@Entity
public class User {
  @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String idUser;
  private String username;
    private String password;
    private String email;
    @Enumerated(EnumType.STRING)
    private Role rol;
    
    
  
  
  
  
}
