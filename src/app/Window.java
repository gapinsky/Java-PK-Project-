package app;

import app.ui.MainPanel;
import app.ui.Menu;
import app.ui.NavigationPanel;
import app.ui.Status;
import app.ui.Tool;
import app.ui.TipOfTheDayService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    private final Status status;

    public Window() {
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
                mainPanel::saveTableToFile
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

        SwingUtilities.invokeLater(() -> TipOfTheDayService.showTip(this));
    }

    public void closeApplication() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Czy na pewno chcesz zamknąć aplikację?",
                "Zamykanie aplikacji",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        } else {
            status.setMessage("Anulowano zamykanie aplikacji");
        }
    }
}