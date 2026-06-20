package app.ui;

import app.Window;

import javax.swing.*;
import javax.swing.event.HyperlinkEvent;
import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AuthorDialog extends JDialog {

    public AuthorDialog(Window owner) {
        super(owner, "O autorze", true);

        setSize(450, 260);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JEditorPane authorInfo = new JEditorPane();
        authorInfo.setContentType("text/html");
        authorInfo.setEditable(false);
        authorInfo.setOpaque(false);

        authorInfo.setText(
                "<html><body style='text-align: center; font-family: sans-serif;'>" +
                        "<h2>Projekt Java - Swing</h2>" +
                        "<p>Autor: Antoni Gapiński</p>" +
                        "<p>Politechnika Koszalińska 2026</p>" +
                        "<p>GitHub: @gapinsky</p>" +
                        "<p><a href='https://github.com/gapinsky/Java-PK-Project-'>github.com/gapinsky/Java-PK-Project-</a></p>" +
                        "</body></html>"
        );

        authorInfo.addHyperlinkListener(event -> {
            if (event.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                try {
                    Desktop.getDesktop().browse(new URI(event.getURL().toString()));
                } catch (IOException | URISyntaxException exception) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Nie udało się otworzyć linku.",
                            "Błąd",
                            JOptionPane.ERROR_MESSAGE
                    );
                }
            }
        });

        JButton closeButton = new JButton("Zamknij");
        closeButton.addActionListener(e -> dispose());

        add(authorInfo, BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
}