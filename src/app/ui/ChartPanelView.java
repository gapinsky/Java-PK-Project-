package app.ui;

import app.model.TableDataModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import javax.swing.*;
import java.awt.*;

/**
 * Panel wykresu kołowego utworzony przy użyciu biblioteki JFreeChart.
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class ChartPanelView extends JPanel {

    private final TableDataModel tableModel;
    private final org.jfree.chart.ChartPanel chartPanel;

    public ChartPanelView(TableDataModel tableModel) {
        this.tableModel = tableModel;

        setLayout(new BorderLayout());

        JFreeChart chart = createChart();
        chartPanel = new org.jfree.chart.ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(320, 280));
        chartPanel.setMinimumDrawWidth(250);
        chartPanel.setMinimumDrawHeight(220);
        chartPanel.setMaximumDrawWidth(900);
        chartPanel.setMaximumDrawHeight(700);

        add(chartPanel, BorderLayout.CENTER);
    }

    public void refreshChart() {
        chartPanel.setChart(createChart());
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

        JFreeChart chart = ChartFactory.createPieChart(
                "Wartości",
                dataset,
                true,
                true,
                false
        );

        PiePlot<?> plot = (PiePlot<?>) chart.getPlot();
        plot.setSimpleLabels(true);
        plot.setNoDataMessage("Brak danych");

        return chart;
    }
}