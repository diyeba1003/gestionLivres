package com.edacyprogram.gestionLivre.service;

import com.edacyprogram.gestionLivre.dto.AuthResponse;
import com.edacyprogram.gestionLivre.dto.LoginRequest;
import com.edacyprogram.gestionLivre.dto.RegisterRequest;
import com.edacyprogram.gestionLivre.entity.AppUser;
import com.edacyprogram.gestionLivre.entity.Role;
import com.edacyprogram.gestionLivre.repository.UserRepository;
import com.edacyprogram.gestionLivre.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsByEmail(registerRequest.getEmail())) {
            throw new IllegalArgumentException("Cette adresse email est déjà utilisée");
        }
        AppUser appUser = AppUser.builder()
                .nom(registerRequest.getNom())
                .prenom(registerRequest.getPrenom())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(appUser);
        String token = jwtService.generateToken(appUser);
        return new AuthResponse(token,appUser.getRole().name());

    }

    public AuthResponse authenticate(LoginRequest request) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );

            // on vérifie si la personne existe dans la base de données
            AppUser appUser = userRepository.findByEmail(request.getEmail())
                    .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

            String token = jwtService.generateToken(appUser);
            return new AuthResponse(token, appUser.getRole().name());
        } catch (BadCredentialsException e) {
            throw new RuntimeException("les identifiants sont incorrectes");
        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de l'authentification");
        }
    }

}
