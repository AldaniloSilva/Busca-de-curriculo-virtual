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
public class AnalisaNome {
    static boolean achado;
    public static String ParsingNome(String texto){
         //1. Lê linha a linha do currículo         
        String nome="";        
        String curriculo [] = texto.split("\n");
        for (int i=0; i < curriculo.length; i++){
        //2. Analisa a linha através do Regex do método ProcuraNome
            if(ProcuraNome(curriculo[i])){                
                nome = curriculo[i];
                break;
            }                    
              }
    return nome;
    }
    
    public static boolean ProcuraNome(String linha){
        Pattern nomePattern = Pattern.compile("[A-ZÀ-Ÿ][A-zÀ-ÿ']+\\s([A-zÀ-ÿ']\\s?)*[A-ZÀ-Ÿ][A-zÀ-ÿ']+");
        Matcher nomeMatcher = nomePattern.matcher(linha);
        achado = nomeMatcher.find();
        
        return achado;
       
    }
    
}
