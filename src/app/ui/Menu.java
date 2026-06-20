package app.ui;

import app.Window;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu(Window window, Status status, MainPanel mainPanel) {

        JMenu fileMenu = new JMenu("Plik");
        JMenu editMenu = new JMenu("Edycja");
        JMenu viewMenu = new JMenu("Widok");
        JMenu calculateMenu = new JMenu("Obliczenia");
        JMenu helpMenu = new JMenu("Pomoc");

        JMenuItem saveItem = new JMenuItem("Zapisz do pliku");
        JMenuItem pdfItem = new JMenuItem("Eksport PDF");
        JMenuItem exitItem = new JMenuItem("Zamknij");

        saveItem.addActionListener(e -> {
            status.setMessage("Zapisano dane do pliku");
            mainPanel.saveTableToFile();
        });

        pdfItem.addActionListener(e -> {
            status.setMessage("Wyeksportowano dane do PDF");
            mainPanel.exportTableToPdf();
        });

        exitItem.addActionListener(e -> window.closeApplication());

        JMenuItem insertItem = new JMenuItem("Wstaw dane");
        JMenuItem clearItem = new JMenuItem("Zeruj tabelę");

        insertItem.addActionListener(e -> {
            status.setMessage("Ustawiono fokus na polu liczby");
            mainPanel.focusNumberField();
        });

        clearItem.addActionListener(e -> {
            status.setMessage("Tabela została wyzerowana");
            mainPanel.clearTable();
        });

        JMenuItem chartItem = new JMenuItem("Wykres kołowy");
        JMenuItem calendarItem = new JMenuItem("Kalendarz");

        chartItem.addActionListener(e ->
                status.setMessage("Wykres kołowy znajduje się po prawej stronie")
        );

        calendarItem.addActionListener(e ->
                status.setMessage("Kalendarz znajduje się po prawej stronie")
        );

        JMenuItem sumItem = new JMenuItem("Suma");
        JMenuItem averageItem = new JMenuItem("Średnia");
        JMenuItem minMaxItem = new JMenuItem("Min / Max");

        sumItem.addActionListener(e -> {
            status.setMessage("Obliczono sumę");
            mainPanel.calculateSum();
        });

        averageItem.addActionListener(e -> {
            status.setMessage("Obliczono średnią");
            mainPanel.calculateAverage();
        });

        minMaxItem.addActionListener(e -> {
            status.setMessage("Obliczono minimum i maksimum");
            mainPanel.calculateMinMax();
        });

        JMenuItem helpItem = new JMenuItem("Instrukcja");
        JMenuItem authorItem = new JMenuItem("Autor");

        helpItem.addActionListener(e -> {
            status.setMessage("Wyświetlono pomoc");
            new HelpDialog(window).setVisible(true);
        });

        authorItem.addActionListener(e -> {
            status.setMessage("Wyświetlono informacje o autorze");
            new AuthorDialog(window).setVisible(true);
        });

        fileMenu.add(saveItem);
        fileMenu.add(pdfItem);
        fileMenu.addSeparator();
        fileMenu.add(exitItem);

        editMenu.add(insertItem);
        editMenu.add(clearItem);

        viewMenu.add(chartItem);
        viewMenu.add(calendarItem);

        calculateMenu.add(sumItem);
        calculateMenu.add(averageItem);
        calculateMenu.add(minMaxItem);

        helpMenu.add(helpItem);
        helpMenu.add(authorItem);

        add(fileMenu);
        add(editMenu);
        add(viewMenu);
        add(calculateMenu);
        add(helpMenu);
    }
}