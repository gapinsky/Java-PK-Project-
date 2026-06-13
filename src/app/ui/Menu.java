package app.ui;

import app.Window;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu(Window window, Status status) {
        JMenu fileMenu = new JMenu("Plik");
        JMenu helpMenu = new JMenu("Pomoc");

        JMenuItem exitItem = new JMenuItem("Zamknij");
        JMenuItem helpItem = new JMenuItem("Instrukcja");
        JMenuItem authorItem = new JMenuItem("Autor");

        exitItem.addActionListener(e -> window.closeApplication());

        helpItem.addActionListener(e -> {
            status.setMessage("Wyświetlono pomoc");
            HelpDialog dialog = new HelpDialog(window);
            dialog.setVisible(true);
        });

        authorItem.addActionListener(e -> {
            status.setMessage("Wyświetlono informacje o autorze");
            AuthorDialog dialog = new AuthorDialog(window);
            dialog.setVisible(true);
        });

        fileMenu.add(exitItem);

        helpMenu.add(helpItem);
        helpMenu.add(authorItem);

        add(fileMenu);
        add(helpMenu);
    }
}