package com.example.bibliosystem.security.jwt;

import java.util.Date;

import com.example.bibliosystem.security.services.UserDetailsImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import io.jsonwebtoken.*;

/**
 * Classe utilitaire pour la gestion des JSON Web Token
 */
@Component
public class JwtUtils {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);
    @Value("${BiblioSystem.app.jwtSecret}")
    private String jwtSecret;
    @Value("${BiblioSystem.app.jwtExpirationMs}")
    private int jwtExpirationMs;

    /**
     * Méthode de génération d'un token
     * @param authentication object d'authentifcation
     * @return un token sous forme de chaine de caractère
     */
    public String generateJwtToken(Authentication authentication) {
        //Récupération des informations d'authentification
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        //génération d'un token
        //à partir du username = email
        //date d'expiration du token = date actuelle + xxx millisecondes
        //chiffrement du token avec l'algorithme HS512 et notre secret
        return Jwts.builder()
                .setSubject((userPrincipal.getUsername()))
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .signWith(SignatureAlgorithm.HS512, jwtSecret)
                .compact();
        }

    /**
     * Méthode de décodage du token
     * @param token un token
     * @return le contenu du subject du token(=adresse email)
     */
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody().getSubject();
    }

    /**
     * Méthode de validation d'un token
     * @param authToken un token
     * @return Booléen
     */
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(authToken);
            return true;
        } catch (SignatureException e) {
            logger.error("Signature JWT invalide: {}", e.getMessage());
        } catch (MalformedJwtException e) {
            logger.error("Token JWT invalide: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("Token JWT expiré: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("Token JWT non supporté: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("Requete JWT vide: {}", e.getMessage());
        }
        return false;
    }
}
