/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Acesso;

import business.Candidato;
import business.Usuario;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author BRUNOSILVA
 */
public class ListagemCandidatosSing {
     //---------Inicio do Padrão Singleton---------------
   

    //instancia que se auto cria no Singleton
    private static ListagemCandidatosSing _instancia;   

    //Lista de candidadtos
    private ArrayList<Candidato> listagemDeCandidatos;

    //construtor privado para instanciar a fila
    private ListagemCandidatosSing() {
        listagemDeCandidatos = new ArrayList<>();
    }

    //método estático que 'auto cria' a instancia do Singleton
    public static ListagemCandidatosSing getInstancia() {
        if (_instancia == null) {
            _instancia = new ListagemCandidatosSing();
        }

        return _instancia;
    }
    //--------------Fim do Padrão Singleton--------------------
    
     //Adiciona uma lista candidato
    public void adicionaCandidato(ArrayList<Candidato> candidatos) {
        this.listagemDeCandidatos=candidatos;
    }
    
    public int tamanhoLista(){        
        return listagemDeCandidatos.size();
    }
    
    public Candidato retornaCandidato(int indice){
        
        return listagemDeCandidatos.get(indice);
    }

    
    

}

