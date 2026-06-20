package app.model;

public enum Operation {

    SUM("Suma elementów"),
    AVERAGE("Średnia elementów"),
    MIN_MAX("Wartość max i min");

    private final String label;

    Operation(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}