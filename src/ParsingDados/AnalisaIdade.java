/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingDados;

import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alipio
 */
public class AnalisaIdade {
    
    static int ano;
    static boolean achado;
    static Calendar cal = Calendar.getInstance();
    static int year = cal.get(Calendar.YEAR);
    
     public static int ParsingIdade(String texto) {        
            
       
               
        String curriculo [] = texto.split("\n");
        for (int i=0; i < curriculo.length; i++){
            if(AnalisaLinha(curriculo[i])){

               return ano;
            }                    
              }
        return 2020;
   
    }
    
    
    public static boolean AnalisaLinha(String linha){
        Pattern idadePattern = Pattern.compile("(Nasc|nasc|NASC)");
        Matcher idadeMatcher = idadePattern.matcher(linha);
        achado = idadeMatcher.find();
        
        if(achado){
              if(CapturaAno(linha)){
                 return achado;
            }                       
    }
        idadePattern = Pattern.compile("[^A-z](idade|Idade|anos|Anos|IDADE|ANOS)");
        idadeMatcher = idadePattern.matcher(linha);
        achado = idadeMatcher.find();
        
        if(achado){
            if(CalculaAno(linha)){
                return achado;
        }
               
    }
     return false;   
    }
    
    
     public static boolean CapturaAno(String linha){
        Pattern idadePattern = Pattern.compile("(\\d{4})");
        Matcher idadeMatcher = idadePattern.matcher(linha);
        achado = idadeMatcher.find();
        try{
            if(achado)
                ano = Integer.parseInt(idadeMatcher.group(0));            
            
        }catch(Exception ex){
            return false;
        }
        
        return achado;     
        
     }
    
    
    public static boolean CalculaAno(String linha){
        Pattern idadePattern = Pattern.compile("(\\d{2})");
        Matcher idadeMatcher = idadePattern.matcher(linha);
        achado = idadeMatcher.find();
        try{
            if(achado)
                ano = 2020 - Integer.parseInt(idadeMatcher.group(0));
            
            
        }catch(Exception ex){
            return false;
        }
        
        return achado; 

       
    }
            
    
    }
    

