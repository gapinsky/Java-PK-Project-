package app.ui;

import app.Window;

import javax.swing.*;

public class Tool extends JToolBar {

    public Tool(Window window, Status status) {
        JButton helpButton = new JButton("Pomoc");
        JButton authorButton = new JButton("Autor");
        JButton exitButton = new JButton("Zamknij");

        helpButton.addActionListener(e -> {
            status.setMessage("Wyświetlono pomoc");
            HelpDialog dialog = new HelpDialog(window);
            dialog.setVisible(true);
        });

        authorButton.addActionListener(e -> {
            status.setMessage("Wyświetlono informacje o autorze");
            AuthorDialog dialog = new AuthorDialog(window);
            dialog.setVisible(true);
        });

        exitButton.addActionListener(e -> window.closeApplication());

        add(helpButton);
        add(authorButton);
        addSeparator();
        add(exitButton);
    }
}