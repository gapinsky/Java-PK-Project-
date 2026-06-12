package app.ui;

import app.Window;

import javax.swing.*;
import java.awt.*;

public class AuthorDialog extends JDialog {

    public AuthorDialog(Window owner) {
        super(owner, "O autorze", true);

        setSize(350, 200);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JLabel authorInfo = new JLabel(
                "<html><center>" +
                        "<h2>Projekt Java - Swing</h2>" +
                        "<p>Autor: Antoni Gapiński</p>" +
                        "<p>Politechnika Koszalińska 2026</p>" +
                        "</center></html>",
                SwingConstants.CENTER
        );

        JButton closeButton = new JButton("Zamknij");
        closeButton.addActionListener(e -> dispose());

        add(authorInfo, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
}