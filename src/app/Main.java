package app;

import org.apache.log4j.Logger;

import javax.swing.*;

/**
 * Klasa startowa aplikacji Swing.
 * <p>
 * Odpowiada za uruchomienie programu, utworzenie głównego okna aplikacji
 * oraz zapis podstawowych informacji do dziennika zdarzeń log4j.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class Main {

    /**
     * Logger klasy głównej programu.
     */
    private static final Logger logger = Logger.getLogger(Main.class);

    /**
     * Główna metoda uruchamiająca aplikację.
     * <p>
     * Tworzy okno aplikacji w wątku obsługi zdarzeń Swing.
     * </p>
     *
     * @param args argumenty przekazane podczas uruchamiania programu
     */
    public static void main(String[] args) {
        logger.info("Uruchamianie aplikacji");

        SwingUtilities.invokeLater(() -> {
            try {
                Window window = new Window();
                window.setVisible(true);
                logger.info("Okno aplikacji zostało wyświetlone");
            } catch (Exception exception) {
                logger.error("Błąd podczas uruchamiania aplikacji", exception);
            }
        });
    }
}