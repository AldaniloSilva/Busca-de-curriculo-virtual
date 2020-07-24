/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Enums.EnumOperacao;
import business.CamposDeClasse;
import business.Entidade;
import java.io.IOException;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import repositorio.IRepositorio;
import servicos.ServicoDeMensagens;

/**
 *
 * @author BRUNOSILVA
 */
public abstract class MySqlDAO<T extends Entidade> extends PadraoDAO<T> implements IRepositorio<T> {

    /**
     *
     * @param entidade
     * @param params
     * @param operacao
     * @return método adiciona parametros a um PreparedStatement e o retorna
     * preenchido
     * @throws SQLException
     */
    protected PreparedStatement adicionaParametrosNoBanco(T entidade,
            PreparedStatement params,
            EnumOperacao operacao) throws SQLException {

        throw new UnsupportedOperationException("Não implementado nessa classe, só na filha se"
                + "houver necessidade.");
    }

    protected abstract T montaEntidade(ResultSet rs);

    public MySqlDAO(Class<T> entidade) {
        super(entidade);
    }

    public MySqlDAO() {

    }

    protected String montaSelectSimplesPorId() {
        return "select * from " + getTabela() + " where id=?";
    }

    protected String montaSelectConsulta() {
        return "select * from " + getTabela();
    }

    /**
     *
     * @param camposParaFiltrar
     * @return //retorna uma string para select total de uma tabela com filtros
     */
    public String stringSelectTabelaComFiltro(ArrayList<CamposDeClasse> camposParaFiltrar) {
        String comandoSelect = "";
        comandoSelect += montaSelectConsulta();

        for (int n = 0; n < camposParaFiltrar.size(); n++) {
            CamposDeClasse cc = camposParaFiltrar.get(n);
            String operador = "";
            String opLogico = "";
            operador = cc.getComparador().getDescricao();

            if (cc.isOperadorLogicoE()) {
                opLogico = " and ";
            } else {
                opLogico = " or ";
            }

            cc.preparaChave();
            if (n == 0) {
                comandoSelect += " where " + cc.getNomeChave() + operador + cc.getValorChave();

            } else {
                comandoSelect += opLogico + cc.getNomeChave() + operador + cc.getValorChave();
            }
        }

        return comandoSelect;
    }

    /**
     * pega a string de select total de uma tabela, e substitui o asterisco (*)
     * pelos campos que se deseja selecionar
     *
     * @param camposDaTabela
     * @param retornoStringSelectTabelaComFiltro
     * @return
     */
    public String stringSelectCamposComFiltro(ArrayList<String> camposDaTabela,
            String retornoStringSelectTabelaComFiltro) {

        String comandoSelect = "";
        comandoSelect = retornoStringSelectTabelaComFiltro;
        String campos = "";

        for (int n = 0; n < camposDaTabela.size(); n++) {
            if (n != camposDaTabela.size() - 1) {
                campos += camposDaTabela.get(n) + ", ";

            } else {
                campos += camposDaTabela.get(n);
            }
        }

        String replaceCampos = comandoSelect.replace("*", campos);

        return replaceCampos;
    }

    public ArrayList<CamposDeClasse> listaDeCamposPadrao(T entidade) {
        Class<?> classeEntidade = entidade.getClass();
        ArrayList<CamposDeClasse> listaCampos = new ArrayList<>();

        for (Field atributo : classeEntidade.getDeclaredFields()) {
            if (!atributo.getName().toLowerCase().equals("id")) {
                CamposDeClasse campos = new CamposDeClasse();
                campos.setNomeChave(atributo.getName());
                listaCampos.add(campos);

            }
        }
        return listaCampos;

    }

    //método para padronizar string sql dos CRUDS independente da classe
    public String montaStringSql(EnumOperacao operacao, ArrayList<CamposDeClasse> listaDeCampos) throws IllegalArgumentException,
             IllegalAccessException {
        ArrayList<CamposDeClasse> listaCampos = listaDeCampos;

        String campos = "";
        String valores = "";

        String enviaComando = "";
        switch (operacao) {
            case INSERIR:
                for (int n = 0; n < listaCampos.size(); n++) {
                    String aux = "";

                    if (n != listaCampos.size() - 1) {
                        campos += listaCampos.get(n).getNomeChave() + ",";
                        aux = "?" + ",";

                    } else {
                        campos += listaCampos.get(n).getNomeChave();
                        aux = "?";
                    }
                    //verifica se é o campo senha para encriptar os dados no banco.
                    //Encriptação do tipo AES
                    if (listaCampos.get(n).getNomeChave().toLowerCase().equals("senha")) {
                        String crip = "";
                        crip = aux.replace("?", " aes_encrypt(?,'rh') ");
                        aux = crip;
                    }
                    valores += aux;
                }
                enviaComando += "insert into " + getTabela() + " "
                        + "(" + campos + ")" + " values " + "(" + valores + ")";
                break;
            case ALTERAR:
                for (int n = 0; n < listaCampos.size(); n++) {
                    String aux = "";
                    if (n != listaCampos.size() - 1) {
                        aux = listaCampos.get(n).getNomeChave() + " = ?, ";

                    } else {
                        aux = listaCampos.get(n).getNomeChave() + " = ?";
                    }
                    //verifica se é o campo senha para encriptar os dados no banco.
                    //Encriptação do tipo AES
                    if (listaCampos.get(n).getNomeChave().toLowerCase().equals("senha")) {
                        String crip = "";
                        crip = aux.replace("?", " aes_encrypt(?,'rh') ");
                        aux = crip;
                    }
                    campos += aux;
                }

                enviaComando += "update " + getTabela() + " set "
                        + campos + " where id = ?";
                break;

            case DELETAR:
                enviaComando += "delete from " + getTabela()
                        + " where id = ?";
                break;
            default:
                enviaComando = stringSelectTabelaComFiltro(listaCampos);
                break;
        }
        return enviaComando;
    }

//
    protected void executaSql(T entidade, EnumOperacao operacao) throws SQLException, IOException {
        try (Connection conect = MySqlConexao.geraConexao()) {
            String comandoSql = montaStringSql(operacao, listaDeCamposPadrao(entidade));
            try (PreparedStatement params
                    = adicionaParametrosNoBanco(entidade, conect.prepareStatement(comandoSql), operacao)) {
                params.execute();
            }
            if (!conect.isClosed()) {
                conect.close();
            }
        } catch (IllegalArgumentException ex) {
            ServicoDeMensagens.mensagem = "Argumento não aceito na consulta!";
            //Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            ServicoDeMensagens.mensagem = "Consulta inválida no banco!";
            //Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void inserirEntidade(T entidade) {
        try {
            EnumOperacao op = EnumOperacao.INSERIR;

            //String sql = montaStringSql(op);
            executaSql(entidade, op);

            ServicoDeMensagens.mensagem = getTabela() + " id: " + entidade.getId() + " inserido com sucesso!!";
        } catch (SQLException ex) {
            ServicoDeMensagens.mensagem = "Ocorreu um erro ao inserir! ";
        } catch (IOException ex) {
            ServicoDeMensagens.mensagem = "Arquivo inválido no insert! ";
            //Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void alterarEntidade(int id, T entidade) {
        try {
            EnumOperacao op = EnumOperacao.ALTERAR;

            //String sql = montaStringSql(op);
            executaSql(entidade, op);

            ServicoDeMensagens.mensagem = getTabela() + " id: " + entidade.getId() + " alterado com sucesso!!";
        } catch (SQLException ex) {
            ServicoDeMensagens.mensagem = "Ocorreu um erro ao alterar! ";
            //Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            ServicoDeMensagens.mensagem = "Arquivo inválido no update! ";

        }

    }

    @Override
    public void deletarEntidade(int id, T entidade) {
        try {
            EnumOperacao op = EnumOperacao.DELETAR;
            
            executaSql(entidade, op);

            ServicoDeMensagens.mensagem = getTabela() + " id: " + entidade.getId() + " deletado com sucesso!!";
        } catch (SQLException ex) {
            ServicoDeMensagens.mensagem = "Ocorreu um erro ao alterar! ";            
        } catch (IOException ex) {
            ServicoDeMensagens.mensagem = "Arquivo inválido na exclusão! ";
        }

    }

    //Faz listagem de dados usando o select enviado
    public ArrayList<T> listagemDeEntidade(String strParaSelect) throws SQLException {
        ArrayList<T> listaEntidades = new ArrayList<>();
        String comandoSql = strParaSelect;
        try (Connection conect = MySqlConexao.geraConexao()) {
            try (PreparedStatement params = conect.prepareStatement(comandoSql)) {
                try (ResultSet dados = params.executeQuery()) {
                    while (dados.next()) {
                        T entidade = null;
                        entidade = montaEntidade(dados);
                        listaEntidades.add(entidade);
                    }
                }
            }
            if (!conect.isClosed()) {
                conect.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return listaEntidades;
    }

    @Override
    public T consultaPorId(int id) throws SQLException {
        T entidade = null;
        try (Connection conect = MySqlConexao.geraConexao()) {
            String comandoSql = montaSelectSimplesPorId();
            try (PreparedStatement params = conect.prepareStatement(comandoSql)) {
                params.setInt(1, id);
                try (ResultSet dados = params.executeQuery()) {
                    if (dados.first()) {
                        entidade = montaEntidade(dados);
                    }
                }
            }
            if (!conect.isClosed()) {
                conect.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidade;
    }

    //Faz uma consulta com um comando select qualquer
    public T consultaGenerica(String comandoSql) throws SQLException {
        T entidade = null;
        try (Connection conect = MySqlConexao.geraConexao()) {
            try (PreparedStatement params = conect.prepareStatement(comandoSql)) {
                try (ResultSet dados = params.executeQuery()) {
                    if (dados.first()) {
                        entidade = montaEntidade(dados);
                    }
                }
            }
            if (!conect.isClosed()) {
                conect.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return entidade;
    }

    public int proximoId() throws SQLException {
        int proximo = 0;
        String comandoSql = "select max(id)+1 as proximo from " + getTabela();
        try (Connection conect = MySqlConexao.geraConexao()) {
            try (PreparedStatement params = conect.prepareStatement(comandoSql)) {
                try (ResultSet dados = params.executeQuery()) {
                    if (dados.first()) {
                        proximo = dados.getInt(1);
                    }
                }
            }
            if (!conect.isClosed()) {
                conect.close();
            }
        } catch (IOException ex) {
            Logger.getLogger(MySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return proximo;
    }

}
