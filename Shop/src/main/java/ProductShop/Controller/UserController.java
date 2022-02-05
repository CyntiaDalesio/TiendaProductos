package ProductShop.Controller;

import ProductShop.Entity.Usuario;
import ProductShop.Service.UserService;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users")
    public String index(ModelMap model) {

        List<Usuario> users = userService.ListUsers();
        model.put("usuarios", users);

        return "users.html";
    }

    @GetMapping("/register")
    public String newUser() {
        return "register.html";
    }

    @GetMapping("/contact")
    public String newContact(ModelMap model) {

        return "contact.html";
    }

    @PostMapping("/contact/")
    public String createContact(@RequestParam String name, @RequestParam String message) {
        try {

            Usuario user = userService.obtenerUsuarioSesion();
            userService.createContact(user, name, message);
        } catch (Error ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/";
    }

    @PostMapping("/register")
    public String create(@RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String dni) {
        try {

            userService.save(username, password, password2, email, dni);
        } catch (Error ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/login";
    }

    @GetMapping("/editUser")
    public String edit(ModelMap model) throws Error {
        Usuario user = userService.obtenerUsuarioSesion();

        model.put("user", user);

        return "editUser.html";
    }

    @PostMapping("/editUser")
    public String update(@RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String dni) throws Error {

              Usuario user = userService.obtenerUsuarioSesion();

        userService.changeUser(user.getIdUser(), username, dni, email, password, password2);
        return "redirect:/";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users/editRole/{id}")
    public String editRole(@PathVariable String id, ModelMap model) throws Error {

        userService.changeRolUser(id);

        return "redirect:/users";
    }

 @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users/deleteUser/{id}")
    public String deleteRole(@PathVariable String id, ModelMap model) throws Error {

        userService.deleteUser(id);

        return "redirect:/users";
    }





    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("clientes/updateRole/{id}")
    public String updateRole(@PathVariable String id, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String dni) throws Error {

        userService.changeUser(id, username, dni, email, password, password2);
        return "redirect:/users";
    }

}
