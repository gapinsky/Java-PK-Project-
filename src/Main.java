import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {

    static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            AppWindow window = new AppWindow();
            window.setVisible(true);
        });
    }
}

class AppWindow extends JFrame {

    public AppWindow() {
        setTitle("Projekt Java - Interfejs GUI");

        // rozmiar okna
        setSize(900, 600);

        // ustawienie okna na środku ekranu
        setLocationRelativeTo(null);

        // user obsluguje zamykanie okna
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

        // zdarzenie zamkniecia okna
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int result = JOptionPane.showConfirmDialog(
                        AppWindow.this,
                        "Czy na pewno chcesz zamknąć aplikację?",
                        "Zamykanie aplikacji",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (result == JOptionPane.YES_OPTION) {
                    dispose();
                    System.exit(0);
                }
            }
        });
    }
}