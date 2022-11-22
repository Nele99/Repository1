import java.util.Arrays;

public class Company {

	private String name;
	private Employee[] employees;
	private int companyMaximum;
	private int bonusVacationDays;

	public Company(String name, int companyMaximum, int bonusVacationDays) {
		super();
		this.name = name;
		this.employees = new Employee[companyMaximum];
		this.companyMaximum = companyMaximum;
		this.bonusVacationDays = bonusVacationDays;
	}

	public void addEmployee(Employee e1) {

		for (int i = 0; i < employees.length; i++) {
			if (employees[i] == null) {
				employees[i] = e1;
				return;
			}
		}

	}

	public void updateVacationDays() {
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				for (int j = 5; j <= 50; j += 5) {
					if (employees[i].getYearsOfWork() > j) {
						employees[i].setVacationDays(employees[i].getVacationDays() + 1);
					}

				}

				employees[i].setVacationDays(employees[i].getVacationDays() + bonusVacationDays);
			}
		}

	}

	public void updateYearsOfWork() {
		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				employees[i].setYearsOfWork(employees[i].getYearsOfWork() + employees[i].calculateExperience());
			}
		}
	}

	public void showMainComp() {

		for (int i = 0; i < employees.length; i++) {
			if (employees[i] != null) {
				System.out.println(employees[i].toString());
			}
		}

	}

}
