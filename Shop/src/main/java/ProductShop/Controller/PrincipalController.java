
package ProductShop.Controller;

import org.springframework.web.bind.annotation.GetMapping;


public class PrincipalController {
   @GetMapping("/")
    public String principal(){
  
    return "index.html";
    }
    
}
