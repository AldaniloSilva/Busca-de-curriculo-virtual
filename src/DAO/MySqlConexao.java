/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import servicos.GerenciaPasta;

/**
 *
 * @author BRUNOSILVA
 */
public class MySqlConexao {

    public static Connection geraConexao() throws SQLException, IOException {
        ConfiguracaoBanco configuracao = GerenciaPasta.RetornaAcesso();
        final String stringConexao = "jdbc:mysql://" + configuracao.getConexao() + ":3306/projeto_rh?useTimezone=true&serverTimezone=UTC";
        final String usuario = configuracao.getUsuario();
        //final String senha = "";
        final String senha = configuracao.getSenha();

        Connection conn = DriverManager
                .getConnection(stringConexao, usuario, senha);
        return conn;
    }

}
