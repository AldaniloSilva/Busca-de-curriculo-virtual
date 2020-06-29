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
public class AnalisaTelefone {

    static boolean achado;

    public static String ParsingTelefone(String texto) {
        String telefone = PegaTelefone(texto);
        return telefone;
    }

    public static String PegaTelefone(String texto) {
        Matcher telefoneMatcher;
        Pattern telefonePatternA = Pattern.compile("(\\(?\\d{2,3}\\)?\\s)?(\\d{4,5}\\-\\d{4})");
        Pattern telefonePatternB = Pattern.compile("\\(([^()]+)\\) (\\(?\\d{2}\\)?\\s)?(\\d{4,5}\\-\\d{4})");
        Pattern telefonePatternC = Pattern.compile("\\(([^()]+)\\) \\d{9}");
        Pattern telefonePatternD = Pattern.compile("\\(([^()]+)\\) 9 \\d{4}-\\d{4}");
        Pattern telefonePatternE = Pattern.compile("\\(([^()]+)\\) \\d{5}.\\d{4}");
        Pattern telefonePatternF = Pattern.compile("\\(([^()]+)\\)\\d{9}");

        telefoneMatcher = telefonePatternA.matcher(texto);
        if (achado = telefoneMatcher.find()) {
            return telefoneMatcher.group(0);
        }

        telefoneMatcher = telefonePatternB.matcher(texto);
        if (achado = telefoneMatcher.find()) {
            return telefoneMatcher.group(0);
        }

        telefoneMatcher = telefonePatternC.matcher(texto);
        if (achado = telefoneMatcher.find()) {
            return telefoneMatcher.group(0);
        }

        telefoneMatcher = telefonePatternD.matcher(texto);
        if (achado = telefoneMatcher.find()) {
            return telefoneMatcher.group(0);
        }

        telefoneMatcher = telefonePatternE.matcher(texto);
        if (achado = telefoneMatcher.find()) {
            return telefoneMatcher.group(0);
        }

        telefoneMatcher = telefonePatternF.matcher(texto);
        if (achado = telefoneMatcher.find()) {
            return telefoneMatcher.group(0);
        }

        return "";
    }
}
