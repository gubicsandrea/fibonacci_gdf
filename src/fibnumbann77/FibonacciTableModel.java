/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fibnumbann77;

import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author user
 */
public class FibonacciTableModel extends AbstractTableModel {
    
    private final String[] columnNames = {"Sorszám", "Eredmény"};
    
    private final List<FibPair> data;

    public FibonacciTableModel(List<FibPair> data) {
        this.data = data;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        if(rowIndex >= data.size()){
            throw new IllegalArgumentException("Invalid row number");
        }
        switch(columnIndex){
            case 0:
                return data.get(rowIndex).getN();
            case 1:
                return data.get(rowIndex).getValue();
            default:
                throw new IllegalArgumentException("Invalid column index");
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }    
    
}
