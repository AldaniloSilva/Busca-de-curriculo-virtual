/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

/**
 *
 * @author BRUNOSILVA
 */
public enum EnumOperacao {
    
    INSERIR("I"),
    ALTERAR("A"),
    DELETAR("D");
 
    private String descricao;
 
    EnumOperacao(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
}
