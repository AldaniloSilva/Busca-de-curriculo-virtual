/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Enums.EnumOperacao;
import business.Entidade;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BRUNOSILVA
 */
public class MySqlConexao {

    public static Connection geraConexao() throws SQLException {
        final String stringConexao = "jdbc:mysql://localhost:3306/projeto_rh?useTimezone=true&serverTimezone=UTC";
        final String usuario = "developer";
        final String senha = "123456";
        //final String usuario = "root";
        //final String senha = "";

        Connection conn = DriverManager
                .getConnection(stringConexao, usuario, senha);
        return conn;
    }

    

}
