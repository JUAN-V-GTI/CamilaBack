package ACCES.CAMILA.CamilaBack.Controllers;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping; 
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
@CrossOrigin(origins = "http://192.168.100.2:8080")
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class HomeController {
    
    @PostMapping(value = "HOME")
    public String welcome()
    {
       org.springframework.security.core.Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
       
        String roles = authentication.getAuthorities().stream()
        
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));
        
        // Retorna el mensaje de bienvenida con el nombre y el rol del usuario
        return "Bienvenido " + username + ". Tu rol es: " + roles;
    }
}