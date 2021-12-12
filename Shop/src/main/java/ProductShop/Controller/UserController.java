package ProductShop.Controller;

import ProductShop.Entity.User;
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

        List<User> users = userService.ListUsers();
        model.put("users", users);

        return "users/index";
    }

    @GetMapping("/users/new")
    public String newUser() {
        return "user/new.html";
    }
    
     @GetMapping("/users/contact")
    public String newContact() {
        return "user/newContact.html";
    }
    
    
     @PostMapping("/users/contact{id}")
    public String createContact(@PathVariable String id, @RequestParam String name, @RequestParam String email) {
        try {
            userService.createContact(id, name, email);
        } catch (Error ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/users";
    }
    
    
    
    

    @PostMapping("/users")
    public String create(@RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String dni) {
        try {
            userService.save(username, password, password2, email, dni);
        } catch (Error ex) {
            Logger.getLogger(UserController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "redirect:/users";
    }

    @GetMapping("/users/edit/{id}")
    public String edit(@PathVariable String id, ModelMap model) throws Error {

        User user = userService.searchUserId(id);

        model.put("user", user);

        return "users/edit.html";
    }

    @PostMapping("clientes/update/{id}")
    public String update(@PathVariable String id, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String dni) throws Error {

        userService.changeUser(id, username, dni, email, password, password2);
        return "redirect:/users";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @GetMapping("/users/editRole/{id}")
    public String editRole(@PathVariable String id, ModelMap model) throws Error {

        User user = userService.searchUserId(id);

        model.put("user", user);

        return "users/edit.html";
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN')")
    @PostMapping("clientes/updateRole/{id}")
    public String updateRole(@PathVariable String id, @RequestParam String username, @RequestParam String password, @RequestParam String password2, @RequestParam String email, @RequestParam String dni) throws Error {

        userService.changeUser(id, username, dni, email, password, password2);
        return "redirect:/users";
    }

}
