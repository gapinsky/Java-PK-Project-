package app.ui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MainPanel extends JPanel {

    private final JTextField numberField;
    private final JSlider rowSlider;
    private final JSlider columnSlider;
    private final JTable table;
    private final JTextArea resultArea;
    private final JComboBox<String> operationComboBox;

    public MainPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        numberField = new JTextField(10);

        rowSlider = new JSlider(0, 4, 0);
        columnSlider = new JSlider(0, 4, 0);

        setupSlider(rowSlider);
        setupSlider(columnSlider);

        table = createTable();

        resultArea = new JTextArea(7, 30);
        resultArea.setEditable(false);

        operationComboBox = new JComboBox<>(new String[]{
                "Suma elementów",
                "Średnia elementów",
                "Wartość max i min"
        });

        add(createInputPanel(), BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private void setupSlider(JSlider slider) {
        slider.setMajorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        JLabel numberLabel = new JLabel("Liczba:");
        JLabel rowLabel = new JLabel("Wiersz:");
        JLabel columnLabel = new JLabel("Kolumna:");

        JButton insertButton = new JButton("Wstaw do tabeli");
        JButton clearButton = new JButton("Zeruj tabelę");
        JButton saveButton = new JButton("Zapisz do pliku");

        insertButton.addActionListener(e -> insertValueIntoTable());
        clearButton.addActionListener(e -> clearTable());
        saveButton.addActionListener(e -> saveTableToFile());

        panel.add(numberLabel);
        panel.add(numberField);

        panel.add(rowLabel);
        panel.add(rowSlider);

        panel.add(columnLabel);
        panel.add(columnSlider);

        panel.add(insertButton);
        panel.add(clearButton);
        panel.add(saveButton);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel operationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        operationPanel.setBorder(BorderFactory.createTitledBorder("JComboBox - wybór operacji"));

        JButton calculateButton = new JButton("Oblicz");

        calculateButton.addActionListener(e -> calculateSelectedOperation());

        operationPanel.add(new JLabel("Operacja:"));
        operationPanel.add(operationComboBox);
        operationPanel.add(calculateButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Wyniki obliczeń"));
        resultPanel.add(new JScrollPane(resultArea), BorderLayout.CENTER);

        panel.add(operationPanel, BorderLayout.NORTH);
        panel.add(resultPanel, BorderLayout.CENTER);

        return panel;
    }

    private JTable createTable() {
        String[] columns = {"0", "1", "2", "3", "4"};

        DefaultTableModel model = new DefaultTableModel(columns, 5) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        JTable createdTable = new JTable(model);
        createdTable.setRowHeight(28);

        return createdTable;
    }

    private void insertValueIntoTable() {
        String text = numberField.getText();

        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(
                    this,
                    "Wpisz liczbę w pole tekstowe.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        try {
            double value = Double.parseDouble(text);

            int row = rowSlider.getValue();
            int column = columnSlider.getValue();

            table.setValueAt(value, row, column);

            resultArea.append("Wstawiono wartość " + value +
                    " do komórki [" + row + ", " + column + "]\n");

            numberField.setText("");

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "Podana wartość nie jest poprawną liczbą.",
                    "Błąd danych",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void clearTable() {
        for (int row = 0; row < table.getRowCount(); row++) {
            for (int column = 0; column < table.getColumnCount(); column++) {
                table.setValueAt(null, row, column);
            }
        }

        resultArea.append("Tabela została wyzerowana.\n");
    }

    private void calculateSelectedOperation() {
        String selectedOperation = (String) operationComboBox.getSelectedItem();

        if (selectedOperation == null) {
            JOptionPane.showMessageDialog(
                    this,
                    "Wybierz operację z pola JComboBox.",
                    "Błąd",
                    JOptionPane.ERROR_MESSAGE
            );
            return;
        }

        calculate(selectedOperation);
    }

    private void calculate(String operation) {
        try {
            double sum = 0;
            int count = 0;
            Double min = null;
            Double max = null;

            for (int row = 0; row < table.getRowCount(); row++) {
                for (int column = 0; column < table.getColumnCount(); column++) {
                    Object cellValue = table.getValueAt(row, column);

                    if (cellValue != null) {
                        double value = Double.parseDouble(cellValue.toString());

                        sum += value;
                        count++;

                        if (min == null || value < min) {
                            min = value;
                        }

                        if (max == null || value > max) {
                            max = value;
                        }
                    }
                }
            }

            if (count == 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Tabela jest pusta. Brak danych do obliczeń.",
                        "Błąd",
                        JOptionPane.ERROR_MESSAGE
                );
                return;
            }

            switch (operation) {
                case "Suma elementów":
                    resultArea.append("Suma elementów: " + sum + "\n");
                    break;

                case "Średnia elementów":
                    double average = sum / count;
                    resultArea.append("Średnia elementów: " + average + "\n");
                    break;

                case "Wartość max i min":
                    resultArea.append("Wartość maksymalna: " + max + "\n");
                    resultArea.append("Wartość minimalna: " + min + "\n");
                    break;

                default:
                    resultArea.append("Nieznana operacja.\n");
            }

        } catch (NumberFormatException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "Tabela zawiera niepoprawne dane.",
                    "Błąd danych",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    private void saveTableToFile() {
        try (FileWriter writer = new FileWriter("tabela.txt")) {
            for (int row = 0; row < table.getRowCount(); row++) {
                for (int column = 0; column < table.getColumnCount(); column++) {
                    Object value = table.getValueAt(row, column);

                    if (value == null) {
                        writer.write("0");
                    } else {
                        writer.write(value.toString());
                    }

                    if (column < table.getColumnCount() - 1) {
                        writer.write("; ");
                    }
                }

                writer.write("\n");
            }

            resultArea.append("Zapisano zawartość tabeli do pliku tabela.txt\n");

            JOptionPane.showMessageDialog(
                    this,
                    "Zapisano plik tabela.txt w folderze projektu.",
                    "Zapis zakończony",
                    JOptionPane.INFORMATION_MESSAGE
            );

        } catch (IOException exception) {
            JOptionPane.showMessageDialog(
                    this,
                    "Nie udało się zapisać pliku.",
                    "Błąd zapisu",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
}