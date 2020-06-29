/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author BRUNOSILVA
 */
public class ThreadAbreArquivo extends Thread{
    
    private String arquivo;

    @Override
    public void run() {
        try {
            //if(new File(getArquivo()).exists()){
            Desktop.getDesktop().open(new File(getArquivo()));
            //}
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problema ao abrir o arquivo","Erro",JOptionPane.WARNING_MESSAGE);
            //Logger.getLogger(ThreadAbreArquivo.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
    
    
    
    
    
}
