package app.ui;

import app.model.Operation;
import app.model.TableDataModel;

import javax.swing.*;
import java.awt.*;
import java.io.FileWriter;
import java.io.IOException;

public class MainPanel extends JPanel {

    private final JTextField numberField;
    private final JSlider rowSlider;
    private final JSlider columnSlider;
    private final JTable table;
    private final JTextArea resultArea;
    private final JComboBox<Operation> operationComboBox;
    private final TableDataModel tableModel;
    private final ChartPanelView chartPanelView;

    public MainPanel() {
        setLayout(new BorderLayout(10, 8));
        setBorder(BorderFactory.createEmptyBorder(8, 10, 8, 10));

        numberField = new JTextField(10);

        rowSlider = new JSlider(0, 4, 0);
        columnSlider = new JSlider(0, 4, 0);

        setupSlider(rowSlider);
        setupSlider(columnSlider);

        tableModel = new TableDataModel();
        chartPanelView = new ChartPanelView(tableModel);

        table = new JTable(tableModel);
        table.setRowHeight(26);

        resultArea = new JTextArea(4, 30);
        resultArea.setEditable(false);

        operationComboBox = new JComboBox<>(Operation.values());

        add(createInputPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
    }

    private void setupSlider(JSlider slider) {
        slider.setMajorTickSpacing(1);
        slider.setMinorTickSpacing(1);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
    }

    private JPanel createInputPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 4));

        JPanel selectorsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 3));
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 3));

        JLabel numberLabel = new JLabel("Liczba:");
        JLabel rowLabel = new JLabel("Wiersz:");
        JLabel columnLabel = new JLabel("Kolumna:");

        numberField.setPreferredSize(new Dimension(110, 24));

        rowSlider.setPreferredSize(new Dimension(170, 42));
        rowSlider.setMaximumSize(new Dimension(170, 42));

        columnSlider.setPreferredSize(new Dimension(170, 42));
        columnSlider.setMaximumSize(new Dimension(170, 42));

        JButton insertButton = new JButton("Wstaw do tabeli");
        JButton clearButton = new JButton("Zeruj tabelę");
        JButton saveButton = new JButton("Zapisz do pliku");

        insertButton.addActionListener(e -> insertValueIntoTable());
        clearButton.addActionListener(e -> clearTable());
        saveButton.addActionListener(e -> saveTableToFile());

        selectorsPanel.add(numberLabel);
        selectorsPanel.add(numberField);

        selectorsPanel.add(rowLabel);
        selectorsPanel.add(rowSlider);

        selectorsPanel.add(columnLabel);
        selectorsPanel.add(columnSlider);

        buttonsPanel.add(insertButton);
        buttonsPanel.add(clearButton);
        buttonsPanel.add(saveButton);

        panel.add(selectorsPanel, BorderLayout.NORTH);
        panel.add(buttonsPanel, BorderLayout.SOUTH);

        return panel;
    }

    private JPanel createCenterPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));

        JPanel tablePanel = new JPanel(new BorderLayout());
        tablePanel.setBorder(BorderFactory.createTitledBorder("Tabela 5x5"));
        tablePanel.add(new JScrollPane(table), BorderLayout.CENTER);

        CalendarPanel calendarPanel = new CalendarPanel(resultArea);
        calendarPanel.setPreferredSize(new Dimension(300, 230));
        calendarPanel.setMaximumSize(new Dimension(300, 230));

        JButton refreshChartButton = new JButton("Odśwież wykres");
        refreshChartButton.addActionListener(e -> chartPanelView.refreshChart());

        JPanel chartWrapper = new JPanel(new BorderLayout());
        chartWrapper.setBorder(BorderFactory.createTitledBorder("Wykres kołowy"));
        chartWrapper.add(chartPanelView, BorderLayout.CENTER);
        chartWrapper.add(refreshChartButton, BorderLayout.SOUTH);

        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setPreferredSize(new Dimension(310, 0));

        calendarPanel.setAlignmentX(Component.LEFT_ALIGNMENT);
        chartWrapper.setAlignmentX(Component.LEFT_ALIGNMENT);

        rightPanel.add(calendarPanel);
        rightPanel.add(Box.createVerticalStrut(8));
        rightPanel.add(chartWrapper);

        panel.add(tablePanel, BorderLayout.CENTER);
        panel.add(rightPanel, BorderLayout.EAST);

        return panel;
    }

    private JPanel createBottomPanel() {
        JPanel panel = new JPanel(new BorderLayout(8, 6));

        JPanel operationPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 3));
        operationPanel.setBorder(BorderFactory.createTitledBorder("JComboBox - wybór operacji"));

        JButton calculateButton = new JButton("Oblicz");
        calculateButton.addActionListener(e -> calculateSelectedOperation());

        operationPanel.add(new JLabel("Operacja:"));
        operationPanel.add(operationComboBox);
        operationPanel.add(calculateButton);

        JPanel resultPanel = new JPanel(new BorderLayout());
        resultPanel.setBorder(BorderFactory.createTitledBorder("Wyniki obliczeń"));

        JScrollPane resultScrollPane = new JScrollPane(resultArea);
        resultScrollPane.setPreferredSize(new Dimension(0, 95));

        resultPanel.add(resultScrollPane, BorderLayout.CENTER);

        panel.add(operationPanel, BorderLayout.NORTH);
        panel.add(resultPanel, BorderLayout.CENTER);

        return panel;
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

            tableModel.setNumericValueAt(value, row, column);
            chartPanelView.refreshChart();

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

    public void clearTable() {
        tableModel.clear();
        chartPanelView.refreshChart();

        resultArea.append("Tabela została wyzerowana. Puste komórki są traktowane jako 0.\n");
    }

    private void calculateSelectedOperation() {
        Operation selectedOperation = (Operation) operationComboBox.getSelectedItem();

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

    public void calculateSum() {
        operationComboBox.setSelectedItem(Operation.SUM);
        calculate(Operation.SUM);
    }

    public void calculateAverage() {
        operationComboBox.setSelectedItem(Operation.AVERAGE);
        calculate(Operation.AVERAGE);
    }

    public void calculateMinMax() {
        operationComboBox.setSelectedItem(Operation.MIN_MAX);
        calculate(Operation.MIN_MAX);
    }

    public void focusNumberField() {
        numberField.requestFocusInWindow();
        resultArea.append("Akcja nawigacji: ustawiono fokus na polu liczby.\n");
    }

    private void calculate(Operation operation) {
        double sum = 0;
        double min = 0;
        double max = 0;
        boolean firstCell = true;

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            for (int column = 0; column < tableModel.getColumnCount(); column++) {
                double value = tableModel.getNumericValueAt(row, column);

                sum += value;

                if (firstCell) {
                    min = value;
                    max = value;
                    firstCell = false;
                } else {
                    if (value < min) {
                        min = value;
                    }

                    if (value > max) {
                        max = value;
                    }
                }
            }
        }

        switch (operation) {
            case SUM:
                resultArea.append("Suma elementów: " + sum + "\n");
                break;

            case AVERAGE:
                double average = sum / tableModel.getElementsCount();
                resultArea.append("Średnia elementów: " + average + "\n");
                break;

            case MIN_MAX:
                resultArea.append("Wartość maksymalna: " + max + "\n");
                resultArea.append("Wartość minimalna: " + min + "\n");
                break;
        }
    }

    public void saveTableToFile() {
        try (FileWriter writer = new FileWriter("tabela.txt")) {
            for (int row = 0; row < tableModel.getRowCount(); row++) {
                for (int column = 0; column < tableModel.getColumnCount(); column++) {
                    double value = tableModel.getNumericValueAt(row, column);
                    writer.write(String.valueOf(value));

                    if (column < tableModel.getColumnCount() - 1) {
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