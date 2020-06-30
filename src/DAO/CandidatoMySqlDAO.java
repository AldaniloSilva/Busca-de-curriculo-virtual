/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Enums.EnumComparadorQuery;
import Enums.EnumOperacao;
import business.CamposDeClasse;
import business.Candidato;
import business.Cidade;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author BRUNOSILVA
 */
public class CandidatoMySqlDAO extends MySqlDAO<Candidato> {

    public CandidatoMySqlDAO(Class<Candidato> entidade) {
        super(entidade);
        setTabela("candidato");
    }

    public CandidatoMySqlDAO() {
        setTabela("candidato");
    }

    @Override
    protected String montaSelectConsulta() {
        return "select candidato.* from candidato"
                + " inner join cidade on candidato.cidade=cidade.id";
    }

    @Override
    protected PreparedStatement adicionaParametrosNoBanco(Candidato entidade,
            PreparedStatement params,
            EnumOperacao operacao) throws SQLException {

        switch (operacao) {
            case INSERIR:

                params.setString(1, entidade.getNome());
                params.setInt(2, entidade.getNascimento());
                params.setInt(3, entidade.getCidade().getId());
                params.setString(4, entidade.getCargo());
                params.setString(5, entidade.getTelefone());
                params.setString(6, entidade.getEscolaridade());
                params.setString(7, entidade.getEmail());
                params.setString(8, entidade.getNome_arquivo());

                break;

            case ALTERAR:

                params.setString(1, entidade.getNome());
                params.setInt(2, entidade.getNascimento());
                params.setInt(3, entidade.getCidade().getId());
                params.setString(4, entidade.getCargo());
                params.setString(5, entidade.getTelefone());
                params.setString(6, entidade.getEscolaridade());
                params.setString(7, entidade.getEmail());
                params.setString(8, entidade.getNome_arquivo());
                params.setInt(9, entidade.getId());
                break;

            case DELETAR:

                params.setInt(1, entidade.getId());
        }
        return params;

    }

    @Override
    protected Candidato montaEntidade(ResultSet rs) {
        Candidato can = new Candidato();
        try {
            can.setId(rs.getInt("id"));
            can.setNome(rs.getString("nome"));
            can.setNascimento(rs.getInt("nascimento"));

            //int idCidade=rs.getInt("cidade");
            String idCidade = rs.getString("cidade").trim();
            CidadeMySqlDAO dao = new CidadeMySqlDAO();
            String comando = dao.strSqlParaCidades("id", idCidade);
            Cidade cid = dao.consultaGenerica(comando);
            //Cidade cid=dao.consultaPorId(idCidade);
            //can.setCidade_Id(rs.getInt("cidade"));
            can.setCidade(cid);

            can.setCargo(rs.getString("cargo"));
            can.setTelefone(rs.getString("telefone"));
            can.setEscolaridade(rs.getString("escolaridade"));
            can.setEmail(rs.getString("email"));
            can.setNome_arquivo(rs.getString("nome_arquivo"));

        } catch (SQLException ex) {
            Logger.getLogger(CandidatoMySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return can;
    }
    
    public boolean validaSeRepete(Candidato can) throws SQLException{
        boolean existe=false;
        ArrayList<CamposDeClasse> camposFiltro=new ArrayList<>();
        CamposDeClasse campo=new CamposDeClasse();
        campo.setNomeChave("email");
        campo.setValorChave(can.getEmail());
        campo.setComparador(EnumComparadorQuery.IGUAL);
        campo.setOperadorLogicoE(false);
        camposFiltro.add(campo);
        String comando=stringSelectTabelaComFiltro(camposFiltro);
        Candidato candidatoConsultado = consultaGenerica(comando);
        if(candidatoConsultado!=null){
            existe=true;
        }        
        
        return existe; 
    }

}

/*
     @Override
    protected String montaStringSql(EnumOperacao operacao) {
        
        
        
        String enviaComando = "";
        switch (operacao) {
            case INSERIR:
                enviaComando += "insert into " + getTabela()
                        + " (nome,nascimento,cidade,cargo"
                        + ",telefone,escolaridade,email,nome_arquivo) "
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
 /*

 @Override
    protected PreparedStatement adicionaParametros(Candidato entidade
            , PreparedStatement params
            , EnumOperacao operacao) throws SQLException {
        
        DateFormat formataData = new SimpleDateFormat("yyyy-MM-dd"); 
        
        switch (operacao) {
            case INSERIR:
                
                 enviaComando += "insert into " + getTabela()
                        + " (nome,senha,tipoAcesso) "
                        + "values (?,?,?)";                
                
                params.setString(1, entidade.getNome());
                params.setString(2, formataData.format(entidade.getNascimento()));
                params.setString(3, );
                break;
                
            case ALTERAR:
                
                enviaComando += "update " + getTabela()
                        + " set nome = ?, senha=?, tipoAcesso=?"
                        + " where id = ?";
                
                params.setString(1, entidade.getNome());
                params.setString(2, entidade.getSenha());
                params.setString(3, entidade.getTipoAcesso().getDescricao());
                params.setInt(4, entidade.getId()); 
                break;

            case DELETAR:
                
                enviaComando += "delete from " + getTabela()
                        + " where id = ?";
                
                params.setInt(1, entidade.getId());                 
        }
        return params;
         
    }
 */
