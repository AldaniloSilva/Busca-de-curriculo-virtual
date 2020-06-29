/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zNaousar;

import Enums.EnumOperacao;
import business.Entidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import repositorio.IRepositorio;

/**
 *
 * @author BRUNOSILVA
 */
public class RepositorioMySQL implements IRepositorio<Entidade> {   
  
    

    @Override
    public void inserirEntidade(Entidade entidade) {
        
        
    }

    @Override
    public void alterarEntidade(int id, Entidade entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletarEntidade(int id, Entidade entidade) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Entidade consultaPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

  

   

   
    
}
