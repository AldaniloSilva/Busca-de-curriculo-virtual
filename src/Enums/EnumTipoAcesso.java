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
public enum EnumTipoAcesso {
    
    ADM("Administrador"),

    PADRAO("Padrão");
 
    private String descricao;
 
    EnumTipoAcesso(String descricao) {
        this.descricao = descricao;
    }
 
    public String getDescricao() {
        return descricao;
    }
    
}
