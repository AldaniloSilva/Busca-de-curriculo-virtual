/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingDados;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alipio
 */
public class AnalisaCidade {

    static boolean achado;
    static String cidade;

    public static int ParsingCidade(String texto) throws IOException {

        String curriculo[] = texto.split("\n");
        for (int i = 1; i < curriculo.length; i++) {
            if (AnalisaLinha(curriculo[i])) {
                return ValidaCidadeBanco(cidade);
            }
        }
        return 6000;

    }

    public static boolean AnalisaLinha(String linha) {
        Pattern cidadePattern = Pattern.compile("[,/:-–(]");
        Matcher cidadeMatcher = cidadePattern.matcher(linha);
        achado = cidadeMatcher.find();

        if (achado) {
            linha = linha.replace("CEP", "");
            linha = linha.replace("CEL", "");
            if (ProcuraUf(linha)) {
                if (ProcuraCidade(linha)) {
                    return achado;
                }
            }

        }
        return false;
    }

    public static boolean ProcuraUf(String linha) {
        Pattern cidadePattern = Pattern.compile("(AC|AL|AM|AP|BA|CE|DF|ES|GO|MA|MG|MS|MT|PA|PB|PE|PI|PR|RJ|RN|RO|RR|RS|SC|SE|SP|TO)");
        Matcher cidadeMatcher = cidadePattern.matcher(linha);
        achado = cidadeMatcher.find();
        return achado;

    }

    public static boolean ProcuraCidade(String linha) {
        Pattern cidadePattern = Pattern.compile("[^-,:–]+(.(/| |-)(AC|AL|AP|AM|BA|CE|DF|ES|GO|MA|MT|MS|MG|PA|PB|"
                + "PR|PE|PI|RJ|RN|RS|RO|RR|SC|SP|SE|TO))[^A-z]");
        Matcher cidadeMatcher = cidadePattern.matcher(linha);
        achado = cidadeMatcher.find();
        if (achado) {
            cidade = cidadeMatcher.group(0);
        }

        return achado;
    }

    public static int ValidaCidadeBanco(String linha) throws IOException {
        Pattern cidadePattern;
        Matcher cidadeMatch;
        ArrayList<String> estados = new ArrayList<>(Arrays.asList(new String[]{"AC", "AL", "AP", "AM", "BA", "CE", "DF", "ES", "GO", "MA", "MT", "MS", "MG", "PA", "PB", "PR", "PE", "PI", "RJ", "RN", "RS", "RO", "RR", "SC", "SP", "SE", "TO"}));
        for (String uf : estados) {
            cidadePattern = Pattern.compile(uf);
            cidadeMatch = cidadePattern.matcher(linha);
            if (cidadeMatch.find()) {
                return LeiaCVS.RetornaCodigoCidade(uf, linha);
            }
        }
        return 6000;
    }

}
