package app.model;

import javax.swing.table.AbstractTableModel;

public class TableDataModel extends AbstractTableModel {

    private static final int SIZE = 5;
    private final Double[][] data;

    public TableDataModel() {
        data = new Double[SIZE][SIZE];
    }

    @Override
    public int getRowCount() {
        return SIZE;
    }

    @Override
    public int getColumnCount() {
        return SIZE;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Double value = data[rowIndex][columnIndex];
        return value == null ? "" : value;
    }

    public double getNumericValueAt(int rowIndex, int columnIndex) {
        Double value = data[rowIndex][columnIndex];
        return value == null ? 0 : value;
    }

    @Override
    public String getColumnName(int column) {
        return String.valueOf(column);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    public void setNumericValueAt(double value, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = value;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    public void clear() {
        for (int row = 0; row < SIZE; row++) {
            for (int column = 0; column < SIZE; column++) {
                data[row][column] = null;
            }
        }

        fireTableDataChanged();
    }

    public int getElementsCount() {
        return SIZE * SIZE;
    }
}