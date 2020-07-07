/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import Enums.EnumComparadorQuery;

/**
 *
 * @author BRUNOSILVA
 */
public class CamposDeClasse {

    //ArrayList<String> listaCamposParaSelecionar;
    private String nomeChave;
    private String valorChave;
    private EnumComparadorQuery comparador;
    private boolean operadorLogicoE;

    public String getNomeChave() {
        return nomeChave;
    }

    public void setNomeChave(String nomeChave) {
        this.nomeChave = nomeChave;
    }

    public String getValorChave() {
        return valorChave;
    }

    public void setValorChave(String valorChave) {
        this.valorChave = valorChave;
    }

    public EnumComparadorQuery getComparador() {
        return comparador;
    }

    public void setComparador(EnumComparadorQuery comparador) {
        this.comparador = comparador;
    }

    public boolean isOperadorLogicoE() {
        return operadorLogicoE;
    }

    public void setOperadorLogicoE(boolean operadorLogicoE) {
        this.operadorLogicoE = operadorLogicoE;
    }

    public void preparaChave() {
        if (getComparador() == EnumComparadorQuery.LIKE
                || getComparador() == EnumComparadorQuery.NOT_LIKE) {
            String aux = " '%" + valorChave + "%' ";
            setValorChave(aux);
        } else {
            if (!getNomeChave().equals("id")) {
                setValorChave("'" + getValorChave() + "'");
            }
        }
    }

}
