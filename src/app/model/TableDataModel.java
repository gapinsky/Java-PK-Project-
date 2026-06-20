package app.model;

import javax.swing.table.AbstractTableModel;

/**
 * Model danych tabeli JTable zgodny ze wzorcem MVC.
 * <p>
 * Klasa przechowuje dane liczbowe tabeli 5x5 oraz udostępnia je widokowi JTable.
 * Puste komórki są traktowane jako wartość 0 podczas obliczeń.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class TableDataModel extends AbstractTableModel {

    /**
     * Liczba wierszy tabeli.
     */
    private static final int ROW_COUNT = 5;

    /**
     * Liczba kolumn tabeli.
     */
    private static final int COLUMN_COUNT = 5;

    /**
     * Dwuwymiarowa tablica przechowująca wartości liczbowe.
     */
    private final Double[][] data;

    /**
     * Konstruktor modelu tabeli.
     * <p>
     * Tworzy pustą tabelę o rozmiarze 5x5.
     * </p>
     */
    public TableDataModel() {
        data = new Double[ROW_COUNT][COLUMN_COUNT];
    }

    /**
     * Zwraca liczbę wierszy tabeli.
     *
     * @return liczba wierszy
     */
    @Override
    public int getRowCount() {
        return ROW_COUNT;
    }

    /**
     * Zwraca liczbę kolumn tabeli.
     *
     * @return liczba kolumn
     */
    @Override
    public int getColumnCount() {
        return COLUMN_COUNT;
    }

    /**
     * Zwraca nazwę kolumny wyświetlaną w nagłówku tabeli.
     *
     * @param column indeks kolumny
     * @return nazwa kolumny
     */
    @Override
    public String getColumnName(int column) {
        return String.valueOf(column);
    }

    /**
     * Zwraca wartość znajdującą się w podanej komórce.
     *
     * @param rowIndex    indeks wiersza
     * @param columnIndex indeks kolumny
     * @return wartość komórki
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    /**
     * Ustawia wartość w podanej komórce tabeli.
     *
     * @param value       nowa wartość komórki
     * @param rowIndex    indeks wiersza
     * @param columnIndex indeks kolumny
     */
    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        if (value instanceof Double) {
            data[rowIndex][columnIndex] = (Double) value;
        } else if (value instanceof Number) {
            data[rowIndex][columnIndex] = ((Number) value).doubleValue();
        } else if (value == null || value.toString().isEmpty()) {
            data[rowIndex][columnIndex] = null;
        } else {
            data[rowIndex][columnIndex] = Double.parseDouble(value.toString());
        }

        fireTableCellUpdated(rowIndex, columnIndex);
    }

    /**
     * Określa, czy dana komórka tabeli może być edytowana bezpośrednio przez użytkownika.
     *
     * @param rowIndex    indeks wiersza
     * @param columnIndex indeks kolumny
     * @return true, ponieważ komórki tabeli są edytowalne
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    /**
     * Zwraca klasę danych przechowywanych w kolumnie.
     *
     * @param columnIndex indeks kolumny
     * @return klasa Double
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Double.class;
    }

    /**
     * Ustawia wartość liczbową w konkretnej komórce tabeli.
     *
     * @param value  wartość liczbowa
     * @param row    indeks wiersza
     * @param column indeks kolumny
     */
    public void setNumericValueAt(double value, int row, int column) {
        data[row][column] = value;
        fireTableCellUpdated(row, column);
    }

    /**
     * Zwraca wartość liczbową z konkretnej komórki.
     * <p>
     * Jeżeli komórka jest pusta, metoda zwraca 0.
     * </p>
     *
     * @param row    indeks wiersza
     * @param column indeks kolumny
     * @return wartość komórki albo 0 dla pustej komórki
     */
    public double getNumericValueAt(int row, int column) {
        Double value = data[row][column];

        if (value == null) {
            return 0;
        }

        return value;
    }

    /**
     * Czyści tabelę, ustawiając wszystkie komórki jako puste.
     */
    public void clear() {
        for (int row = 0; row < ROW_COUNT; row++) {
            for (int column = 0; column < COLUMN_COUNT; column++) {
                data[row][column] = null;
            }
        }

        fireTableDataChanged();
    }

    /**
     * Zwraca całkowitą liczbę elementów tabeli.
     *
     * @return liczba komórek w tabeli
     */
    public int getElementsCount() {
        return ROW_COUNT * COLUMN_COUNT;
    }
}