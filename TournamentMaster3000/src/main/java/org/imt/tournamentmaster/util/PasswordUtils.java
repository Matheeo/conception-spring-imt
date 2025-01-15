package org.imt.tournamentmaster.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordUtils {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String encode(String mot_de_passe_claire){
        return passwordEncoder.encode(mot_de_passe_claire);
    }

    public Boolean check(String mot_de_passe_claire, String mot_de_passe_encode){
        return passwordEncoder.matches(mot_de_passe_claire, mot_de_passe_encode);
    }
}
