package app.ui;

import com.l2fprod.common.swing.JOutlookBar;

import javax.swing.*;
import java.awt.*;

public class NavigationPanel extends JPanel {

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

    private JPanel createButtonPanel(JButton... buttons) {
        JPanel panel = new JPanel(new GridLayout(0, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        for (JButton button : buttons) {
            panel.add(button);
        }

        return panel;
    }
}