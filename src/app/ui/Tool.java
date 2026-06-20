package app.ui;

import app.Window;

import javax.swing.*;
import java.awt.*;

public class Tool extends JToolBar {

    public Tool(Window window, Status status, MainPanel mainPanel) {
        setFloatable(false);
        setBorder(BorderFactory.createEtchedBorder());

        JButton saveButton = createToolbarButton("", UIManager.getIcon("FileView.floppyDriveIcon"));
        JButton pdfButton = createToolbarButton("PDF", UIManager.getIcon("FileView.fileIcon"));
        JButton clearButton = createToolbarButton("", UIManager.getIcon("OptionPane.errorIcon"));
        JButton sumButton = createToolbarButton("Σ", null);
        JButton avgButton = createToolbarButton("Śr", null);
        JButton minMaxButton = createToolbarButton("Min/Max", null);
        JButton helpButton = createToolbarButton("", UIManager.getIcon("OptionPane.questionIcon"));
        JButton authorButton = createToolbarButton("", UIManager.getIcon("OptionPane.informationIcon"));
        JButton exitButton = createToolbarButton("", UIManager.getIcon("InternalFrame.closeIcon"));

        saveButton.setToolTipText("Zapisz dane do pliku tekstowego");
        pdfButton.setToolTipText("Eksportuj tabelę do pliku PDF");
        clearButton.setToolTipText("Wyczyść tabelę");
        sumButton.setToolTipText("Oblicz sumę");
        avgButton.setToolTipText("Oblicz średnią");
        minMaxButton.setToolTipText("Oblicz minimum i maksimum");
        helpButton.setToolTipText("Instrukcja");
        authorButton.setToolTipText("Informacje o autorze");
        exitButton.setToolTipText("Zamknij aplikację");

        saveButton.addActionListener(e -> {
            status.setMessage("Zapisano dane do pliku");
            mainPanel.saveTableToFile();
        });

        pdfButton.addActionListener(e -> {
            status.setMessage("Wyeksportowano dane do PDF");
            mainPanel.exportTableToPdf();
        });

        clearButton.addActionListener(e -> {
            status.setMessage("Tabela została wyzerowana");
            mainPanel.clearTable();
        });

        sumButton.addActionListener(e -> {
            status.setMessage("Obliczono sumę");
            mainPanel.calculateSum();
        });

        avgButton.addActionListener(e -> {
            status.setMessage("Obliczono średnią");
            mainPanel.calculateAverage();
        });

        minMaxButton.addActionListener(e -> {
            status.setMessage("Obliczono minimum i maksimum");
            mainPanel.calculateMinMax();
        });

        helpButton.addActionListener(e -> {
            status.setMessage("Wyświetlono pomoc");
            new HelpDialog(window).setVisible(true);
        });

        authorButton.addActionListener(e -> {
            status.setMessage("Wyświetlono informacje o autorze");
            new AuthorDialog(window).setVisible(true);
        });

        exitButton.addActionListener(e -> window.closeApplication());

        add(saveButton);
        add(pdfButton);
        add(clearButton);

        addSeparator();

        add(sumButton);
        add(avgButton);
        add(minMaxButton);

        addSeparator();

        add(helpButton);
        add(authorButton);

        addSeparator();

        add(exitButton);
    }

    private JButton createToolbarButton(String text, Icon icon) {
        JButton button = new JButton(text, icon);

        Dimension buttonSize = new Dimension(90, 36);
        button.setPreferredSize(buttonSize);
        button.setMinimumSize(buttonSize);
        button.setMaximumSize(buttonSize);

        button.setHorizontalTextPosition(SwingConstants.RIGHT);
        button.setVerticalTextPosition(SwingConstants.CENTER);
        button.setFocusable(false);

        return button;
    }
}