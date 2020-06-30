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
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Alipio
 */
public class PDFMain {

    private static int loadStatus;
    private static int loadMax;
    private static boolean finalizado;
    //   
    
    
    //    

    public static int getLoadStatus() {
        return loadStatus;
    }

    public static int getLoadMax() {
        return loadMax;
    }

    public static boolean isFinalizado() {
        return finalizado;
    }

    public static void CarregarArquivos(String pasta) throws IOException, SQLException {
        CandidatoMySqlDAO DAO = new CandidatoMySqlDAO();

        //Cria uma lista para receber o retorno da classe PDFReaderFolders
        File[] listaArquivos;

        //Usa o método da classe PDFREaderFolders para ler cada um dos arquivos da pasta CONFIGURADA
        listaArquivos = PDFReaderFolders.FilesList(pasta);

        //Cria uma lista de Candidatos para Salvar as informações no banco
        ArrayList<Candidato> dados = new ArrayList();

        //loadMax = listaArquivos.length;
        loadMax = 100;
        
       

        for (File listaArquivo : listaArquivos) {

            //1. Move o arquivo para pasta de arquivos lidos
            PDFMoveFolderFile.MoveFile(listaArquivo.toString());

            //2. Transforma o PDF em texto
            String texto = PDFBoxToText.TransformCurriculumText(listaArquivo.getName());
            //3. Instancia a Classe Candidato            
            Candidato candidato = new Candidato();
            Cidade cidade = new Cidade();
            //4. Pega os dados do Candidato
            String arqProblema = "";

            try {

                candidato.setNome(AnalisaNome.ParsingNome(texto).trim());
                arqProblema = candidato.getNome();

                candidato.setEmail(AnalisaEmail.ParsingEmail(texto));
                candidato.setTelefone(AnalisaTelefone.ParsingTelefone(texto).trim());
                candidato.setNome_arquivo(listaArquivo.getName().trim());

                cidade.setId(AnalisaCidade.ParsingCidade(texto));
                candidato.setCidade(cidade);

                candidato.setNascimento(AnalisaIdade.ParsingIdade(texto));
                candidato.setEscolaridade(AnalisaEscolaridade.ParsingEscolaridade(texto).trim());
                candidato.setCargo(AnalisaCargo.ParsingCargo(texto));
                dados.add(candidato);
            } catch (IOException | IllegalStateException e) {
                //ServicoDeMensagens.mensagem = e.getMessage();
                System.out.println("Problema: " + arqProblema);
            }
            

            //5. Adiciona na lista de Candidado
            //loadStatus += dados.size();
            
            try {
                Thread.sleep(5);
            } catch (InterruptedException ex) {
                Logger.getLogger(PDFMain.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        }

        for (Candidato pessoa : dados) {

            boolean jaExiste = DAO.validaSeRepete(pessoa);
            if (!jaExiste) {
                DAO.inserirEntidade(pessoa);
            } else {
                DAO.alterarEntidade(pessoa.getId(), pessoa);
            }

            //DAO.inserirEntidade(pessoa);
        }        
       

        finalizado = true;

    }

}
