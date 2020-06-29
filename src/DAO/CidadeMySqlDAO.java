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
            //int idEstado = rs.getInt("estado");          

            cid.setUf(rs.getString("uf"));

            return cid;
        } catch (SQLException ex) {
            ServicoDeMensagens.mensagem = ex.getMessage();
            //Logger.getLogger(UsuarioMySqlDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cid;
    }

}

/*
 CamposDeClasse chaves=new CamposDeClasse();
            ArrayList<CamposDeClasse> listaChaves=new ArrayList<>();
            chaves.setIgual(true);
            chaves.setNomeChave("estado");
            chaves.setValorChave(Integer.toString(idEstado));
            listaChaves.add(chaves);
                    
            ArrayList<String> listaCampos=new ArrayList<>();
            listaCampos.add("estado.uf");            
            
            String comandoSql = stringSelectCamposComFiltro(listaCampos, stringSelectTabelaComFiltro(listaChaves));

 */
 /*
@Override
    protected PreparedStatement adicionaParametros(Cidade entidade,
            PreparedStatement params,
            EnumOperacao operacao) throws SQLException{
        
        
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
**/
