package app.ui;

import app.Window;

import javax.swing.*;
import java.awt.*;

public class HelpDialog extends JDialog {

    public HelpDialog(Window owner) {
        super(owner, "Pomoc", true);

        setSize(450, 300);
        setLocationRelativeTo(owner);
        setLayout(new BorderLayout());

        JTextArea helpText = new JTextArea();
        helpText.setEditable(false);
        helpText.setLineWrap(true);
        helpText.setWrapStyleWord(true);

        helpText.setText(
                "Instrukcja obsługi aplikacji:\n\n" +
                        "1. Wpisz liczbę w pole tekstowe.\n" +
                        "2. Wybierz wiersz i kolumnę tabeli za pomocą suwaków JSlider.\n" +
                        "3. Kliknij przycisk 'Wstaw do tabeli'.\n" +
                        "4. Wybierz operację z listy JComboBox.\n" +
                        "5. Kliknij 'Oblicz', aby wyświetlić wynik w polu tekstowym.\n" +
                        "6. Przycisk 'Zeruj tabelę' usuwa dane z tabeli.\n" +
                        "7. Przycisk 'Zapisz do pliku' zapisuje zawartość tabeli do pliku tabela.txt."
        );

        JButton closeButton = new JButton("Zamknij");
        closeButton.addActionListener(e -> dispose());

        add(new JScrollPane(helpText), BorderLayout.CENTER);
        add(closeButton, BorderLayout.SOUTH);
    }
}