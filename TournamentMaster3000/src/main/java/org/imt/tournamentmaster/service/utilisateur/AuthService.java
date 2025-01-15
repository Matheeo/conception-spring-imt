package org.imt.tournamentmaster.service.utilisateur;

import org.imt.tournamentmaster.dto.authentification.AuthentificationLoginDto;
import org.imt.tournamentmaster.dto.utilisateur.UtilisateurLoginDto;
import org.imt.tournamentmaster.dto.utilisateur.UtilisateurRegisterDto;
import org.imt.tournamentmaster.model.utilisateur.Utilisateur;
import org.imt.tournamentmaster.repository.utilisateur.UtilisateurRepository;
import org.imt.tournamentmaster.util.JwtUtils;
import org.imt.tournamentmaster.util.PasswordUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final JwtUtils jwtUtils;

    private final UtilisateurRepository utilisateurRepository;

    private final PasswordUtils passwordUtils;

    @Autowired
    public AuthService(JwtUtils jwtUtils, UtilisateurRepository utilisateurRepository, PasswordUtils passwordUtils) {
        this.jwtUtils = jwtUtils;
        this.utilisateurRepository = utilisateurRepository;
        this.passwordUtils = passwordUtils;
    }

    public void register(UtilisateurRegisterDto userDto){
        Utilisateur utilisateur = new Utilisateur();

        utilisateur.setNom(userDto.getNom());
        utilisateur.setPrenom(userDto.getPrenom());
        utilisateur.setEmail(userDto.getEmail());
        utilisateur.setMot_de_passe(this.passwordUtils.encode(userDto.getMot_de_passe()));

        this.utilisateurRepository.save(utilisateur);
    }

    public ResponseEntity<Object> login(UtilisateurLoginDto utilisateurLoginDto){
        Utilisateur utilisateur = utilisateurRepository.getUtilisateurByEmail(utilisateurLoginDto.getEmail());

        // On verifie si le mot de passe et l'identifiant corresponde
        if (utilisateur != null && passwordUtils.check(utilisateurLoginDto.getMot_de_passe(), utilisateur.getMot_de_passe())){

            String[] roles = utilisateur.isEst_administrateur() ? new String[]{"ADMIN", "USER"} : new String[]{"USER"};

            String token = jwtUtils.genereToken(utilisateur.getEmail(), roles);

            AuthentificationLoginDto tokenResponse = new AuthentificationLoginDto();
            tokenResponse.setToken(token);

            return ResponseEntity.status(HttpStatus.CREATED).body(tokenResponse);
        } else {
            return ResponseEntity.badRequest().body("error");
        }
    }
}
