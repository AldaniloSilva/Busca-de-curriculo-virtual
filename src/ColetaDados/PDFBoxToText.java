/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColetaDados;

import java.io.File;
import java.io.IOException;
import servicos.GerenciaPasta;

/**
 *
 * @author Alipio
 */
public class PDFBoxToText {
    
    public static String TransformCurriculumText(String arquivo) throws IOException {
        String text = null;
        
        //Instancia um objeto da classe PDFManager
        PDFManager pdfManager = new PDFManager();
        
        //Envia o caminho do arquivo para ser lido
        //pdfManager.setFilePath("C:\\Users\\Alipio\\Desktop\\N2-Ling.Programacao\\N2-Ling.Programacao\\ztestes\\" + arquivo);
       
        pdfManager.setFilePath(servicos.GerenciaPasta.PastaDestino().getPath() + "\\" + arquivo);

        try {
            //Atribue o retorno da classe pdfManager para a String text
            text = pdfManager.toText();          

        } catch (IOException ex) {

            System.err.println(ex.getMessage());
        }
        finally{
             pdfManager.getPdDoc().close();
        }  
        
        return text;
       
        
        
    }
    
}
