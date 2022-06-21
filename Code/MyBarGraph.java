import javax.swing.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

public class MyBarGraph extends JFrame {

    private MyTableModel1 inputTable;
    private static final long serialVersionUID = 1L;

    public MyBarGraph(String appTitle) {
        super(appTitle);

    }

    void generateBarChart(MyTableModel1 inputTable)
    {
        this.inputTable = inputTable;

        // Create Dataset
        CategoryDataset dataset = createDataset();

        //Create chart
        JFreeChart chart=ChartFactory.createBarChart(
                "Covid Vaccine", //Chart Title
                "Vaccine", // Category axis
                "Number of Vaccinations", // Value axis
                dataset,
                PlotOrientation.VERTICAL,
                true,true,false
        );

        ChartPanel panel=new ChartPanel(chart);
        setContentPane(panel);
    }

    private CategoryDataset createDataset() {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        dataset.addValue(inputTable.getPfizer(), "Pfizer","");
        dataset.addValue(inputTable.getModerna(), "Moderna", "");
        dataset.addValue(inputTable.getNovavax(), "Novavax", "");
        dataset.addValue(inputTable.getSinovac(), "Sinovac", "");
        dataset.addValue(inputTable.getJohnsonJohnson(), "Johnson&Johnson", "");
        dataset.addValue(inputTable.getAstraZeneca(), "AstraZeneca", "");

        return dataset;
    }
}