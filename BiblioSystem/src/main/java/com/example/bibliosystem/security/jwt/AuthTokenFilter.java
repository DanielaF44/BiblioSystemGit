package com.example.bibliosystem.security.jwt;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.bibliosystem.security.services.UserDetailsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * Classe de filtre permettant de vérifier si un token passé dans une requête est valide
 * Hérite de  OncePerRequestFilter car on ne veut exécuter ce filtre qu'une seule fois par requête
 * on va stocker les infos de l'authentification dans un objet nommé SecurityContextholder
 * Donc pas besoin d'appeler à nouveau ce filtre
 */
public class AuthTokenFilter extends OncePerRequestFilter{
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsService;
    private static final Logger logger = LoggerFactory.getLogger(AuthTokenFilter.class);

    /**
     * Filtre spécifique au JWT
     * @param request une requête
     * @param response une réponse
     * @param filterChain une chaine de filtre
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            //récupération du token dans le HTTP header de la requête
            String jwt = parseJwt(request);
            if (jwt != null && jwtUtils.validateJwtToken(jwt)) {
                //Récupération de l'email via le token et le secret de hashage
                String username = jwtUtils.getUserNameFromJwtToken(jwt);
                //Récupération des informations de l'utilisateur
                UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                // authentification
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                        userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                //Sauvegarde en mémoire des données d'authentifcation pour cet appel
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        } catch (Exception e) {
            logger.error("Authentification impossible: {}", e);
        }
        //execution du reste de la chaine de filtre
        filterChain.doFilter(request, response);
    }

    /**
     * Méthode qui retourne le token issu du header d'une requête HTTP
     * @param request la requêtre HTTP
     * @return le token
     */
    private String parseJwt(HttpServletRequest request) {
        //format : Authorization: Bearer <token>
        //Etape 1 : récupérer le paramètre "Authorization" dans le header
        String headerAuth = request.getHeader("Authorization");
        //on retire le début de la chaine qui indique que le token est de type "bearer" = 7 premiers caractères
        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith("Bearer ")) {
            return headerAuth.substring(7, headerAuth.length());
        }
        return null;
    }

}
