/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import DAO.ConfiguracaoBanco;
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
    private static final String ACESSO = "banco.conf";
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

    public static void SalvaDadosAcesso(String texto) throws IOException {
        new File(ACESSO).createNewFile();
        FileWriter escrever = new FileWriter(ACESSO, false);
        BufferedWriter bw = new BufferedWriter(escrever);
        bw.write(texto);
        bw.close();
        escrever.close();
    }

    public static ConfiguracaoBanco RetornaAcesso() throws FileNotFoundException, IOException {
        File arquivo = new File(ACESSO);
        String[] caminho = new String[3];
        ConfiguracaoBanco dados = new ConfiguracaoBanco();
        dados.setSenha("");  
        
        if (arquivo.exists()) {
            FileReader ler = new FileReader(ACESSO);
            BufferedReader br = new BufferedReader(ler);
            while (br.ready()) {
                caminho = br.readLine().split("=");

            }
            br.close();
            ler.close();
        }
        
        dados.setConexao(caminho[0]);
        dados.setUsuario(caminho[1]);
        
        if(caminho.length> 2){
            dados.setSenha(caminho[2]);
        }
       

        return dados;

    }

    public static File PastaDestino() throws IOException {

        File diretorioTeste = new File(new File(".").getCanonicalPath() + "\\Projeto-Rh\\");
        if (!diretorioTeste.exists()) {

            diretorioTeste.mkdir();
        }
        PastaDestino = true;

        return diretorioTeste;

    }

}
