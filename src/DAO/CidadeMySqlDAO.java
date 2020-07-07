/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import business.Cidade;
import java.sql.ResultSet;
import java.sql.SQLException;
import servicos.ServicoDeMensagens;

/**
 *
 * @author BRUNOSILVA
 */
public class CidadeMySqlDAO extends MySqlDAO<Cidade> {

    public CidadeMySqlDAO(Class<Cidade> entidade) {
        super(entidade);
        setTabela("cidade");
    }

    public CidadeMySqlDAO() {
        setTabela("cidade");
    }

    public String strSqlParaCidades(String chave, String valorChave) {
        String comando = "";

        comando += "select cidade.id,cidade.nome,estado.uf from cidade "
                + "inner join estado on cidade.estado=estado.id "
                + "where " + getTabela() + "." + chave + " = " + "'" + valorChave + "' ;";

        return comando;
    }

    @Override
    protected Cidade montaEntidade(ResultSet rs) {
        
        Cidade cid = new Cidade();

        try {
            cid.setId(rs.getInt("id"));
            cid.setNome(rs.getString("nome"));                    

            cid.setUf(rs.getString("uf"));

            return cid;
        } catch (SQLException ex) {
            ServicoDeMensagens.mensagem = "Erro na classe cidade";
            
        }
        return cid;
    }

}

