/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author Alipio
 */
public class GerenciaPasta {

    private static final String ARQUIVO = "pathConf.txt";
    private static boolean PastaDestino = false;

    public static boolean isPastaDestino() {
        return PastaDestino;
    }

    public static void SalvaCaminhoPasta(String texto) throws IOException {
        //String arquivo = "pathConf.txt";
        new File(ARQUIVO).createNewFile();
        FileWriter escrever = new FileWriter(ARQUIVO, false);
        BufferedWriter bw = new BufferedWriter(escrever);
        bw.write(texto);
        bw.close();
        escrever.close();
    }

    public static String RetornaCaminhoPasta() throws FileNotFoundException, IOException {
        File arquivo = new File(ARQUIVO);
        String caminho = "";
        if (arquivo.exists()) {
            FileReader ler = new FileReader(ARQUIVO);
            BufferedReader br = new BufferedReader(ler);
            while (br.ready()) {
                caminho = br.readLine();
            }
            br.close();
            ler.close();
        }
        

        return caminho;

    }

    public static File PastaDestino() throws IOException {

        File diretorioTeste = new File(new File(".").getCanonicalPath() + "\\Projeto-Rh\\" );
        if (!diretorioTeste.exists()) {
           
            diretorioTeste.mkdir();
        }
        PastaDestino = true;

        return diretorioTeste;

    }

}
