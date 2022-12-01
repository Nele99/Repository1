public class Test {

    public void Test() {


        Employee emp1 = new Employee("Marko", "Zizic", "Software", 2);
        Employee emp2 = new Employee("Evdin", "Mandzukic", "Tech support", 2);
        Employee emp3 = new Employee("Lara", "Croft", "Testing", 2);
        Employee emp4 = new Employee("Stepher", "Wurst", "Customer relations", 2);

        emp1.addManualCompany(new Company("Uhura", "2001-01-01", "2002-01-01"));
        emp1.addManualCompany(new Company("B1", "2005-04-04", "2008-04-04"));

        emp2.addManualCompany(new Company("Amplitudo", "2011-01-02", "2013-06-23"));
        emp2.addManualCompany(new Company("CodeIo", "2014-05-07", "2015-08-01"));

        emp3.addManualCompany(new Company("CoreIt", "2018-01-15", "2021-02-03"));
        emp3.addManualCompany(new Company("Codingo", "2001-05-11", "2003-09-10"));

        emp4.addManualCompany(new Company("Smart Tech", "1998-03-08", "2010-09-21"));
        emp4.addManualCompany(new Company("MaxCod", "2020-06-02", "2022-01-01"));

        Company mainComp = new Company("SynergySuite", 50, 1);

        mainComp.addEmployee(emp1);
        mainComp.addEmployee(emp2);
        mainComp.addEmployee(emp3);
        mainComp.addEmployee(emp4);

        System.out.println("Choose an option,\n ");

        System.out.println("See all employees: - 1");
        System.out.println("Insert new Employee: - 2");

        int choice = Utility.inputInt();

        while(true){
         if(Utility.choiceControl(choice)){
             break;
         }
             System.err.println("Invalid choice try again");
             choice = Utility.inputInt();
        }

        if(choice == 1){
            mainComp.updateYearsOfWork();
            mainComp.updateVacationDays();
            mainComp.showMainComp();

        }

        System.out.println("Insert name: ");
        String name = Utility.inputString();

        System.out.println("Insert surname");
        String surname = Utility.inputString();

        System.out.println("Add role in the company");
        String role = Utility.inputString();

        System.out.println("Enter the number of companies employee worked for in the past");
        int numPastCompanies = Utility.inputInt();

        Employee e1 = new Employee(name, surname, role, numPastCompanies);

        e1.addPastCompany(numPastCompanies);

        mainComp.addEmployee(e1);

        mainComp.updateYearsOfWork();
        mainComp.updateVacationDays();

        mainComp.showMainComp();

        // emp1.showPastCompanies();


    }
}


