package app;

import app.ui.MainPanel;
import app.ui.Menu;
import app.ui.NavigationPanel;
import app.ui.Status;
import app.ui.Tool;
import app.ui.TipOfTheDayService;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Główne okno aplikacji.
 * <p>
 * Klasa tworzy główną ramkę programu, ustawia menu, pasek narzędzi,
 * panel statusu, panel nawigacyjny JOutlookBar oraz panel główny aplikacji.
 * Obsługuje również zamykanie programu oraz wyświetlenie okna Tip of the Day.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class Window extends JFrame {

    /**
     * Logger służący do zapisu informacji o działaniu głównego okna aplikacji.
     */
    private static final Logger logger = Logger.getLogger(Window.class);

    /**
     * Panel statusu wyświetlający komunikaty na dole okna.
     */
    private final Status status;

    /**
     * Konstruktor głównego okna aplikacji.
     * <p>
     * Inicjalizuje układ graficzny programu, tworzy panele,
     * podpina zdarzenia oraz uruchamia okno z poradą dnia.
     * </p>
     */
    public Window() {
        logger.info("Tworzenie głównego okna aplikacji");

        setTitle("Projekt Java - Interfejs GUI");
        setSize(1200, 800);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        status = new Status();

        setJMenuBar(new Menu(this, status));
        add(new Tool(this, status), BorderLayout.NORTH);

        MainPanel mainPanel = new MainPanel();

        NavigationPanel navigationPanel = new NavigationPanel(
                mainPanel::focusNumberField,
                mainPanel::clearTable,
                mainPanel::calculateSum,
                mainPanel::calculateAverage,
                mainPanel::calculateMinMax,
                mainPanel::saveTableToFile,
                mainPanel::exportTableToPdf
        );

        navigationPanel.setMinimumSize(new Dimension(150, 0));
        mainPanel.setMinimumSize(new Dimension(700, 0));

        JSplitPane splitPane = new JSplitPane(
                JSplitPane.HORIZONTAL_SPLIT,
                navigationPanel,
                mainPanel
        );

        splitPane.setDividerLocation(180);
        splitPane.setResizeWeight(0);

        add(splitPane, BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            /**
             * Obsługuje próbę zamknięcia okna aplikacji.
             *
             * @param e zdarzenie zamknięcia okna
             */
            @Override
            public void windowClosing(WindowEvent e) {
                closeApplication();
            }
        });

        SwingUtilities.invokeLater(() -> {
            logger.info("Wyświetlanie okna Tip of the Day");
            TipOfTheDayService.showTip(this);
        });

        logger.info("Główne okno aplikacji zostało utworzone");
    }

    /**
     * Obsługuje zamykanie aplikacji.
     * <p>
     * Wyświetla okno potwierdzenia. Po zaakceptowaniu zamyka program,
     * a po anulowaniu aktualizuje komunikat w panelu statusu.
     * </p>
     */
    public void closeApplication() {
        logger.info("Użytkownik próbuje zamknąć aplikację");

        int result = JOptionPane.showConfirmDialog(
                this,
                "Czy na pewno chcesz zamknąć aplikację?",
                "Zamykanie aplikacji",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            logger.info("Użytkownik potwierdził zamknięcie aplikacji");
            dispose();
            System.exit(0);
        } else {
            logger.info("Użytkownik anulował zamknięcie aplikacji");
            status.setMessage("Anulowano zamykanie aplikacji");
        }
    }
}