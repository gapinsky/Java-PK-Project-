package app;

import app.ui.Menu;
import app.ui.Tool;
import app.ui.Status;
import app.ui.MainPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Window extends JFrame {

    private final Status status;

    public Window() {
        setTitle("Projekt Java - Interfejs GUI");
        setSize(900, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        status = new Status();

        setJMenuBar(new Menu(this, status));
        add(new Tool(this, status), BorderLayout.NORTH);
        add(new MainPanel(), BorderLayout.CENTER);
        add(status, BorderLayout.SOUTH);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                closeApplication();
            }
        });
    }

    public void closeApplication() {
        int result = JOptionPane.showConfirmDialog(
                this,
                "Czy na pewno chcesz zamknąć aplikację?",
                "Zamykanie aplikacji",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE
        );

        if (result == JOptionPane.YES_OPTION) {
            dispose();
            System.exit(0);
        } else {
            status.setMessage("Anulowano zamykanie aplikacji");
        }
    }
}