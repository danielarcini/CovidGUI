/*
Description: Class extension of CompouundCovidLayout which creates a pie chart gui once
the Pie chart button listener is clicked.
*/

import java.text.DecimalFormat;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;
import org.jfree.data.general.PieDataset;
public class MyPieChart extends JFrame {

    private MyTableModel1 inputTable;
    private static final long serialVersionUID = 6294689542092367723L;

    public MyPieChart(String title) {
        super(title);
    }
    void generateBarChart(MyTableModel1 inputTable)
    {
        this.inputTable = inputTable;
        //System.out.println(inputTable.tableToString());

        // Create dataset
        PieDataset dataset = createDataset();

        // Create chart
        JFreeChart chart = ChartFactory.createPieChart(
                "Covid vaccinations per Location",
                dataset,
                true,
                true,
                false);

        //Format Label
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator(
                "{0} : ({2})", new DecimalFormat("0"), new DecimalFormat("0%"));
        ((PiePlot) chart.getPlot()).setLabelGenerator(labelGenerator);

        // Create Panel
        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);

    }

    private PieDataset createDataset() {

        DefaultPieDataset dataset=new DefaultPieDataset();

        for(int i = 0; i< inputTable.getArrlength(); i++){
            dataset.setValue(inputTable.getsarr(i),inputTable.getiarr(i));
        }

        return dataset;
    }
}