package app.ui;

import app.model.TableDataModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

public class ChartPanelView extends JPanel {

    private final TableDataModel tableModel;
    private org.jfree.chart.ChartPanel chartPanel;

    public ChartPanelView(TableDataModel tableModel) {
        this.tableModel = tableModel;

        setLayout(new BorderLayout());

        JFreeChart chart = createChart();
        chartPanel = new org.jfree.chart.ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(260, 220));

        add(chartPanel, BorderLayout.CENTER);
    }

    public void refreshChart() {
        JFreeChart chart = createChart();
        chartPanel.setChart(chart);
    }

    private JFreeChart createChart() {
        DefaultPieDataset<String> dataset = new DefaultPieDataset<>();

        double positiveSum = 0;
        double negativeSum = 0;
        double zeroCount = 0;

        for (int row = 0; row < tableModel.getRowCount(); row++) {
            for (int column = 0; column < tableModel.getColumnCount(); column++) {
                double value = tableModel.getNumericValueAt(row, column);

                if (value > 0) {
                    positiveSum += value;
                } else if (value < 0) {
                    negativeSum += Math.abs(value);
                } else {
                    zeroCount++;
                }
            }
        }

        dataset.setValue("Dodatnie", positiveSum);
        dataset.setValue("Ujemne", negativeSum);
        dataset.setValue("Zera", zeroCount);

        return ChartFactory.createPieChart(
                "Wartości w tabeli",
                dataset,
                true,
                true,
                false
        );
    }
}