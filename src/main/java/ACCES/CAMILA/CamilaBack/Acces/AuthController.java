package ACCES.CAMILA.CamilaBack.Acces;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
// import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ACCES.CAMILA.CamilaBack.DTOS.AuthResponse;
import ACCES.CAMILA.CamilaBack.DTOS.LoginRequest;
import ACCES.CAMILA.CamilaBack.DTOS.RegisterRequest;
import ACCES.CAMILA.CamilaBack.ServicesImpl.AuthService;
 
@RestController
@RequestMapping("/camila")
@RequiredArgsConstructor
//  @CrossOrigin(origins = "http://192.168.100.2:8080")
// @CrossOrigin(origins = "http://localhost:8080")
public class AuthController {
    
    private final AuthService authService;
    
    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request)
    {
        return ResponseEntity.ok(authService.register(request));
    }
}