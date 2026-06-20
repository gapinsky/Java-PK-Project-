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

public class Window extends JFrame {

    private static final Logger logger = Logger.getLogger(Window.class);

    private final Status status;

    public Window() {
        logger.info("Tworzenie głównego okna aplikacji");

        setTitle("Projekt Java - Interfejs GUI");
        setSize(1250, 720);
        setMinimumSize(new Dimension(1100, 650));
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        status = new Status();

        MainPanel mainPanel = new MainPanel();

        setJMenuBar(new Menu(this, status, mainPanel));
        add(new Tool(this, status, mainPanel), BorderLayout.NORTH);

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