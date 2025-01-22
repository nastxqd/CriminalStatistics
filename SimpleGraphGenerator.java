package com.example.criminal3;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
import java.text.NumberFormat;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.List;

public class SimpleGraphGenerator {

    public static JFreeChart generatePieChart(List<String> labels, List<Double> values, String name) throws IOException {
        PieDataset dataset = createDataset(labels, values);
        JFreeChart chart = ChartFactory.createPieChart(
                name,
                dataset,
                true,
                true,
                false
        );
        customizeChart(chart);
        return chart;
    }
    private static void customizeChart(JFreeChart chart) {
        PiePlot plot = (PiePlot) chart.getPlot();
        plot.setBackgroundPaint(Color.WHITE);
        plot.setLabelFont(new Font("Arial", Font.PLAIN, 12));
        plot.setLabelPaint(Color.DARK_GRAY);


        //Показ процентов и значений
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0} ({1})",
                NumberFormat.getNumberInstance(), // Формат для значения
                NumberFormat.getNumberInstance() // Формат для процентов (не используется, но должен быть)
        );
        plot.setLabelGenerator(labelGenerator);

        //контур для каждого сегмента
        plot.setSectionOutlinesVisible(true);
        plot.setOutlinePaint(Color.LIGHT_GRAY);
        plot.setOutlineStroke(new BasicStroke(1.0f));

        chart.getTitle().setFont(new Font("Arial Black", Font.BOLD, 18));
        chart.getTitle().setPaint(Color.DARK_GRAY);

    }


    private static PieDataset createDataset(List<String> labels, List<Double> values) {
        DefaultPieDataset dataset = new DefaultPieDataset();

        if (labels == null || values == null || labels.size() != values.size()) {
            throw new IllegalArgumentException("Labels and values lists must be not null and of the same size");
        }
        for (int i = 0; i < labels.size(); i++) {
            dataset.setValue(labels.get(i), values.get(i));
        }
        return dataset;
    }
}
