package GuiFolder;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Gui extends JFrame implements ActionListener {

    JRadioButton inputEmployee;
    JRadioButton showAllEmployees;

    public Gui() {
        this.setTitle("Employee Entry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 200);
        this.setLayout(new FlowLayout());
        this.setLocationRelativeTo(null);
        inputEmployee = new JRadioButton("Input employee");
        showAllEmployees = new JRadioButton("Show all employees");


        ButtonGroup group = new ButtonGroup();
        group.add(inputEmployee);
        group.add(showAllEmployees);

        inputEmployee.addActionListener(this);
        showAllEmployees.addActionListener(this);

        this.add(inputEmployee);
        this.add(showAllEmployees);
    }

    public static void showAllInfo() {

        String[][] data = {
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
                {"Nebojsa", "Nedic", "15", "Dev-ops", "6.87", "22"},
                {"Marko", "Markovic", "25", "QA", "4", "21"},
                {"Nikolina", "Nikolic", "35", "Testing", "1.2", "20"},
        };

        String[] columnNames = {"Name", "Surname", "Age", "Role", "Experience", "Vacation days"};

        JTable table = new JTable(data, columnNames);


        JFrame frame = new JFrame("Employee Information");

        frame.add(new JScrollPane(table));

        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);


    }

    public static void pastCompanyWindow() {

        JPanel panel = new JPanel(new GridLayout(3, 2, 5, 5));

        JFrame frame = new JFrame("Past Company entry");
        JLabel pastCompanyName = new JLabel("Past company");
        JLabel startDate = new JLabel("Start date");
        JLabel endDate = new JLabel("End date");

        JTextField newPastCompanyName = new JTextField();
        JTextField newStartDate = new JTextField();
        JTextField newEndDate = new JTextField();

        org.jdatepicker.util.JDatePickerUtil.getMediumDateFormat();

        frame.setVisible(true);
        frame.setSize(500,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        frame.add(panel);
        panel.add(pastCompanyName);
        panel.add(newPastCompanyName);
        panel.add(startDate);
        panel.add(endDate);
    }

    public static Object[] inputNameSurname() {

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));

        JFrame frame = new JFrame();

        JLabel name = new JLabel("Name");
        JLabel surname = new JLabel("Surname");
        JLabel pastCompaniesNum = new JLabel("Number of past companies");


        JTextField newNameEntry = new JTextField();
        JTextField newSurnameEntry = new JTextField();
        JTextField newPastCompanies = new JTextField();


        newPastCompanies.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Gui.pastCompanyWindow();
            }
        });

        frame.add(panel);
        panel.add(name);
        panel.add(newNameEntry);
        panel.add(surname);
        panel.add(newSurnameEntry);
        panel.add(pastCompaniesNum);
        panel.add(newPastCompanies);


        frame.setTitle("Input Employee");
        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);

        Object[] array = new Object[3];
        array[0] = newNameEntry;
        array[1] = newNameEntry;
        array[2] = newNameEntry;


        return array;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputEmployee) {
            dispose();
            Gui.inputNameSurname();
        } else {
            dispose();
            Gui.showAllInfo();
        }
    }
}
