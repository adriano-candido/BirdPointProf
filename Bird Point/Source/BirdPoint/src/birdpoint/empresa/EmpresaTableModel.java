/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package birdpoint.empresa;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Karlos
 */
public class EmpresaTableModel extends AbstractTableModel {

    private List<Empresa> empresas = new ArrayList<>();
    private String[] colunas = {"Código", "Nome", "Nome Fantasia", "CNPJ", "Cidade", "Telefone", "Email"};

    public EmpresaTableModel(List<Empresa> empresa) {
        this.empresas = empresa;
    }

    @Override
    public int getRowCount() {
        return empresas.size();
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empresa empresa = empresas.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return empresa.getIdEmpresa();
            case 1:
                return empresa.getNomeEmpresa();
            case 2:
                return empresa.getNomeFantasiaEmpresa();
            case 3:
                return empresa.getCnpjEmpresa();
            case 4:
                return empresa.getCidadeEnderecoEmpresa();
            case 5:
                return empresa.getTelefoneEmpresa();
            case 6:
                return empresa.getEmailEmpresa();
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
            case 3:
                return colunas[3];
            case 4:
                return colunas[4];
            case 5:
                return colunas[5];
            case 6:
                return colunas[5];

        }
        return null;
    }
}
