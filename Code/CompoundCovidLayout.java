/*
Description: creates a gui that gives detail about our names. This gui can also
load a csv file, manually enter data, create a new save file of manually put in
data. It can also, display a table, display a pie chart, and display a bar chart.
 */

import java.io.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.JFileChooser;

public class CompoundCovidLayout {

    //private variables
    private JComponent ui = null;
    private JButton about;
    private JButton load_D;
    private JButton add_D;
    private JButton save_D;
    private JButton visual_D;
    private JButton pie_D;
    private JButton bar_D;
    private JButton csv_fileButton;
    private JButton save_fileButton;
    private JButton addInfo_Button;

    private JPanel lineStart;
    private JPanel buttons_left;
    private JPanel grid;
    private JPanel labels_left;
    private JPanel labels_custom;

    private JLabel team_Number;
    private JLabel team_Member1;
    private JLabel team_Member2;
    private JLabel team_Member3;
    private JLabel team_Member4;
    private JLabel csv_File;
    private JLabel save_File;

    private JLabel addIDNumber;
    private JLabel addLastNameInfo;
    private JLabel addFirstNameInfo;
    private JLabel addVaccineTypeInfo;
    private JLabel addDateInfo;
    private JLabel addLocationInfo;

    private JTextField IDNumberTextField;
    private JTextField lastNameTextField;
    private JTextField firstNameTextField;
    private JTextField vaccineTypeTextField;
    private JTextField dateTextField;
    private JTextField locationTextField;

    private JTextField csvFileTextField;
    private JTextField saveFileTextField;

    private String csv;
    private String IDNumber;
    private String lastName;
    private String firstName;
    private String vaccineType;
    private String date;
    private String location;

    private File testfile;

    private MyTableModel1 dataTable;

    private MyBarGraph barGraph;
    CompoundCovidLayout() {
        initUI();
    }

    public void initUI() {
        if (ui!=null) return;

        ui = new JPanel(new BorderLayout(2,2));
        ui.setBorder(new TitledBorder(" "));
        ui.setPreferredSize(new Dimension(650,500));

        //center gridbaglayout
        grid = new JPanel(new GridBagLayout());
        grid.setBorder(new TitledBorder(" "));
        grid.setPreferredSize(new Dimension(0,0));
        ui.add(grid);
        labels_left = new JPanel(new GridLayout(0,1,0,0));

        labels_custom = new JPanel(new GridLayout(0,2,0,0));

        lineStart = new JPanel(new GridBagLayout());
        // will appear on the left, in a LTR text orientation locale
        ui.add(lineStart, BorderLayout.LINE_START);
        // as single component added w/no constraint, will be centered
        buttons_left = new JPanel(new GridLayout(0,1,0,0));
        lineStart.add(buttons_left);

        about = new JButton("About  ");
        buttons_left.add(about);
        load_D = new JButton("Load Data  ");
        buttons_left.add(load_D);
        add_D = new JButton("Add Data  ");
        buttons_left.add(add_D);
        save_D = new JButton("Save Data  ");
        buttons_left.add(save_D);
        visual_D = new JButton("Visualize Data");
        buttons_left.add(visual_D);

        //labels for the about button listener
        team_Number = new JLabel("Team #14");
        team_Member1 = new JLabel("Team member Daniel Arciniega");
        team_Member2 = new JLabel("Team member David Ruan");
        team_Member3 = new JLabel("Team member Kang Yi Lim");
        team_Member4 = new JLabel("Team member Kekaiolu Rodriguez");

        //label and text field for load button listener
        //create object then implement it into setTextField
        csv_File = new JLabel("Enter a filepath to a .csv file");
        csvFileTextField = new JTextField(5);

        csv_fileButton = new JButton("Load CSV File");
        csv_fileButton.addActionListener(new csvButtonListener());

        //add info to the table, but not to the csv file
        addIDNumber = new JLabel("ID Number: ");
        IDNumberTextField = new JTextField(10);

        addLastNameInfo = new JLabel("Last Name: ");
        lastNameTextField = new JTextField(10);

        addFirstNameInfo = new JLabel("First Name: ");
        firstNameTextField = new JTextField(10);

        addVaccineTypeInfo = new JLabel("Vaccine Type: ");
        vaccineTypeTextField = new JTextField(10);

        addDateInfo = new JLabel("Date: ");
        dateTextField = new JTextField(10);

        addLocationInfo = new JLabel("Location: ");
        locationTextField = new JTextField(10);

        addInfo_Button = new JButton("Add to table");
        addInfo_Button.addActionListener(new add_info_ButtonListener());


        //setting up save file buttons and labels
        save_File = new JLabel("Enter filename to save as");
        saveFileTextField = new JTextField(5);
        save_fileButton = new JButton("Save File");
        save_fileButton.addActionListener(new saveFileButtonListener());

        //connect event handler to event source
        about.addActionListener(new aboutButtonListener());
        load_D.addActionListener(new loadDButtonListener());
        add_D.addActionListener(new addButtonListener());
        save_D.addActionListener(new SaveButtonListener());

        //visual table
        visual_D.addActionListener(new visualButtonListener());
        pie_D = new JButton("Visualize Pie");
        bar_D = new JButton("Visualize bar");
        pie_D.addActionListener(new pieButtonListener());
        bar_D.addActionListener(new barButtonListener());
    }

    //private internal class that is a listener for Deposit button push(action) events.
    //also called an event handler
    public class aboutButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();

            //adds new data to the panel
            grid.add(labels_left);
            labels_left.add(team_Number);
            labels_left.add(team_Member1);
            labels_left.add(team_Member2);
            labels_left.add(team_Member3);
            labels_left.add(team_Member4);

            //adjusts the panel
            labels_left.revalidate();
            labels_left.repaint();
        }
    }
    //button listener for the load tab
    public class loadDButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();
            //adds new data to the panel
            grid.add(labels_left);
            labels_left.add(csv_File);
            labels_left.add(csvFileTextField);
            csvFileTextField.setText("");

            //adjusts the panel
            labels_left.add(csv_fileButton);
            labels_left.revalidate();

        }
    }
    //button listener that loads the csv file into the table.
    public class csvButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();
            //adds new data to the panel
            csv = csvFileTextField.getText();
            MyTableModel1 table1 = null;
            try {
                if (dataTable == null) {
                    table1 = new MyTableModel1(csv);
                    dataTable = table1;
                }
                else
                {
                    File f = new File(csv);
                    InputStream is = new FileInputStream(f);
                    dataTable.insertData(is);
                    testfile = f;
                }
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            labels_left.add(dataTable.scroll);

            labels_left.revalidate();


        }

    }
    //button listener for the add tab
    public class addButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();

            //adds new data to the panel and create button object
            grid.add(labels_custom);

            addInfo_Button = new JButton("Add to table");
            addInfo_Button.addActionListener(new add_info_ButtonListener());

            labels_custom.add(addIDNumber);
            labels_custom.add(IDNumberTextField);
            labels_custom.add(addLastNameInfo);
            labels_custom.add(lastNameTextField);
            labels_custom.add(addFirstNameInfo);
            labels_custom.add(firstNameTextField);
            labels_custom.add(addVaccineTypeInfo);
            labels_custom.add(vaccineTypeTextField);
            labels_custom.add(addDateInfo);
            labels_custom.add(dateTextField);
            labels_custom.add(addLocationInfo);
            labels_custom.add(locationTextField);
            labels_custom.add(addInfo_Button);
            labels_custom.revalidate();
            labels_custom.repaint();

        }
    }
    //button listener that adds the user input data in to table.
    public class add_info_ButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //retrieves data from panel
            IDNumber =  IDNumberTextField.getText();
            lastName = lastNameTextField.getText();
            firstName = firstNameTextField.getText();
            vaccineType = vaccineTypeTextField.getText();
            date = dateTextField.getText();
            location = locationTextField.getText();
            //adds new data from panel to model.
            dataTable.addToModel(IDNumber, lastName, firstName, vaccineType, date, location);

            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();

            //adds data to the panel
            if (dataTable != null) {
                labels_left.add(dataTable.scroll);
            }
            //restores the panel
            labels_left.revalidate();
        }

    }
    //button listener that goes to the save tab
    public class SaveButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();
            //adds new data to the panel
            grid.add(labels_left);
            labels_left.add(save_File);
            labels_left.add(saveFileTextField);
            saveFileTextField.setText("");
            //adds new data to the panel
            labels_left.add(save_fileButton);
            //restores the panel
            labels_left.revalidate();
        }
    }
    //button listener that creates a save file of the updated table
    public class saveFileButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //gets data from textbox & saves the data to a string
            String filename = saveFileTextField.getText();
            JFileChooser fileChooser = new JFileChooser();
            File filetosave = new File(filename); //saves file
            fileChooser.setSelectedFile(filetosave);
            fileChooser.setDialogTitle("Save file to location");
            int userSelection = fileChooser.showSaveDialog(null);
            BufferedWriter writer;
            System.out.println(userSelection);
            System.out.println(fileChooser.getSelectedFile());
            if (userSelection == JFileChooser.APPROVE_OPTION)
            {
                try {
                    writer = new BufferedWriter(new FileWriter(fileChooser.getSelectedFile()));
                    //writer.write("Try me");
                    //Now convert the table data into a csv, or have the table return that at least
                    String csvDataTable = dataTable.tableToString();
                    writer.write(csvDataTable);
                    writer.close();

                    //File filetosave = fileChooser.getSelectedFile();
                    System.out.println("Save as file " + filetosave.getAbsolutePath());
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
            else
            {
                System.out.println("Save command cancelled");
            }
        }

    }
    //button listener for the visual tab
    public class visualButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();

            if (dataTable != null) {
                labels_left.add(dataTable.scroll);
            }

            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();

            //adds data to panel
            labels_left.add(bar_D);
            labels_left.add(pie_D);
            labels_left.revalidate();
        }
    }
    // button listener that creates a jframe of the pie chart
    public class pieButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from the panel
            labels_left.removeAll();
            labels_custom.removeAll();

            if (dataTable != null) {
                labels_left.add(dataTable.scroll);
                dataTable.initializeArrays();
            }
            //creates piechart object and sets size of piechart and implements table
            MyPieChart pie = new MyPieChart("Pie Chart Example");
            pie.generateBarChart(dataTable);
            pie.setSize(900, 600);
            pie.setLocationRelativeTo(null);
            pie.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            pie.setVisible(true);

            labels_left.revalidate();
        }
    }
    // button listener that creates a jframe of the bar chart
    public class barButtonListener implements ActionListener
    {
        public void actionPerformed (ActionEvent event)
        {
            //removes data from panel
            labels_left.removeAll();
            labels_custom.removeAll();

            //adds data to panel
            if (dataTable != null) {
                labels_left.add(dataTable.scroll);
                dataTable.initializeArrays();
            }
            //creates chart object and sets size of bargraph and implements table
            MyBarGraph chart=new MyBarGraph("");
            chart.generateBarChart(dataTable);
            chart.setSize(800, 400);
            chart.setLocationRelativeTo(null);
            chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            chart.setVisible(true);

            //restores
            labels_left.revalidate();
        }
    }
        public JComponent getUI() {
        return ui;
    }

    public static void main(String[] args) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception useDefault) {
                }
                CompoundCovidLayout o = new CompoundCovidLayout();

                JFrame f = new JFrame(o.getClass().getSimpleName());
                f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                f.setLocationByPlatform(true);

                f.setContentPane(o.getUI());
                f.pack();
                f.setMinimumSize(f.getSize());

                f.setVisible(true);
            }
        };
        SwingUtilities.invokeLater(r);
    }
}
