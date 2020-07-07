/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servicos;

import Acesso.UsuarioLogado;
import DAO.UsuarioMySqlDAO;
import Enums.EnumComparadorQuery;
import Enums.EnumOperacao;
import business.CamposDeClasse;
import business.Usuario;
import java.lang.reflect.Field;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author BRUNOSILVA
 */
public class Validacoes {
    
    public static boolean validaUsuarioSenha(Usuario usu) throws IllegalArgumentException,
            IllegalAccessException, SQLException {

        boolean valido = false;

        Class<?> classeEntidade = usu.getClass();
        ArrayList<CamposDeClasse> camposParaFiltro = new ArrayList<>();

        for (Field atributo : classeEntidade.getDeclaredFields()) {
            atributo.setAccessible(true);
            if (!atributo.getName().toLowerCase().equals("id")
                    && !atributo.getName().toLowerCase().equals("tipoacesso")) {

                CamposDeClasse campos = new CamposDeClasse();
                campos.setNomeChave(" BINARY " + atributo.getName());
                campos.setValorChave((String) atributo.get(usu));
                
                campos.setComparador(EnumComparadorQuery.IGUAL);
                campos.setOperadorLogicoE(true);

                camposParaFiltro.add(campos);
            }
        }

        UsuarioMySqlDAO dao = new UsuarioMySqlDAO();
        String comando = dao.stringSelectTabelaComFiltro(camposParaFiltro);
        Usuario usuarioConsultado = dao.consultaGenerica(comando);
        if (usuarioConsultado != null) {
            valido = true;
            UsuarioLogado.getInstancia().adicionaUsuario(usuarioConsultado);
        } else {
            ServicoDeMensagens.mensagem = "Usuário e/ou Senha Inválidos!";
        }

        return valido;
    }

    public static boolean validaCamposCadastroUsuario(Usuario usu, EnumOperacao operacao) throws SQLException {
        boolean valido = true;
        UsuarioMySqlDAO dao = new UsuarioMySqlDAO();

        if (usu.getNome().isEmpty()) {
            valido = false;
            ServicoDeMensagens.mensagem = "Não pode deixar campo 'nome' em branco!!!";
        } else if (usu.getSenha().length() < 6 || usu.getSenha().length() > 10) {
            if (!operacao.equals(EnumOperacao.DELETAR)) {
                valido = false;
                ServicoDeMensagens.mensagem = "Número de caracteres tem que ser no minimo 6"
                        + "e no máximo 10!";
            }
        } else if (EnumOperacao.DELETAR.equals(operacao)
                || EnumOperacao.ALTERAR.equals(operacao)) {
            Usuario consultaPorId = dao.consultaPorId(usu.getId());
            if (consultaPorId == null) {
                valido = false;
                ServicoDeMensagens.mensagem = "Usuário não encontrado";
            }
        }

        return valido;
    }

}
