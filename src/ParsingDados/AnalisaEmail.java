/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingDados;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alipio
 */
public class AnalisaEmail {
    
    public static String ParsingEmail(String texto){
        
        
            Pattern emailPattern = Pattern.compile("[a-zA-Z0-9_.-]+@[a-zA-Z0-9-]+.[a-zA-Z0-9-.]+");
            Matcher emailMatcher = emailPattern.matcher(texto);
            emailMatcher.find();
            String email = emailMatcher.group(0);
            

        return email;
    }
    
}
