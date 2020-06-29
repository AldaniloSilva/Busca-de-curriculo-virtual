/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import business.Entidade;
import java.sql.PreparedStatement;
import repositorio.IRepositorio;

/**
 *
 * @author BRUNOSILVA
 */
public abstract class PadraoDAO<T extends Entidade> {
    private String tabela;     
    

    protected PadraoDAO(Class<T> entidade) {
        this.tabela = entidade.getName().toLowerCase();       
    }  
    
    public PadraoDAO() {

    }
    

    public String getTabela() {
        return tabela;
    }

    protected void setTabela(String tabela) {
        this.tabela = tabela;
    }
    
    

    
    
    

   

   
    
    
}
