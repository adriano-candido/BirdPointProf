/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.horariosemanal;

import birdpoint.setor.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karlos
 */
public class HorarioTableModel extends AbstractTableModel {

    private List<Horario> horario = new ArrayList<>();
    private String[] colunas = {"Código", "Funcionário"};

    public HorarioTableModel(List<Horario> horario) {
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
        Horario horarios = horario.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return horarios.getIdHorario();
            case 1:
                return horarios.getFuncionario().getNomeFuncionario();

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
        }
        return null;
    }

}
