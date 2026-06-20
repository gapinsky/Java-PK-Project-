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
 * <p>
 * Wykres prezentuje dane z tabeli w trzech kategoriach:
 * suma wartości dodatnich, suma wartości ujemnych oraz liczba zer.
 * </p>
 *
 * @author Antoni Gapiński
 * @version 3.0
 * @since 2026-06-20
 */
public class ChartPanelView extends JPanel {

    /**
     * Model danych tabeli, z którego pobierane są wartości do wykresu.
     */
    private final TableDataModel tableModel;

    /**
     * Panel JFreeChart wyświetlający wykres kołowy.
     */
    private final org.jfree.chart.ChartPanel chartPanel;

    /**
     * Konstruktor panelu wykresu.
     *
     * @param tableModel model danych tabeli używany jako źródło danych wykresu
     */
    public ChartPanelView(TableDataModel tableModel) {
        this.tableModel = tableModel;

        setLayout(new BorderLayout());

        JFreeChart chart = createChart();
        chartPanel = new org.jfree.chart.ChartPanel(chart);
        chartPanel.setPreferredSize(new Dimension(300, 230));

        add(chartPanel, BorderLayout.CENTER);
    }

    /**
     * Odświeża wykres na podstawie aktualnych danych w tabeli.
     */
    public void refreshChart() {
        chartPanel.setChart(createChart());
    }

    /**
     * Tworzy wykres kołowy na podstawie danych z tabeli.
     *
     * @return obiekt JFreeChart reprezentujący wykres kołowy
     */
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