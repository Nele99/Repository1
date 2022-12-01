import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.Scanner;

public class Employee {

    private String name;
    private String surname;
    private String role;
    private Company[] pastCompanies;
    private double yearsOfWork = 0;
    private int vacationDays = 20;

    public Employee(String name, String surname, String role, int numOfPastCompanies) {
        super();
        this.name = name;
        this.surname = surname;
        this.role = role;
        this.pastCompanies = new Company[numOfPastCompanies];
    }

    public void addManualCompany(Company p1) {
        for (int i = 0; i < pastCompanies.length; i++) {
            if (pastCompanies[i] == null) {
                pastCompanies[i] = p1;
                return;
            }
        }
    }

    public boolean checkDuplicateCompanyName(String newName) {

        for (int i = 0; i < pastCompanies.length; i++) {
            if (pastCompanies[i] != null) {
                if (pastCompanies[i].getName().equalsIgnoreCase(newName)) {
                    return true;
                }
            }
        }

        return false;

    }

    public boolean addPastCompany(int newArraySize) {
        Scanner input = new Scanner(System.in);

        if (newArraySize == 0) {
            System.out.println("No past experience");
            return false;
        } else {

            int[] array = new int[newArraySize];

            for (int i = 0; i < array.length; i++) {

                System.out.println("Company name number " + (i + 1));
                String compName = Utility.inputString();

                if (Utility.isDigits(compName) == true) {
                    System.err.println("Company name can't be digits only");
                    i--;
                    continue;
                }

                if (checkDuplicateCompanyName(compName)) {
                    System.err.println("Company with that name already exists , try again");
                    i--;
                    continue;
                }

                System.out.println("Add start date");
                LocalDate start = Utility.inputDate();

                while (true) {
                    if (start.isAfter(LocalDate.now())) {
                        System.err.println("Start date is invalid,try again");
                        start = Utility.inputDate();
                    } else {
                        break;
                    }
                }

                System.out.println("Add end date");
                LocalDate end = Utility.inputDate();

                while (true) {
                    if (end.isBefore(start) || end.isAfter(LocalDate.now())) {
                        System.err.println("End date is invalid,try again");
                        end = Utility.inputDate();
                    } else {
                        break;
                    }
                }


                Company pc = new Company(compName, start, end);

                if (isDateValid(pc)) {
                    addPastCompanyDatePeriod(pc);
                } else {
                    System.err.println("Date period is invalid,already exists,try again");
                    i--;
                    continue;
                }

            }
            return true;
        }

    }


    public void addPastCompanyDatePeriod(Company p1) {
        for (int i = 0; i < pastCompanies.length; i++) {
            if (pastCompanies[i] == null) {
                pastCompanies[i] = p1;
                return;
            }
        }
    }

    public boolean isDateValid(Company p1) {

        for (int i = 0; i < pastCompanies.length; i++) {

            if (pastCompanies[i] == null && i == 0) {
                return true;
            }

            if (pastCompanies[i] != null) {
                if ((p1.getStartDate().isAfter(pastCompanies[i].getEndDate()) && p1.getEndDate().isAfter(pastCompanies[i].getEndDate()))
                        || (p1.getStartDate().isBefore(pastCompanies[i].getStartDate()) && p1.getEndDate().isBefore(pastCompanies[i].getStartDate()))) {
                    return true;
                }
            }
        }

        return false;

    }

    public double calculateExperience() {

        double years = 0;
        double months = 0;
        double days = 0;

        for (int i = 0; i < pastCompanies.length; i++) {

            days += Period.between(pastCompanies[i].getStartDate(), pastCompanies[i].getEndDate()).getDays();
            months += Period.between(pastCompanies[i].getStartDate(), pastCompanies[i].getEndDate()).getMonths();
            years += Period.between(pastCompanies[i].getStartDate(), pastCompanies[i].getEndDate()).getYears();

        }
        return years + months / 12 + days / 31;
    }

    public void showPastCompanies() {

        for (int i = 0; i < pastCompanies.length; i++) {
            if (pastCompanies[i] != null) {
                System.out.println(pastCompanies[i]);
            }
        }

    }

    public int getVacationDays() {
        return vacationDays;
    }

    public void setVacationDays(int vacationDays) {
        this.vacationDays = vacationDays;
    }

    public double getYearsOfWork() {
        return yearsOfWork;
    }

    public void setYearsOfWork(double yearsOfWork) {
        this.yearsOfWork = yearsOfWork;
    }

    public Company[] getPastCompanies() {
        return pastCompanies;
    }

    public void setPastCompanies(Company[] pastCompanies) {
        this.pastCompanies = pastCompanies;
    }

    @Override
    public String toString() {

        DecimalFormat df = new DecimalFormat("0.00");

        String s = "Employee: " + name.toUpperCase() + " " + surname.toUpperCase() + "\nRole: " + role
                + "\nYearsOfWork: " + df.format(yearsOfWork) + "\nVacationDays: " + vacationDays + "\nPastCompanies:"
                + "\n";

        if (pastCompanies.length == 0) {
            s += "-----No experience-----" + "\n";
            return s;
        }

        for (int i = 0; i < pastCompanies.length; i++) {
            if (pastCompanies[i] != null) {
                s += pastCompanies[i] + "\n";
            }
        }

        return s;

    }
}
