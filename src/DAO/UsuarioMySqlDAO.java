/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Enums.EnumOperacao;
import Enums.EnumTipoAcesso;
import business.Usuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import servicos.ServicoDeMensagens;

/**
 *
 * @author BRUNOSILVA
 */
public class UsuarioMySqlDAO extends MySqlDAO<Usuario> {

    public UsuarioMySqlDAO(Class<Usuario> entidade) {
        super(entidade);
        setTabela("usuario");
    }

    public UsuarioMySqlDAO() {
        setTabela("usuario");
    }

    @Override
    protected PreparedStatement adicionaParametrosNoBanco(Usuario entidade,
            PreparedStatement params,
            EnumOperacao operacao) throws SQLException {

        switch (operacao) {
            case INSERIR:

                params.setString(1, entidade.getNome());
                params.setString(2, entidade.getSenha());
                params.setString(3, entidade.getTipoAcesso().getDescricao());
                break;

            case ALTERAR:

                params.setString(1, entidade.getNome());
                params.setString(2, entidade.getSenha());
                params.setString(3, entidade.getTipoAcesso().getDescricao());
                params.setInt(4, entidade.getId());
                break;

            case DELETAR:
                params.setInt(1, entidade.getId());
        }

        return params;

    }

    @Override
    protected Usuario montaEntidade(ResultSet rs) {
        Usuario usu = new Usuario();
        try {
            usu.setId(rs.getInt("id"));
            usu.setNome(rs.getString("nome"));
            usu.setSenha(rs.getString("senha"));
            String acesso = rs.getString("tipoAcesso");
            switch (acesso) {
                case "Administrador":
                    usu.setTipoAcesso(EnumTipoAcesso.ADM);
                    break;
                case "Padrão":
                    usu.setTipoAcesso(EnumTipoAcesso.PADRAO);
                    break;
            }

            return usu;
        } catch (SQLException ex) {
            ServicoDeMensagens.mensagem = ex.getMessage();
            //Logger.getLogger(UsuarioMySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usu;
    }

}

/*
@Override
    protected String montaStringSql(EnumOperacao operacao) {
        String enviaComando = "";
        switch (operacao) {
            case INSERIR:
                enviaComando += "insert into " + getTabela()
                        + " (nome,senha,tipoAcesso) "
                        + "values (?,?,?)";
                break;
            case ALTERAR:
                enviaComando += "update " + getTabela()
                        + " set nome = ?, senha=?, tipoAcesso=?"
                        + " where id = ?";
                break;

            case DELETAR:
                enviaComando += "delete from " + getTabela()
                        + " where id = ?";
                break;
        }
        return enviaComando;
    }

 */
