/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.horariosemanal;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karlos
 */
public class HorarioSemanalTableModel extends AbstractTableModel {

    private List<HorarioSemanal> horario = new ArrayList<>();
    private String[] colunas = {"Dia", "Horário Entrada", "Horário Saída"};

    public HorarioSemanalTableModel(List<HorarioSemanal> horario) {
        this.horario = horario;
    }

    @Override
    public int getRowCount() {
        return horario.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        HorarioSemanal horarios = horario.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return horarios.getNomeDiaSemana();
            case 1:
                return horarios.getHoraEntrada();
            case 2:
                return horarios.getHoraSaida();

        }
        return null;
    }

    @Override
    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return colunas[0];
            case 1:
                return colunas[1];
            case 2:
                return colunas[2];
        }
        return null;
    }

}
