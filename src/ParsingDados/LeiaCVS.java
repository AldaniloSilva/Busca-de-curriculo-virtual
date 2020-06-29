/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParsingDados;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Alipio
 */
public  class LeiaCVS {
    
   
  public static int RetornaCodigoCidade(String uf, String string) {

    String arquivoCSV = "C:\\Users\\Alipio\\Desktop\\Currículos-FTT\\Cidades\\"+ uf + ".csv";
    BufferedReader br = null;
    String linha;
    String csvDivisor = ";";
    Pattern cidadePattern;
    Matcher cidadeMatcher;
    
    
    try {

        br = new BufferedReader(new FileReader(arquivoCSV));
        while ((linha = br.readLine()) != null) {

            String[] cidade = linha.split(csvDivisor);
            cidadePattern = Pattern.compile(cidade[1].toUpperCase());
            cidadeMatcher = cidadePattern.matcher(string.toUpperCase());
            if(cidadeMatcher.find()){
                int codigo = Integer.parseInt(cidade[0].trim());
                br.close();
                return codigo;                
            }
        }         

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } finally {
        if (br != null) {
            try {
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
  return -1; 
  }
}
