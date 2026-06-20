package app.model;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Testy jednostkowe typu wyliczeniowego Operation.
 * <p>
 * Sprawdzają teksty wyświetlane użytkownikowi w komponencie JComboBox.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 4.0
 * @since 2026-06-20
 */
public class OperationTest {

    /**
     * Sprawdza opis operacji sumowania.
     */
    @Test
    public void shouldReturnSumLabel() {
        assertEquals("Suma elementów", Operation.SUM.toString());
    }

    /**
     * Sprawdza opis operacji średniej.
     */
    @Test
    public void shouldReturnAverageLabel() {
        assertEquals("Średnia elementów", Operation.AVERAGE.toString());
    }

    /**
     * Sprawdza opis operacji minimum i maksimum.
     */
    @Test
    public void shouldReturnMinMaxLabel() {
        assertEquals("Wartość max i min", Operation.MIN_MAX.toString());
    }

    /**
     * Sprawdza liczbę dostępnych operacji.
     */
    @Test
    public void shouldContainThreeOperations() {
        assertEquals(3, Operation.values().length);
    }
}