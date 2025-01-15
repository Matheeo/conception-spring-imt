package org.imt.tournamentmaster.controller.utilisateur;

import org.imt.tournamentmaster.dto.utilisateur.UtilisateurLoginDto;
import org.imt.tournamentmaster.dto.utilisateur.UtilisateurRegisterDto;
import org.imt.tournamentmaster.service.utilisateur.AuthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authentification")
public class AuthController {

    private static final Logger log = LoggerFactory.getLogger(AuthController.class);

    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody UtilisateurRegisterDto userDto) {
        try {
            authService.register(userDto);
            return ResponseEntity.status(HttpStatus.CREATED).body("created");
        } catch (Exception e){
            log.info(e.toString());
            return ResponseEntity.badRequest().body("error");
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody UtilisateurLoginDto utilisateurLoginDto){

        try {
            return authService.login(utilisateurLoginDto);
        } catch (Exception e){
            log.info(e.toString());
            return ResponseEntity.badRequest().body("error");
        }

    }
}
