package ProductShop.Entity;

import ProductShop.Enums.Role;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
    private String dni;
    @Enumerated(EnumType.STRING)
    private Role rol;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @OneToMany
    private Contact contact;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
   

    public User() {
    }

    public User(String username,String password){
   
          this.username = username;
        this.password = password;
       
    }
    
    
    public User(String idUser, String username, String password, String email, String dni, Role rol, Date startDate) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.email = email;
        this.dni = dni;
        this.rol = rol;
        this.startDate = startDate;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Role getRol() {
        return rol;
    }

    public void setRol(Role rol) {
        this.rol = rol;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    
    
    

}
