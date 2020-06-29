/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColetaDados;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import ParsingDados.AnalisaEmail;
import ParsingDados.AnalisaNome;
import ParsingDados.AnalisaTelefone;
import ParsingDados.AnalisaCidade;
import ParsingDados.AnalisaIdade;
import ParsingDados.AnalisaEscolaridade;
import ParsingDados.AnalisaCargo;
import business.Candidato;
import business.Cidade;
import DAO.CandidatoMySqlDAO;
import views.Login;
//import business.Candidato;
//import business.Cidade;

/**
 *
 * @author Alipio
 */
public class PDFMain {

    private static int loadStatus;
    private static int loadMax;
    private static boolean finalizado;

    public static int getLoadStatus() {
        return loadStatus;
    }

    public static int getLoadMax() {
        return loadMax; 
    }

    public static boolean isFinalizado() {
        return finalizado;
    }



    public static void CarregarArquivos(String pasta) throws IOException {
        CandidatoMySqlDAO DAO = new CandidatoMySqlDAO();
        
       

        //Cria uma lista para receber o retorno da classe PDFReaderFolders
        File[] listaArquivos;

        //Usa o método da classe PDFREaderFolders para ler cada um dos arquivos da pasta CONFIGURADA
        listaArquivos = PDFReaderFolders.FilesList(pasta);

        //Cria uma lista de Candidatos para Salvar as informações no banco
        ArrayList<Candidato> dados = new ArrayList();

        loadMax = listaArquivos.length;

        for (File listaArquivo : listaArquivos) {

            //1. Move o arquivo para pasta de arquivos lidos
            PDFMoveFolderFile.MoveFile(listaArquivo.toString());

            //2. Transforma o PDF em texto
            String texto = PDFBoxToText.TransformCurriculumText(listaArquivo.getName());

            //3. Instancia a Classe Candidato            
            Candidato candidato = new Candidato();
            Cidade cidade = new Cidade();
            //4. Pega os dados do Candidato
            candidato.setNome(AnalisaNome.ParsingNome(texto));
            candidato.setEmail(AnalisaEmail.ParsingEmail(texto));
            candidato.setTelefone(AnalisaTelefone.ParsingTelefone(texto));
            candidato.setNome_arquivo(listaArquivo.getName());

            cidade.setId(AnalisaCidade.ParsingCidade(texto));
            candidato.setCidade(cidade);

            candidato.setNascimento(AnalisaIdade.ParsingIdade(texto));
            candidato.setEscolaridade(AnalisaEscolaridade.ParsingEscolaridade(texto));
            candidato.setCargo(AnalisaCargo.ParsingCargo(texto));

            //5. Adiciona na lista de Candidado
            dados.add(candidato);
            loadStatus += dados.size();

        }

        for (Candidato pessoa : dados) {
            DAO.inserirEntidade(pessoa);
        }
        
        finalizado = true;

    }

}
