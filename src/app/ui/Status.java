package app.ui;

import javax.swing.*;
import java.awt.*;

/**
 * Panel statusu wyświetlany na dole okna aplikacji.
 */
public class Status extends JPanel {

    private final JLabel messageLabel;

    public Status() {
        setLayout(new BorderLayout());

        setPreferredSize(new Dimension(0, 30));
        setBorder(BorderFactory.createEtchedBorder());

        messageLabel = new JLabel("Status: Gotowy");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));

        add(messageLabel, BorderLayout.WEST);
    }

    /**
     * Zmienia komunikat wyświetlany w pasku statusu.
     *
     * @param message nowa wiadomość statusu
     */
    public void setMessage(String message) {
        messageLabel.setText("Status: " + message);
    }
}