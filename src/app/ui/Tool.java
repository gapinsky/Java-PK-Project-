package app.ui;

import app.Window;

import javax.swing.*;

public class Tool extends JToolBar {

    public Tool(Window window, Status status) {
        JButton authorButton = new JButton("Autor");
        JButton exitButton = new JButton("Zamknij");

        authorButton.addActionListener(e -> {
            status.setMessage("Wyświetlono informacje o autorze");
            AuthorDialog dialog = new AuthorDialog(window);
            dialog.setVisible(true);
        });

        exitButton.addActionListener(e -> window.closeApplication());

        add(authorButton);
        addSeparator();
        add(exitButton);
    }
}