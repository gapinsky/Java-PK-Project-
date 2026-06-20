package app.model;

import javax.swing.table.AbstractTableModel;

/**
 * Model danych tabeli JTable zgodny ze wzorcem MVC.
 * Klasa przechowuje dane liczbowe tabeli 5x5.
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class TableDataModel extends AbstractTableModel {

    private static final int ROW_COUNT = 5;
    private static final int COLUMN_COUNT = 5;

    private final Double[][] data;

    public TableDataModel() {
        data = new Double[ROW_COUNT][COLUMN_COUNT];
        clear();
    }

    @Override
    public int getRowCount() {
        return ROW_COUNT;
    }

    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    @Override
    public String getColumnName(int column) {
        return String.valueOf(column);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {

        if (value instanceof Number) {
            data[rowIndex][columnIndex] =
                    ((Number) value).doubleValue();
        } else {
            data[rowIndex][columnIndex] =
                    Double.parseDouble(value.toString());
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    public void setNumericValueAt(double value, int row, int column) {
        data[row][column] = value;
        fireTableCellUpdated(row, column);
    }

    public double getNumericValueAt(int row, int column) {
        return data[row][column];
    }

    /**
     * Zeruje całą tabelę.
     */
    public void clear() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {
                data[row][column] = 0.0;
            }
        }

        fireTableDataChanged();
    }

    public int getElementsCount() {
        return ROW_COUNT * COLUMN_COUNT;
    }
}