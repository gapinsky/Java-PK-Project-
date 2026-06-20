package app.ui;

import com.l2fprod.common.swing.JOutlookBar;

import javax.swing.*;
import java.awt.*;

/**
 * Panel nawigacyjny aplikacji oparty na komponencie JOutlookBar.
 * <p>
 * Panel grupuje akcje programu w zakładkach: tabela, obliczenia oraz plik.
 * Komponent został użyty zgodnie z wymaganiem dla numeru indeksu kończącego się cyfrą 9.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class NavigationPanel extends JPanel {

    /**
     * Konstruktor panelu nawigacyjnego.
     * <p>
     * Przyjmuje akcje w postaci obiektów Runnable i podpina je pod przyciski
     * znajdujące się w komponencie JOutlookBar.
     * </p>
     *
     * @param focusNumberFieldAction akcja ustawiająca fokus na polu liczby
     * @param clearTableAction       akcja czyszcząca tabelę
     * @param calculateSumAction     akcja obliczająca sumę
     * @param calculateAverageAction akcja obliczająca średnią
     * @param calculateMinMaxAction  akcja obliczająca minimum i maksimum
     * @param saveToFileAction       akcja zapisująca tabelę do pliku tekstowego
     * @param exportPdfAction        akcja eksportująca tabelę do pliku PDF
     */
    public NavigationPanel(
            Runnable focusNumberFieldAction,
            Runnable clearTableAction,
            Runnable calculateSumAction,
            Runnable calculateAverageAction,
            Runnable calculateMinMaxAction,
            Runnable saveToFileAction,
            Runnable exportPdfAction
    ) {
        setLayout(new BorderLayout());

        JOutlookBar outlookBar = new JOutlookBar();

        JButton focusButton = new JButton("Wstaw dane");
        JButton clearButton = new JButton("Zeruj tabelę");

        focusButton.addActionListener(e -> focusNumberFieldAction.run());
        clearButton.addActionListener(e -> clearTableAction.run());

        outlookBar.addTab("Tabela", createButtonPanel(
                focusButton,
                clearButton
        ));

        JButton sumButton = new JButton("Suma");
        JButton averageButton = new JButton("Średnia");
        JButton minMaxButton = new JButton("Min / Max");

        sumButton.addActionListener(e -> calculateSumAction.run());
        averageButton.addActionListener(e -> calculateAverageAction.run());
        minMaxButton.addActionListener(e -> calculateMinMaxAction.run());

        outlookBar.addTab("Obliczenia", createButtonPanel(
                sumButton,
                averageButton,
                minMaxButton
        ));

        JButton saveButton = new JButton("Zapis do pliku");
        JButton pdfButton = new JButton("Eksport PDF");

        saveButton.addActionListener(e -> saveToFileAction.run());
        pdfButton.addActionListener(e -> exportPdfAction.run());

        outlookBar.addTab("Plik", createButtonPanel(
                saveButton,
                pdfButton
        ));

        add(outlookBar, BorderLayout.CENTER);
    }

    /**
     * Tworzy panel przycisków dla wybranej zakładki JOutlookBar.
     *
     * @param buttons przyciski dodawane do panelu
     * @return panel zawierający przekazane przyciski
     */
    private JPanel createButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (JButton button : buttons) {
            panel.add(button);
        }

        return panel;
    }
}