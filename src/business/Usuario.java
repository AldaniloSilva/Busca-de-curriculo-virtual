/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import Enums.EnumTipoAcesso;

/**
 *
 * @author BRUNOSILVA
 */
public class Usuario extends Entidade {
    
    private String nome;
    private String senha;
    private EnumTipoAcesso tipoAcesso;
    
    public Usuario(){
        
    }   


    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public EnumTipoAcesso getTipoAcesso() {
        return tipoAcesso;
    }

    public void setTipoAcesso(EnumTipoAcesso tipoAcesso) {
        this.tipoAcesso = tipoAcesso;
    }
    
    
    
    
      
    
    
}
