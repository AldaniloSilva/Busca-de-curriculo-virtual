/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repositorio;

import business.Entidade;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BRUNOSILVA
 */
public interface IRepositorio<T extends Entidade> {    
    
    public void inserirEntidade(T entidade)throws SQLException ;
    public void alterarEntidade(int id,T entidade)throws SQLException ;
    public void deletarEntidade(int id, T entidade)throws SQLException ;
    public T consultaPorId(int id)throws SQLException ;
    
    //public T consultaGenerica(String comandoSql)throws SQLException ;
    //public ArrayList<T> listagemDeEntidade(String strParaSelect)throws SQLException ; 
    
    
}
