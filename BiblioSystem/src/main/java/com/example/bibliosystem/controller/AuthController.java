package com.example.bibliosystem.controller;

import com.example.bibliosystem.common.ERole;
import com.example.bibliosystem.common.Helper;
import com.example.bibliosystem.entity.Role;
import com.example.bibliosystem.entity.User;
import com.example.bibliosystem.payload.request.LoginRequest;
import com.example.bibliosystem.payload.request.ProfileRequest;
import com.example.bibliosystem.payload.request.SignupRequest;
import com.example.bibliosystem.payload.response.JwtResponse;
import com.example.bibliosystem.payload.response.MessageResponse;
import com.example.bibliosystem.repository.RoleRepository;
import com.example.bibliosystem.repository.UserRepository;
import com.example.bibliosystem.security.jwt.JwtUtils;
import com.example.bibliosystem.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * controleur responsable de l'authentification et inscriprion des utilisateurs
 */
@CrossOrigin(origins ={"http://localhost:8081", "http://localhost:8080", "https://df-biblio-front.herokuapp.com"}, exposedHeaders = "**")
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserRepository userRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    PasswordEncoder encoder;
    @Autowired
    JwtUtils jwtUtils;

    /**
     * Gestion de l'authentification d'un utilisateur
     * @param loginRequest requ??te
     * @return r??ponse
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {

        //V??rification du mail (=username) et du mot de passe
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);

        //G??n??ration du JSON Web Token qui sera n??cessaire au frontend
        //pour appeler les services qui ont besoin d'une connexion
        String jwt = jwtUtils.generateJwtToken(authentication);

        //R??cup??ration des informations principales de l'utilisateur connect??
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        //R??cup??ration des r??les
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        //Passage des informations dans la r??ponse
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getNom(),
                userDetails.getPrenom(),
                userDetails.getUsername(),
                roles));
    }

    /**
     * Gestion de l'inscription d'un utilisateur
     * @param signUpRequest requ??te
     * @return r??ponse
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {

        //V??rification : l'utilisateur existe d??j???
        //L'email ne dois pas ??tre d??j?? pr??sent en base de donn??es
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur lors de l'inscription : email invalide"));
        }

        //V??rification des informations
        String messages = this.checkUserInfo(
                signUpRequest.getNom(),
                signUpRequest.getPrenom(),
                signUpRequest.getEmail(),
                signUpRequest.getPassword()
        );

        if(!messages.equals("")){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(messages));
        }


        // Cr??ation du nouvel utilisateur
        User user = new User(signUpRequest.getNom(), signUpRequest.getPrenom(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        //Gestion des r??les
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Erreur : r??le non trouv??"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur : r??le non trouv??"));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Erreur : r??le non trouv??"));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("Utilisateur enregistr??"));
    }

    /**
     * Mise ?? jour des informations de l'utilisateur
     * @param profileRequest requ??te
     * @return r??ponse
     */
    @PostMapping("/profile")
    public ResponseEntity<?> updateProfile (@RequestBody ProfileRequest profileRequest) {

        //Pour mettre ?? jour les informations d'un utilisateur il faut que celui ci existe
        //on utilise Optional pour g??rer simplement le cas <null>
        Optional<User> optUser =  userRepository.findByEmail(profileRequest.getEmail());
        if(!optUser.isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Erreur: L'utilisateur n'existe pas"));
        }

        String messages = this.checkUserInfo(
                profileRequest.getNom(),
                profileRequest.getPrenom(),
                profileRequest.getEmail(),
                profileRequest.getPassword()
        );

        if(!messages.equals("")){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(messages));
        }

        User user = new User(
                optUser.get().getUserId(),
                profileRequest.getNom(),
                profileRequest.getPrenom(),
                profileRequest.getEmail(),
                encoder.encode(profileRequest.getPassword())
        );
        Set<String> strRoles = profileRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Erreur : r??le non trouv??"));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Erreur : r??le non trouv??"));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Erreur : r??le non trouv??"));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("utilisateur enregistr??"));
    }

    /**
     * M??thode priv??e responsable des
     * @param sNom nom de l'utilsateur
     * @param sPrenom prenom de l'utilsateur
     * @param sEmail email de l'utilisateur
     * @param sPwd mot de passe de l'utilisateur
     * @return message d'erreur
     */
    private String checkUserInfo(String sNom, String sPrenom, String sEmail, String sPwd){
        String messages = "";
        String passw = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

        if (!Helper.checkStringLengthBetween(sNom,2,100)) {
            messages = messages.concat( "Nom invalide |" );
        }

        if (!Helper.checkStringLengthBetween(sPrenom,2,100)) {
            messages = messages.concat( "Prebom invalide |" );
        }

        if(sEmail.equals("null")
                || !sEmail.contains("@")
                || !sEmail.contains(".")
                || !Helper.checkStringLengthBetween(sEmail,5,100)){
            messages = messages.concat(" Email invalide |");
        }

        if(sPwd.equals("null") || !sPwd.matches(passw)) {
            messages = messages.concat(" Mot de passe invalide |");
        }

        return messages;
    }
}
