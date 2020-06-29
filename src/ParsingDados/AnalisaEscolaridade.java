/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingDados;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alipio
 */
public class AnalisaEscolaridade {

    static boolean achado;
    //static String modalidade;
    static int linhacerta = 0;

    public static String ParsingEscolaridade(String texto) {
        //1. Lê linha a linha do currículo 
        String curriculo[] = texto.split("\n");
        for (int i = 1; i < curriculo.length; i++) {
            //2. Através do método AnalisaLinha verifica se tem algum nome relacionado
            // formação acadêmica, caso tiver guarda esse linha
            if (AnalisaLinha(curriculo[i])) {
                linhacerta = i;
                //3. Passa as informações para o método caputaEscolaridade, que retornará
                //somente a parte do currículo que terá parte das informações de escolaridade
                texto = CapturaEscolaridade(texto);
                
                //4. Por último é aplicado um regex no texto para classificar a escolaridade
                return ClassificaEscolariade(texto);


            }
        }
        return "Indefinido";

    }

    public static boolean AnalisaLinha(String linha) {
        Pattern cidadePattern = Pattern.compile("(Formação|FORMAÇÃO|Educação|Escolar|EDUCAÇÃO|ESCOLAR)");
        Matcher cidadeMatcher = cidadePattern.matcher(linha);
        achado = cidadeMatcher.find();

        return achado;
    }

    public static String CapturaEscolaridade(String texto) {
        String escolaridade = "";
        String curriculo[] = texto.split("\n");
        for (int i = linhacerta; i < linhacerta + 10; i++) {
            escolaridade += curriculo[i];
        }
        return escolaridade;
    }

    public static String ClassificaEscolariade(String texto) {
        Matcher ensinoMatcher;
        ArrayList<String> posGraduacao = new ArrayList<>(Arrays.asList(new String[]{"ós-Graduação", "estrado","ós Grad","ós grad"}));
        ArrayList<String> superior = new ArrayList<>(Arrays.asList(new String[]{"uperior", "ecnólogo", "acharel", "icenciatura",
            "FATEC", "USP", "ederal", "FTT", "aculdade"}));
        ArrayList<String> tecnico = new ArrayList<>(Arrays.asList(new String[]{"écnico", "ETEC"}));
        ArrayList<String> medio = new ArrayList<>(Arrays.asList(new String[]{"Médio", "médio"}));

        for (String palavra : posGraduacao) {
            Pattern tipoPattern = Pattern.compile(palavra);
            ensinoMatcher = tipoPattern.matcher(texto);

            if (ensinoMatcher.find()) {
                return "Pós-Graduação";
            }
        }
        
        for (String palavra : superior) {
            Pattern tipoPattern = Pattern.compile(palavra);
            ensinoMatcher = tipoPattern.matcher(texto);

            if (ensinoMatcher.find()) {
                return "Superior";
            }
        }
        
        for (String palavra : tecnico) {
            Pattern tipoPattern = Pattern.compile(palavra);
            ensinoMatcher = tipoPattern.matcher(texto);

            if (ensinoMatcher.find()) {
                return "Ensino Técnico";
            }
        }
        
        for (String palavra : medio) {
            Pattern tipoPattern = Pattern.compile(palavra);
            ensinoMatcher = tipoPattern.matcher(texto);

            if (ensinoMatcher.find()) {
                return "Ensino Médio";
            }    
        }
        
        Pattern tipoPattern = Pattern.compile("undamental");
        ensinoMatcher = tipoPattern.matcher(texto);
        
        if(ensinoMatcher.find()){
            return "Fundamental";
        }else{
            return "";
        }
            
        
        
        
        
        
        

    }

}
