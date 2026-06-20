package app;

import org.apache.log4j.Logger;

import javax.swing.*;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class);

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