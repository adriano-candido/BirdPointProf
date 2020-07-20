/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.parametrizacao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author adriano
 */
@Entity
public class Parametrizacao {

    @Id
    @GeneratedValue
    private int idParametrizacao;

    private int tempoToleranciaEntrada;
    private int tempoToleranciaSaida;
    private boolean gerarHorarioAutomatico;
    private boolean ignorarTolerancia;

    public boolean isIgnorarTolerancia() {
        return ignorarTolerancia;
    }

    public void setIgnorarTolerancia(boolean ignorarTolerancia) {
        this.ignorarTolerancia = ignorarTolerancia;
    }

    public int getTempoToleranciaEntrada() {
        return tempoToleranciaEntrada;
    }

    public void setTempoToleranciaEntrada(int tempoToleranciaEntrada) {
        this.tempoToleranciaEntrada = tempoToleranciaEntrada;
    }

    public int getTempoToleranciaSaida() {
        return tempoToleranciaSaida;
    }

    public void setTempoToleranciaSaida(int tempoToleranciaSaida) {
        this.tempoToleranciaSaida = tempoToleranciaSaida;
    }

    public boolean isGerarHorarioAutomatico() {
        return gerarHorarioAutomatico;
    }

    public void setGerarHorarioAutomatico(boolean gerarHorarioAutomatico) {
        this.gerarHorarioAutomatico = gerarHorarioAutomatico;
    }

    public int getIdParametrizacao() {
        return idParametrizacao;
    }

    public void setIdParametrizacao(int idParametrizacao) {
        this.idParametrizacao = idParametrizacao;
    }

}
