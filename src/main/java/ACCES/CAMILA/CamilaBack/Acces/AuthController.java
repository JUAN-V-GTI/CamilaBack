package ACCES.CAMILA.CamilaBack.Acces;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ACCES.CAMILA.CamilaBack.DTOS.AuthResponse;
import ACCES.CAMILA.CamilaBack.DTOS.LoginRequest;
import ACCES.CAMILA.CamilaBack.DTOS.RegisterRequest;
import ACCES.CAMILA.CamilaBack.Models.User;
import ACCES.CAMILA.CamilaBack.ServicesImpl.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/cam")
@RequiredArgsConstructor
 @CrossOrigin(origins = "http://192.168.100.2:8086")
//  @CrossOrigin(origins = "http://localhost:8084")
public class AuthController {

    private final AuthService authService;

    @PostMapping(value = "login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }

    @PostMapping(value = "register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PutMapping(value = "update/{id}")
    public ResponseEntity<User> update(@PathVariable Integer id, @RequestBody RegisterRequest request) {
        return ResponseEntity.ok(authService.update(id, request));
    }

    @DeleteMapping(value = "disable/{id}")
    public ResponseEntity<Void> disable(@PathVariable Integer id) {
        authService.disable(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping(value = "enable/{id}")
    public ResponseEntity<Void> enable(@PathVariable Integer id) {
        authService.enable(id);
        return ResponseEntity.ok().build();
    }
}