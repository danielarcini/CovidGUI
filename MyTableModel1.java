/*
Description: creates the table model which can load a csv file, and add user given input
data into the model.
 */

import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MyTableModel1 extends JPanel {

    //variables
    JTable table;
    DefaultTableModel model;
    JScrollPane scroll;
    private int pfizer;
    private int moderna;
    private int Novavax;
    private int Sinovac;
    private int JohnsonJohnson;
    private int AstraZeneca;
    private int arrlen;
    private int iarr[];
    private String []sarr;
    /**
     * Takes data from a CSV file and places it into a table for display.
     *
     * @param source - a reference to the file where the CSV data is located.
     */
    //constructor
    public MyTableModel1(String source) throws FileNotFoundException {
        table = new JTable();
        scroll = new JScrollPane(table);
        model = new DefaultTableModel(0, 6);
        arrlen = 0;
        //file reader
        InputStream is;
        File f = new File(source);
        is = new FileInputStream(f);

        insertData(is);//table.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer());

    }

    //table.getColumnModel().getColumn(0).setCellRenderer(new CustomCellRenderer());
    void insertData(InputStream is) {
        Scanner scan = new Scanner(is);
        String[] row;
        String[] col;
        String line = scan.nextLine(); //first line of file contains the columns

        if (line.contains(","))
            col = line.split(",");
        else
            col = line.split("\t");
        Object[] info = new Object[col.length];
        for (int i = 0; i < col.length; i++){
            info[i] = col[i];}
        model.setColumnIdentifiers(info);

        int j = 0;
        while (scan.hasNextLine()) {
            line = scan.nextLine();
            if (line.contains(","))
                row = line.split(",");
            else
                row = line.split("\t");
            Object[] data = new Object[row.length];
            for (int i = 0; i < row.length; i++) {
                if(row[i].equals("Pfizer")){
                    pfizer++;
                }
                if(row[i].equals("Moderna")){
                    moderna++;
                }
                if(row[i].equals("Novavax")){
                    Novavax++;
                }
                if(row[i].equals("Sinovac")){
                    Sinovac++;
                }
                if(row[i].equals("Johnson&Johnson")){
                    JohnsonJohnson++;
                }
                if(row[i].equals("AstraZeneca")){
                    AstraZeneca++;
                }
                data[i] = row[i];
            }

            arrlen++;
            model.addRow(data);
        }
        this.initializeArrays();
        /*//initialies the arrays
        sarr = new String[arrlen];
        iarr = new int[arrlen];
        //stores column 5 from each row into the array.
        for(int i = 0; i < model.getRowCount();i++){
            sarr[i] = (String) model.getValueAt(i,5);
            iarr[i] = 1;
        }

        //creates a  temp array and adds the count of the vaccines for the same location together
        String [] stemp = new String[arrlen];
        int []itemp = new int[arrlen];
        for (int i = 0; i < sarr.length; i++){
            int add = 0;
            for(j = 0; j< sarr.length; j++){
                if(sarr[i].equals(sarr[j])){
                    add++;
                    stemp[i] = sarr[i];
                    itemp[i] = add;
                }
            }
        }
        sarr = stemp;
        iarr = itemp;
        arrlen = iarr.length;
        */
        table.setModel(model);
    }
    void initializeArrays()
    {
        //initialies the arrays
        sarr = new String[arrlen];
        iarr = new int[arrlen];
        //stores column 5 from each row into the array.
        for(int i = 0; i < model.getRowCount();i++){
            sarr[i] = (String) model.getValueAt(i,5);
            iarr[i] = 1;
        }

        //creates a  temp array and adds the count of the vaccines for the same location together
        String [] stemp = new String[arrlen];
        int []itemp = new int[arrlen];
        for (int i = 0; i < sarr.length; i++){
            int add = 0;
            for(int j = 0; j< sarr.length; j++){
                if(sarr[i].equals(sarr[j])){
                    add++;
                    stemp[i] = sarr[i];
                    itemp[i] = add;
                }
            }
        }
        sarr = stemp;
        iarr = itemp;
        arrlen = iarr.length;
    }
    //adds user input data into table model
    void addToModel(String inID, String lastName, String firstName, String vacchineType, String inDate, String inLocation)
    {
        String [] data = new String[6];
        data[0] = inID;
        data[1] = lastName;
        data[2] = firstName;
        data[3] = vacchineType;
        data[4] = inDate;
        data[5] = inLocation;
        if(vacchineType.equals("Pfizer")){
            pfizer++;
        }
        if(vacchineType.equals("Moderna")){
            moderna++;
        }
        if(vacchineType.equals("Novavax")){
            Novavax++;
        }
        if(vacchineType.equals("Sinovac")){
            Sinovac++;
        }
        if(vacchineType.equals("Johnson&Johnson")){
            JohnsonJohnson++;
        }
        if(vacchineType.equals("AstraZeneca")){
            AstraZeneca++;
        }
        arrlen++;
        model.addRow(data);
        this.initializeArrays();
    }

    String tableToString()
    {
        String returning = "";
        //Set up CSV table header names
        for (int i = 0; i < model.getColumnCount(); i++)
        {
            returning += model.getColumnName(i);
            if (i < model.getColumnCount() - 1)
            {
                returning += ",";
            }
            else
            {
                returning += '\n';
            }
        }
        for (int i = 0; i < model.getRowCount(); i++)
        {
            for (int j = 0; j < model.getColumnCount(); j++)
            {
                returning += model.getValueAt(i, j);
                if (j < model.getColumnCount() - 1)
                {
                    returning += ",";
                }
                else
                {
                    returning += '\n';
                }
            }
        }

        System.out.println(returning);
        return returning;
    }
    //getter methods for variables
    JScrollPane getscroll(){

        return scroll;

    }
    int getPfizer(){
        return pfizer;
    }
    int getModerna(){
        return moderna;
    }
    int getNovavax(){
        return Novavax;
    }
    int getSinovac(){
        return Sinovac;
    }
    int getJohnsonJohnson(){

        return JohnsonJohnson;
    }
    int getAstraZeneca(){

        return AstraZeneca;
    }

    int getiarr(int i){

        return iarr[i];
    }
    String getsarr(int i){

        return sarr[i];
    }
    int getArrlength(){

        return arrlen;
    }

}
