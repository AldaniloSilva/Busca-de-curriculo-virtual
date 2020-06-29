/*
 * Essa classe le os nomes dos arquivos pdf de uma pasta
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ColetaDados;

import java.io.File;
import java.io.FileFilter;

/**
 *
 * @author Alipio
 */
public class PDFReaderFolders {

    static FileFilter filter = new FileFilter() {
        @Override
        public boolean accept(File file) {
            return file.getName().endsWith(".pdf");
        }
    };

    public static File [] FilesList(String caminho){
        File [] files;
    
    File dir = new File(caminho);
    files = dir.listFiles(filter);
    return files;
}

}
