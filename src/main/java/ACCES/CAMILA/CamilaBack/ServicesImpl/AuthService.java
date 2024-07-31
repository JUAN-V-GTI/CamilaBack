package ACCES.CAMILA.CamilaBack.ServicesImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import ACCES.CAMILA.CamilaBack.DTOS.AuthResponse;
import ACCES.CAMILA.CamilaBack.DTOS.LoginRequest;
import ACCES.CAMILA.CamilaBack.DTOS.RegisterRequest;
import ACCES.CAMILA.CamilaBack.JWT.JwtService;
import ACCES.CAMILA.CamilaBack.Models.Role;
import ACCES.CAMILA.CamilaBack.Models.User;
import ACCES.CAMILA.CamilaBack.Repositories.UserRepository;
import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

     public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        UserDetails user=userRepository.findByUsername(request.getUsername()).orElseThrow();
        String token=jwtService.getToken(user);
        return AuthResponse.builder()
            .token(token)
            .build();

     }

     
    public AuthResponse register(RegisterRequest request) {
        Role role;
        if (request.getRole() != null) {
            try {
                role = Role.valueOf(request.getRole().toUpperCase());
            } catch (IllegalArgumentException e) {
                role = Role.USER; // Valor predeterminado si el rol no es válido
            }
        } else {
            role = Role.USER; // Valor predeterminado si el rol es null
        }
        User user = User.builder()
            .username(request.getUsername())
            .password(passwordEncoder.encode( request.getPassword()))
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .sex(request.getSex())
            .rfc(request.getRfc())
            .phone_Number(request.getPhone_Number())
            .role(role)
            .build();

        userRepository.save(user);

        return AuthResponse.builder()
            .token(jwtService.getToken(user))
            .build();
        
    }
  
}