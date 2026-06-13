package app.ui;

import javax.swing.*;
import java.awt.*;

public class Status extends JPanel {

    private final JLabel messageLabel;

    public Status() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createEtchedBorder());

        messageLabel = new JLabel("Gotowy");
        messageLabel.setBorder(BorderFactory.createEmptyBorder(4, 8, 4, 8));

        add(messageLabel, BorderLayout.WEST);
    }

    public void setMessage(String message) {
        messageLabel.setText(message);
    }
}