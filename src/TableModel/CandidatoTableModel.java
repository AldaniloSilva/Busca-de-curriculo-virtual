/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TableModel;

import business.Candidato;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author BRUNOSILVA
 */
public class CandidatoTableModel extends AbstractTableModel {

    ArrayList<Candidato> listaCandidatos;

    String[] colunas = {"Nome", "Idade", "Cidade",
        "Objetivo", "Telefone", "Escolaridade","Email"};

    @Override
    public String getColumnName(int column) {
        return colunas[column];
    }

    @Override
    public int getRowCount() {
        return listaCandidatos.size();

    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int linha, int coluna) {
        switch (coluna) {
            case 0:
                return listaCandidatos.get(linha).getNome();
            case 1:
                return listaCandidatos.get(linha).retornaIdade();
            case 2:
                return listaCandidatos.get(linha).getCidade().toString();
            case 3:
                return listaCandidatos.get(linha).getCargo();
            case 4:
                return listaCandidatos.get(linha).getTelefone();
            case 5:
                return listaCandidatos.get(linha).getEscolaridade();
            case 6:
                return listaCandidatos.get(linha).getEmail();
            default:
                return listaCandidatos.get(linha);
        }
        
    }
    
    public void setLista(ArrayList<Candidato> listaRef){
        this.listaCandidatos=listaRef;
        this.fireTableDataChanged();        
    }
    
    

}
