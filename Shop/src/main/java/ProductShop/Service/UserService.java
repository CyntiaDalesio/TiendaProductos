package ProductShop.Service;

import ProductShop.Entity.Contact;
import ProductShop.Entity.User;
import ProductShop.Enums.Role;
import ProductShop.Repository.UserRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Transactional
    public User save(String username, String password, String password2, String email, String dni) throws Error {
        
        User user = new User();
        
        if (email == null || email.isEmpty()) {
            throw new Error("El email no puede ser nulo");
        }
        
        if (dni == null || dni.isEmpty()) {
            throw new Error("El dni no puede ser nulo");
        }
        
        if (username == null || username.isEmpty()) {
            throw new Error("El username no puede ser nulo");
        }
        
        if (password == null || password.isEmpty() || password2 == null || password2.isEmpty()) {
            throw new Error("El password no puede ser nulo");
        }
        
        if (!password.equals(password2)) {
            throw new Error("Los password no coinciden");
            
        }
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        user.setPassword(encoder.encode(password)); // encripta la contraseña
        user.setUsername(username);
        user.setDni(dni);
        user.setEmail(email);
        
        user.setRol(Role.USER);
        
        return userRepository.save(user);
        
    }
    
    public List<User> ListUsers() {
        
        return userRepository.findAll();
    }
    
    public User searchUserId(String id) {
        Optional<User> answer = userRepository.findById(id);
        User user = answer.get();
        return user;
        
    }
    
    @Transactional
    public void createContact(String idUser, String name, String message) {
        
        if (name == null || name.isEmpty()) {
            
            throw new Error("El name  no puede ser nulo");
        }
        if (message == null || message.isEmpty()) {
            
            throw new Error("El mensaje no puede ser nulo");
        }
        
        Optional<User> answer = userRepository.findById(idUser);
        if (answer.isPresent()) {
            User user = answer.get();
            
            Contact contact = new Contact();
            
            contact.setMessage(message);
            contact.setName(name);
            
//            user.setContact(contact);
            userRepository.save(user);
            
        } else {
            
            throw new Error("No se encontró el user ingresado");
            
        }
        
    }

    //modificar desde el rol user 
    @Transactional
    public void changeUser(String id, String username, String dni, String email, String password, String password2) throws Error {
        if (username == null || username.isEmpty()) {
            
            throw new Error("El username del cliente no puede ser nulo");
        }
        if (dni == null || dni.isEmpty()) {
            
            throw new Error("El dni del cliente no puede ser nulo");
        }
        
        if (email == null || email.isEmpty()) {
            
            throw new Error("El apellido del cliente no puede ser nulo");
        }
        
        if (password == null || password.isEmpty() || password2 == null || password2.isEmpty()) {
            throw new Error("El password no puede ser nulo");
        }
        
        if (!password.equals(password2)) {
            throw new Error("Los password no coinciden");
            
        }
        
        Optional<User> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            User user = answer.get();
            
            user.setUsername(username);
            user.setPassword(password);
            user.setDni(dni);
            user.setEmail(email);
            userRepository.save(user);
            
        } else {
            
            throw new Error("No se encontró el autor ingresado");
        }
        
    }

    //modificar desde el rol admin un user 
    @Transactional
    public void changeRolUser(String id, String Rol) {
        
        Optional<User> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            User user = answer.get();
            
            user.setRol(Role.valueOf(Rol));
            userRepository.save(user);
            
        } else {
            
            throw new Error("No se encontró el autor ingresado");
        }
        
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        
        try {
            User usuario = userRepository.findByUsername(username);
            //User user;

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));
            
            return new org.springframework.security.core.userdetails.User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }
        
    }
    
}
