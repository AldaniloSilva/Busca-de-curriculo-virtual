/*
 * Essa Classe tem um método que será responsável por mover o arquivo para outro
 * diretorio depois que for processado.
 */
package ColetaDados;

import java.io.File;
import java.io.IOException;
import servicos.GerenciaPasta;

/**
 *
 * @author Alipio
 */
public class PDFMoveFolderFile {

    public static void MoveFile(String arquivoParaMover) throws IOException {
        // Arquivo a ser movido
        File arquivo = new File(arquivoParaMover);
        if (!arquivo.exists()) {
            System.out.println("Arquivo não encontrado");
        } else {
            // Diretorio de destino
            //File diretorioDestino = new File("C:\\Users\\Alipio\\Desktop\\N2-Ling.Programacao\\N2-Ling.Programacao\\ztestes\\");
            File diretorioDestino = GerenciaPasta.PastaDestino();
            
            

            
            // Move o arquivo para o novo diretorio
            //boolean sucesso = arquivo.renameTo(new File(diretorioDestino, arquivo.getName()));
             boolean sucesso = arquivo.renameTo(new File(diretorioDestino , arquivo.getName()));
            if (sucesso) {
                System.out.println("Arquivo movido para '" + diretorioDestino.getAbsolutePath() + "'");
                System.out.println(GerenciaPasta.PastaDestino().getPath());
            } else {
                System.out.println("Erro ao mover arquivo '" + arquivo.getAbsolutePath() + "' para '"
                        + diretorioDestino.getAbsolutePath() + "'");
                System.out.println(arquivo.getName());
            }
        }

    }

}
