package com.example.bibliosystem.controller;

import com.example.bibliosystem.*;
import com.example.bibliosystem.payload.request.LoginRequest;
import com.example.bibliosystem.payload.request.ProfileRequest;
import com.example.bibliosystem.payload.request.SignupRequest;
import com.example.bibliosystem.payload.response.JwtResponse;
import com.example.bibliosystem.payload.response.MessageResponse;
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

import java.util.*;
import java.util.regex.Matcher;

import java.util.stream.Collectors;

@CrossOrigin(origins ={"http://localhost:8081", "http://localhost:8080"})
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

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getNom(),
                userDetails.getPrenom(),
                userDetails.getUsername(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        System.out.println(signUpRequest);

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }



        String messages = "";

        if (signUpRequest.getNom().equals("null") || signUpRequest.getNom().length() < 2) {

            messages = "Nom invalide";
        }

        if (signUpRequest.getPrenom().equals("null") || signUpRequest.getPrenom().length() < 2) {

            messages = messages.concat(", Prenom invalide");
        }

        if(signUpRequest.getEmail().equals("null") || !signUpRequest.getEmail().contains("@") && !signUpRequest.getEmail().contains(".")){

            messages = messages.concat(", Email invalide");
        }

        String passw = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

        if(signUpRequest.getPassword().equals("null") || !signUpRequest.getPassword().matches(passw)) {

            messages = messages.concat(", Mot de passe invalide");
        }

        if(!messages.equals("")){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(messages));
        }


        // Create new user's account
        User user = new User(signUpRequest.getNom(), signUpRequest.getPrenom(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));
        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/profile")
    public ResponseEntity<?> updateProfile (@RequestBody ProfileRequest profileRequest) {

        Optional<User> optUser =  userRepository.findByEmail(profileRequest.getEmail());
        if(!optUser.isPresent()){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: L'utilisateur n'existe pas"));
        }

        String messages = "";

        if (profileRequest.getNom().equals("null") || profileRequest.getNom().length() < 2) {

            messages = "Nom invalide";
        }

        if (profileRequest.getPrenom().equals("null") || profileRequest.getPrenom().length() < 2) {

            messages = messages.concat(", Prenom invalide");
        }

        if(profileRequest.getEmail().equals("null") || !profileRequest.getEmail().contains("@") && !profileRequest.getEmail().contains(".")){

            messages = messages.concat(", Email invalide");
        }

        String passw = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,20}$";

        if(profileRequest.getPassword().equals("null") || !profileRequest.getPassword().matches(passw)) {

            messages = messages.concat(", Mot de passe invalide");
        }

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
                encoder.encode(profileRequest.getPassword()));
        Set<String> strRoles = profileRequest.getRole();
        Set<Role> roles = new HashSet<>();
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin" -> {
                        Role adminRole = roleRepository.findByName(ERole.ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                    }
                    default -> {
                        Role userRole = roleRepository.findByName(ERole.USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                    }
                }
            });
        }
        user.setRoles(roles);
        userRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("User updated successfully!"));
    }
}
