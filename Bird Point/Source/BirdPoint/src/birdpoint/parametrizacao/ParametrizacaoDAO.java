/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.parametrizacao;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

public class ParametrizacaoDAO extends GenericDAO<Parametrizacao> {

    public ParametrizacaoDAO() {
        super(Parametrizacao.class);
    }

    public void salvar(Parametrizacao parametros) {
        Object[] options = {"Sim", "Não"};
        if (parametros.getIdParametrizacao() == 0) {
            if (adicionar(parametros)) {
                JOptionPane.showMessageDialog(null, "Parâmetros cadastrados com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(parametros)) {
                JOptionPane.showMessageDialog(null, "Parâmetros editados com sucesso!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }

}
