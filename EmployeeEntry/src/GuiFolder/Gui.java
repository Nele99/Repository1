package GuiFolder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Gui extends JFrame implements ActionListener {

    JRadioButton inputEmployee;
    JRadioButton showAllEmployees;
    static String employeeInfo = "";
    static int numPastCompanies = 0;


    public Gui() {
        this.setTitle("Employee Entry");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.setSize(500, 100);
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

    public static void showAllInfo(String newInfo) {

        String[][] data = new String[20][5];
        String[] columnNames = {"Name", "Surname", "Role", "Experience", "Vacation days"};

        if (newInfo.isEmpty()) {
            try (BufferedReader b = new BufferedReader(new FileReader("src/GuiFolder/EmployeeData"))) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        data[i][j] = b.readLine();
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found: file.txt");
            } catch (IOException e) {
                System.out.println("Error reading file: file.txt");
            }
        } else {
           // newInfo = newInfo + "/aa/aaa";
            String[] newEmployee = newInfo.split("/");

            try (BufferedReader b = new BufferedReader(new FileReader("src/GuiFolder/EmployeeData"))) {
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 5; j++) {
                        data[i][j] = b.readLine();
                    }
                }

            } catch (FileNotFoundException e) {
                System.out.println("File not found: file.txt");
            } catch (IOException e) {
                System.out.println("Error reading file: file.txt");
            }

            boolean emptyRowFound = false;

            for (int i = 0; i < 20 && !emptyRowFound; i++) {
                for (int j = 0; j < 5; j++) {
                    if (data[i][j] == null) {
                        data[i][j] = newEmployee[j];
                        if (j == 4){
                            emptyRowFound = true;
                            break;
                        }
                    }
                }
            }
        }

        JTable table = new JTable(data, columnNames);
        JFrame frame = new JFrame("Employee Information");
        frame.add(new JScrollPane(table));
        frame.setSize(500, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    public static void pastCompanyWindow() {

        JPanel panel = new JPanel(new GridLayout(4, 2, 5, 5));
        JFrame frame = new JFrame("Add past company");
        JLabel companyLabel = new JLabel("Company ");
        JLabel startLabel = new JLabel("Start date");
        JLabel endLabel = new JLabel("End date");
        JButton submit = new JButton("Continue");
        JTextField pastCompanyName = new JTextField();
        JTextField startDate = new JTextField();
        JTextField endDate = new JTextField();

        frame.setResizable(false);
        frame.setVisible(true);
        frame.setSize(500, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.add(panel);

        panel.add(companyLabel);
        panel.add(pastCompanyName);
        panel.add(startLabel);
        panel.add(startDate);
        panel.add(endLabel);
        panel.add(endDate);
        panel.add(submit);

        startDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if (Character.isAlphabetic(input)) {
                    e.consume();
                }
            }
        });

        endDate.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if (Character.isAlphabetic(input)) {
                    e.consume();
                }
            }
        });


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String companyName = pastCompanyName.getText();
                String start = startDate.getText();
                String end = endDate.getText();

                if (companyName.isEmpty() || start.isEmpty() || end.isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields required");
                } else if (!Utility.inputDate(start)) {
                    JOptionPane.showMessageDialog(panel, "Invalid start date, format example: 2001-01-01");
                } else if (!Utility.inputDate(end)) {
                    JOptionPane.showMessageDialog(panel, "Invalid end date, format example: 2001-01-01");
                } else if (!Utility.checkDatesValid(start, end)) {
                    JOptionPane.showMessageDialog(panel, "Invalid end or start date, try again");
                } else if (!Utility.correctDateRange(start, end)) {
                    JOptionPane.showMessageDialog(panel, "Invalid date range, already exists");
                }else{
                    Utility.addCount(1);
                    Utility.addDate(start,end);
                    if(Utility.getCounter() != numPastCompanies){
                        pastCompanyWindow();
                        frame.dispose();
                    }else {
                        frame.dispose();
                        Gui.showAllInfo(Gui.getEmployeeInfo() + "/" +
                                Utility.allYears() + "/" + Utility.vacationDays(Utility.allYears()));
                    }
                }
            }
        });
    }


    public static void inputNameSurname() {

        JPanel panel = new JPanel(new GridLayout(5, 2, 10, 5));

        JFrame frame = new JFrame();

        JLabel name = new JLabel("Name");
        JLabel surname = new JLabel("Surname");
        JLabel pastCompaniesNum = new JLabel("Number of past companies");
        JLabel role = new JLabel("Role");
        JButton submit = new JButton("Continue");

        JTextField newNameEntry = new JTextField();
        JTextField newSurnameEntry = new JTextField();
        JTextField newPastCompanies = new JTextField();
        JTextField newRole = new JTextField();

        frame.add(panel);
        panel.add(name);
        panel.add(newNameEntry);
        panel.add(surname);
        panel.add(newSurnameEntry);
        panel.add(pastCompaniesNum);
        panel.add(newPastCompanies);
        panel.add(role);
        panel.add(newRole);
        panel.add(submit);

        newPastCompanies.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                if (Character.isLetter(input)) {
                    e.consume();
                }
            }
        });


        submit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (newNameEntry.getText().isEmpty()
                        || newSurnameEntry.getText().isEmpty()
                        || newPastCompanies.getText().isEmpty()
                        || newRole.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(panel, "All fields required");

                } else if (newNameEntry.getText().length() < 3
                        || newSurnameEntry.getText().length() < 3) {
                    JOptionPane.showMessageDialog(panel, "Name and surname must be 3 characters long");
                } else {
                    employeeInfo = newNameEntry.getText() + "/"
                            + newSurnameEntry.getText() + "/"
                            + newRole.getText();
                    setEmployeeInfo(employeeInfo);
                    setNumPastCompanies(Integer.parseInt(newPastCompanies.getText()));

                    frame.dispose();
                    Gui.pastCompanyWindow();
                }
            }
        });

        frame.setResizable(false);
        frame.setTitle("Input Employee");
        frame.setSize(500, 200);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Color.LIGHT_GRAY);
        frame.setLocationRelativeTo(null);


    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inputEmployee) {
            dispose();
            Gui.inputNameSurname();
        } else {
            dispose();
            Gui.showAllInfo(Gui.getEmployeeInfo());
        }
    }

    public static void setEmployeeInfo(String employeeInfo) {
        Gui.employeeInfo = employeeInfo;
    }

    public static String getEmployeeInfo() {
        return employeeInfo;
    }

    public static int getNumPastCompanies() {
        return numPastCompanies;
    }

    public static void setNumPastCompanies(int numPastCompanies) {
        Gui.numPastCompanies = numPastCompanies;
    }
}

