/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acesso;

import business.Usuario;

/**
 *
 * @author BRUNOSILVA
 */
public class UsuarioLogado {
    //---------Inicio do Padrão Singleton---------------    

    //instancia que se auto cria no Singleton
    private static UsuarioLogado _instancia;

    //Usuario logado no Sistema
    private Usuario usuario;

    //construtor privado 
    private UsuarioLogado() {
        this.usuario = new Usuario();
    }

    //método estático que 'auto cria' a instancia do Singleton
    public static UsuarioLogado getInstancia() {
        if (_instancia == null) {
            _instancia = new UsuarioLogado();
        }

        return _instancia;
    }
    //--------------Fim do Padrão Singleton-------------------- 

    public void adicionaUsuario(Usuario usuarioPassado){
        usuario.setId(usuarioPassado.getId());
        usuario.setNome(usuarioPassado.getNome());
        usuario.setSenha(usuarioPassado.getSenha());
        usuario.setTipoAcesso(usuarioPassado.getTipoAcesso());
    }
    
    public Usuario retornaDadosUsuario(){        
        return usuario;
    }
   
}

