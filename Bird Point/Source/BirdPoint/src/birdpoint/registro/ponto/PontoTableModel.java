/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.registro.ponto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class PontoTableModel extends AbstractTableModel {

    private List<Ponto> pontos = new ArrayList<>();
    private String[] colunas = {"Código", "Funcionario", "Data", "Entrada", "Saída", "Justificado", "Atestado"};
    SimpleDateFormat formatarHora = new SimpleDateFormat("HH:mm:ss");

    public PontoTableModel(List<Ponto> ponto) {
        this.pontos = ponto;
    }

    @Override
    public int getRowCount() {
        return pontos.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Ponto ponto = pontos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ponto.getIdPonto();
            case 1:
                return ponto.getFuncionario().getNomeFuncionario();
            case 2:
                return ponto.getDataPonto();
            case 3:
                return ponto.getHoraEntradaPonto();
            case 4:
                return ponto.getHoraSaidaPonto();
            case 5:
                if (ponto.getJustificativaPonto() == null) {
                    return "Não";
                } else {
                    return "Sim";
                }
            case 6:
                if (ponto.isAtestadoPonto()) {
                    return "Sim";
                } else {
                    return "Não";
                }

        }
        return null;
    }

    public String getColumnName(int index) {
        switch (index) {
            case 0:
                return colunas[0];
            case 1:
                return colunas[1];
            case 2:
                return colunas[2];
            case 3:
                return colunas[3];
            case 4:
                return colunas[4];
            case 5:
                return colunas[5];
            case 6:
                return colunas[6];

        }
        return null;
    }

}
