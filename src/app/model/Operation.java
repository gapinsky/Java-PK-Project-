package app.model;

/**
 * Typ wyliczeniowy reprezentujący operacje możliwe do wykonania na danych z tabeli.
 * <p>
 * Wartości tego typu są wyświetlane w komponencie JComboBox.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public enum Operation {

    /**
     * Operacja obliczająca sumę wszystkich elementów tabeli.
     */
    SUM("Suma elementów"),

    /**
     * Operacja obliczająca średnią arytmetyczną elementów tabeli.
     */
    AVERAGE("Średnia elementów"),

    /**
     * Operacja wyznaczająca wartość minimalną i maksymalną z tabeli.
     */
    MIN_MAX("Wartość max i min");

    /**
     * Tekst wyświetlany użytkownikowi w polu JComboBox.
     */
    private final String label;

    /**
     * Konstruktor wartości typu wyliczeniowego.
     *
     * @param label etykieta wyświetlana w interfejsie użytkownika
     */
    Operation(String label) {
        this.label = label;
    }

    /**
     * Zwraca opis operacji widoczny w komponencie JComboBox.
     *
     * @return tekstowa etykieta operacji
     */
    @Override
    public String toString() {
        return label;
    }
}