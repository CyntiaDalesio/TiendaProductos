package ProductShop.Entity;

import ProductShop.Enums.Role;
import java.util.Date;
import java.util.List;
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
public class Usuario {

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
    @OneToMany(mappedBy="user")
    private List<Contact> contacts;
    @OneToMany(mappedBy="usuario")
    private List<Purchase> purchase;

    public List<Contact>getContacts() {
        return contacts;
    }

    public void setContacts(List<Contact> contact) {
        this.contacts = contact;
    }

    public List<Purchase> getPurchase() {
        return purchase;
    }

    public void setPurchase(List<Purchase> purchase) {
        this.purchase = purchase;
    }


    public Usuario() {
    }

    public Usuario(String username,String password){

        this.username = username;
        this.password = password;

    }


    public Usuario(String idUser, String username, String password, String email, String dni, Role rol, Date startDate) {
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
