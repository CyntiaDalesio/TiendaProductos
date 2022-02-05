package ProductShop.Service;

import ProductShop.Entity.Contact;
import ProductShop.Entity.Usuario;
import ProductShop.Enums.Role;
import ProductShop.Repository.ContactRepository;
import ProductShop.Repository.UserRepository;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.transaction.Transactional;
import static org.apache.tomcat.jni.User.username;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository repoContact;

    @Transactional
    public Usuario save(String username, String password, String password2, String email, String dni) throws Error {

        Usuario user = new Usuario();

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

        Usuario userExist = userRepository.findByUsername(username);
        if (userExist != null) {

            throw new Error("El username ya existe");
        } else {

            user.setUsername(username);
            user.setDni(dni);
            user.setEmail(email);
            user.setStartDate(Calendar.getInstance().getTime());
            user.setRol(Role.USER);

            return userRepository.save(user);
        }

    }

    public List<Usuario> ListUsers() {

        return userRepository.findAll();
    }

    public Usuario searchUserId(String id) {
        Optional<Usuario> answer = userRepository.findById(id);
        Usuario user = answer.get();
        return user;

    }

    @Transactional
    public void createContact(Usuario user, String name, String message) {

        if (name == null || name.isEmpty()) {

            throw new Error("El name  no puede ser nulo");
        }
        if (message == null || message.isEmpty()) {

            throw new Error("El mensaje no puede ser nulo");
        }

//        Optional<Usuario> answer = userRepository.findById(idUser);
//        if (answer.isPresent()) {
//            Usuario user = answer.get();
//            
        Contact contact = new Contact();

        contact.setMessage(message);
        contact.setName(name);
        contact.setUser(user);
        // user.contacts.add(contact);
        repoContact.save(contact);
        userRepository.save(user);
//            
//       
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

        Optional<Usuario> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            Usuario user = answer.get();
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
            user.setPassword(encoder.encode(password)); // encripta la contraseña
            user.setUsername(username);
            user.setDni(dni);
            user.setEmail(email);
            userRepository.save(user);

        } else {

            throw new Error("No se encontró el usuario ingresado");
        }

    }

    //modificar desde el rol admin un user 
    @Transactional
    public void changeRolUser(String id) {

        Optional<Usuario> answer = userRepository.findById(id);
        if (answer.isPresent()) {
            Usuario user = answer.get();

            user.setRol(Role.SELLER);
            userRepository.save(user);

        } else {

            throw new Error("No se encontró el usuario ingresado");
        }

    }
// eliminar Seller

    @Transactional
    public void deleteUser(String id) {
        try {
            Optional<Usuario> answer = userRepository.findById(id);
            if (answer.isPresent()) {
                Usuario user = answer.get();

                if (user.getPurchase().isEmpty()) {
                    userRepository.delete(user);
                }

            }

        } catch (Exception e) {
            throw new Error("El usuario posee compras realizadas");

        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        try {
            Usuario usuario = userRepository.findByUsername(username);
            //User user;

            List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()));

            return new org.springframework.security.core.userdetails.User(username, usuario.getPassword(), authorities);
        } catch (Exception e) {
            throw new UsernameNotFoundException("El usuario no existe");
        }

    }

    public Usuario obtenerUsuarioSesion() {
        try {
            String username;
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (principal instanceof UserDetails) {
                username = ((UserDetails) principal).getUsername();
            } else {
                username = principal.toString();

            }
            Usuario user = userRepository.findByUsername(username);
            return user;

        } catch (Exception e) {
            System.out.println(e.getMessage());

            return null;
        }

    }
}
