/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.horariosemanal;

import birdpoint.funcionario.Funcionario;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Karlos
 */
@Entity
public class Horario {
    
    @Id
    @GeneratedValue
    private int idHorario;
    
    @Column(length = 5000)
    private String listaHorario;
    
    @OneToOne
    private Funcionario funcionario;

    /**
     * @return the idHorario
     */
    public int getIdHorario() {
        return idHorario;
    }

    /**
     * @param idHorario the idHorario to set
     */
    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    /**
     * @return the funcionario
     */
    public Funcionario getFuncionario() {
        return funcionario;
    }

    /**
     * @param funcionario the funcionario to set
     */
    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    /**
     * @return the listaHorario
     */
    public String getListaHorario() {
        return listaHorario;
    }

    /**
     * @param listaHorario the listaHorario to set
     */
    public void setListaHorario(String listaHorario) {
        this.listaHorario = listaHorario;
    }
    
    



}
