package app.ui;

import app.Window;

import javax.swing.*;

public class Menu extends JMenuBar {

    public Menu(Window window, Status status) {
        JMenu fileMenu = new JMenu("Plik");
        JMenu helpMenu = new JMenu("Pomoc");

        JMenuItem exitItem = new JMenuItem("Zamknij");
        JMenuItem authorItem = new JMenuItem("Autor");

        exitItem.addActionListener(e -> window.closeApplication());

        authorItem.addActionListener(e -> {
            status.setMessage("Wyświetlono informacje o autorze");
            AuthorDialog dialog = new AuthorDialog(window);
            dialog.setVisible(true);
        });

        fileMenu.add(exitItem);
        helpMenu.add(authorItem);

        add(fileMenu);
        add(helpMenu);
    }
}