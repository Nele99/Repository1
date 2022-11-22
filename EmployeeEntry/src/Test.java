import java.time.LocalDate;
import java.time.Period;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Test {

	public void Test() {

		Employee emp1 = new Employee("Marko", "Zizic", "Software", 2);
		Employee emp2 = new Employee("Evdin", "Mandzukic", "Tech support", 2);
		Employee emp3 = new Employee("Lara", "Croft", "Testing", 2);
		Employee emp4 = new Employee("Stepher", "Wurst", "Customer relations", 2);

		emp1.addManulaPastCompany(new PastCompany("Uhura", "2001-01-01", "2002-01-01"));
		emp1.addManulaPastCompany(new PastCompany("B1", "2005-04-04", "2008-04-04"));

		emp2.addManulaPastCompany(new PastCompany("Amplitudo", "2011-01-02", "2013-06-23"));
		emp2.addManulaPastCompany(new PastCompany("CodeIo", "2014-05-07", "2015-08-01"));

		emp3.addManulaPastCompany(new PastCompany("CoreIt", "2018-01-15", "2021-02-03"));
		emp3.addManulaPastCompany(new PastCompany("Codingo", "2001-05-11", "2003-09-10"));

		emp4.addManulaPastCompany(new PastCompany("Smart Tech", "1998-03-08", "2010-09-21"));
		emp4.addManulaPastCompany(new PastCompany("MaxCod", "2020-06-02", "2022-01-01"));

		Company mainComp = new Company("SynergySuite", 50, 1);

		mainComp.addEmployee(emp1);
		mainComp.addEmployee(emp2);
		mainComp.addEmployee(emp3);
		mainComp.addEmployee(emp4);

		System.out.println("Insert new Employee");

		System.out.println("Insert name: ");
		String name = Employee.inputString();

		System.out.println("Insert surname");
		String surname = Employee.inputString();

		System.out.println("Add role in the company");
		String role = Employee.inputString();

		System.out.println("Enter the number of companies employee worked for in the past");
		int numPastCompanies = Employee.inputInt();

		Employee e1 = new Employee(name, surname, role, numPastCompanies);

		e1.addPastCompany(numPastCompanies);

		mainComp.addEmployee(e1);

		mainComp.updateYearsOfWork();
		mainComp.updateVacationDays();

		mainComp.showMainComp();

		// emp1.showPastCompanies();
		
		

	}

}
