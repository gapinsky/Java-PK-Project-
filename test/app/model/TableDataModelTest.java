package app.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testy jednostkowe klasy TableDataModel.
 * <p>
 * Sprawdzają podstawowe operacje na modelu danych tabeli,
 * takie jak ustawianie wartości, czyszczenie tabeli oraz obsługa pustych komórek.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 4.0
 * @since 2026-06-20
 */
public class TableDataModelTest {

    /**
     * Sprawdza, czy nowo utworzony model ma rozmiar 5x5.
     */
    @Test
    public void shouldCreateTableWithFiveRowsAndFiveColumns() {
        TableDataModel model = new TableDataModel();

        assertEquals(5, model.getRowCount());
        assertEquals(5, model.getColumnCount());
        assertEquals(25, model.getElementsCount());
    }

    /**
     * Sprawdza, czy pusta komórka jest traktowana jako wartość 0.
     */
    @Test
    public void shouldReturnZeroForEmptyCell() {
        TableDataModel model = new TableDataModel();

        assertEquals(0.0, model.getNumericValueAt(0, 0), 0.0001);
    }

    /**
     * Sprawdza poprawne ustawianie wartości liczbowej w komórce.
     */
    @Test
    public void shouldSetNumericValueInCell() {
        TableDataModel model = new TableDataModel();

        model.setNumericValueAt(12.5, 2, 3);

        assertEquals(12.5, model.getNumericValueAt(2, 3), 0.0001);
    }

    /**
     * Sprawdza, czy metoda clear usuwa dane z tabeli.
     */
    @Test
    public void shouldClearTableData() {
        TableDataModel model = new TableDataModel();

        model.setNumericValueAt(10.0, 0, 0);
        model.setNumericValueAt(20.0, 1, 1);

        model.clear();

        assertEquals(0.0, model.getNumericValueAt(0, 0), 0.0001);
        assertEquals(0.0, model.getNumericValueAt(1, 1), 0.0001);
    }

    /**
     * Sprawdza, czy model pozwala na edycję komórek.
     */
    @Test
    public void shouldAllowCellEditing() {
        TableDataModel model = new TableDataModel();

        assertTrue(model.isCellEditable(0, 0));
    }

    /**
     * Sprawdza ustawianie wartości przez standardową metodę setValueAt.
     */
    @Test
    public void shouldSetValueUsingSetValueAt() {
        TableDataModel model = new TableDataModel();

        model.setValueAt("7.5", 4, 4);

        assertEquals(7.5, model.getNumericValueAt(4, 4), 0.0001);
    }
}