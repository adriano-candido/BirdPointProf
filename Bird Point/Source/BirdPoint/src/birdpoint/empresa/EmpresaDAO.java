/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.empresa;

import birdpoint.util.GenericDAO;
import javax.swing.JOptionPane;

/**
 *
 * @author Karlos
 */
public class EmpresaDAO extends GenericDAO<Empresa>{
    
    public EmpresaDAO() {
        super(Empresa.class);
    }

    public void salvar(Empresa empresa) {
        Object[] options = {"Sim", "Não"};
        if (empresa.getIdEmpresa() == 0) {
            if (adicionar(empresa)) {
                JOptionPane.showMessageDialog(null, "Empresa cadastrada com sucesso!");
            }
        } else if (JOptionPane.showOptionDialog(null, "Deseja mesmo realizar essa edição"
                + "?", "BirdPoint", JOptionPane.YES_NO_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION) {
            if (atualizar(empresa)) {
                JOptionPane.showMessageDialog(null, "Empresa editada com sucesso!!");
            }
        } else {
            JOptionPane.showMessageDialog(null, "A edição foi cancelada!");
        }
    }
    
}
