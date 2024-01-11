/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utilities;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


/**
 *
 * @author edgar
 */
public class SecurityUtil {
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    public static boolean validatePassword(String providedPassword, String storedPasswordHash) {
        return encoder.matches(providedPassword, storedPasswordHash);
    }
}
