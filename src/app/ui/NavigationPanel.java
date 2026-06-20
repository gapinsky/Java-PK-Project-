package app.ui;

import com.l2fprod.common.swing.JOutlookBar;

import javax.swing.*;
import java.awt.*;

public class NavigationPanel extends JPanel {

    public NavigationPanel() {
        setLayout(new BorderLayout());

        JOutlookBar outlookBar = new JOutlookBar();

        outlookBar.addTab("Tabela", createTextPanel(
                "Panel tabeli",
                "W tej części aplikacji wpisujesz liczby do tabeli 5x5."
        ));

        outlookBar.addTab("Obliczenia", createTextPanel(
                "Panel obliczeń",
                "Tutaj wybierasz operację: suma, średnia albo wartość max/min."
        ));

        outlookBar.addTab("Plik", createTextPanel(
                "Panel pliku",
                "Funkcja zapisu zapisuje zawartość tabeli do pliku tabela.txt."
        ));

        add(outlookBar, BorderLayout.CENTER);
    }

    private JPanel createTextPanel(String title, String description) {
        JPanel panel = new JPanel(new BorderLayout());

        JLabel label = new JLabel(
                "<html><center>" +
                        "<h3>" + title + "</h3>" +
                        "<p>" + description + "</p>" +
                        "</center></html>",
                SwingConstants.CENTER
        );

        panel.add(label, BorderLayout.CENTER);

        return panel;
    }
}