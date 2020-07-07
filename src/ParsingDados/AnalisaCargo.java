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
public class AnalisaCargo {

    static boolean achado;
    static int linhacerta = 0;

    public static String ParsingCargo(String texto) {
        //1. Lê linha a linha do currículo 
        String curriculo[] = texto.split("\n");
        for (int i = 1; i < curriculo.length; i++) {
            //2. Através do método AnalisaLinha verifica se tem algum nome relacionado
            // objetivo ou interesses
            if (AnalisaLinha(curriculo[i])) {
                linhacerta = i;

                //3. Passa as informações para o método caputaObjetivo, que retornará
                //somente a parte do currículo que terá parte das informações de Interesses e Objetivos do cargo
                return CapturaCargo(texto);

            }
        }
        return "Indefinido";

    }

    public static boolean AnalisaLinha(String linha) {
        Pattern cidadePattern = Pattern.compile("(Objetivo|Interesse|OBJETIVO)");
        Matcher cidadeMatcher = cidadePattern.matcher(linha);
        achado = cidadeMatcher.find();

        return achado;
    }

    public static String CapturaCargo(String texto) {
        
        String curriculo[] = texto.split("\n");
        for (int i = linhacerta + 1; i < curriculo.length; i++) {
            if(!TestaEspaco(curriculo[i]))                
                return curriculo[i].replace("\r","");
        }

        return "";
    }

    public static boolean TestaEspaco(String linha) {
        Pattern espacoPattern = Pattern.compile("^\\s$");
        Matcher espacoMatcher = espacoPattern.matcher(linha);
        return espacoMatcher.find();
        
    }

}
