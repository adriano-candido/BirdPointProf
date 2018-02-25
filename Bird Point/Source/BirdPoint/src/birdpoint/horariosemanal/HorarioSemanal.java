/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.horariosemanal;

import java.sql.Time;

/**
 *
 * @author Adriano
 */
public class HorarioSemanal {
    
    private String nomeDiaSemana;
    
    private String turno;
    
    private Time horaEntrada;
    
    private Time horaSaida;

    /**
     * @return the nomeDiaSemana
     */
    public String getNomeDiaSemana() {
        return nomeDiaSemana;
    }

    /**
     * @param nomeDiaSemana the nomeDiaSemana to set
     */
    public void setNomeDiaSemana(String nomeDiaSemana) {
        this.nomeDiaSemana = nomeDiaSemana;
    }

    /**
     * @return the turno
     */
    public String getTurno() {
        return turno;
    }

    /**
     * @param turno the turno to set
     */
    public void setTurno(String turno) {
        this.turno = turno;
    }

    /**
     * @return the horaEntrada
     */
    public Time getHoraEntrada() {
        return horaEntrada;
    }

    /**
     * @param horaEntrada the horaEntrada to set
     */
    public void setHoraEntrada(Time horaEntrada) {
        this.horaEntrada = horaEntrada;
    }

    /**
     * @return the horaSaida
     */
    public Time getHoraSaida() {
        return horaSaida;
    }

    /**
     * @param horaSaida the horaSaida to set
     */
    public void setHoraSaida(Time horaSaida) {
        this.horaSaida = horaSaida;
    }
    
    
    
            
    
}
