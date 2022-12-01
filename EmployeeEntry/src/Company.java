import java.text.DecimalFormat;
import java.time.LocalDate;

public class Company {

	private String name;
	private Employee[] employees;
	private int companyMaximum;
	private int bonusVacationDays;
	private LocalDate startDate;
	private LocalDate endDate;

	public Company(String name, int companyMaximum, int bonusVacationDays){
		super();
		this.name = name;
		this.employees = new Employee[companyMaximum];
		this.companyMaximum = companyMaximum;
		this.bonusVacationDays = bonusVacationDays;
	}

	public Company(String name, String start, String end) {
		super();
		this.name = name;
		this.startDate = LocalDate.parse(start);
		this.endDate = LocalDate.parse(end);
	}

	public Company(String name, LocalDate startDate, LocalDate endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
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

	@Override
	public String toString() {

		DecimalFormat df = new DecimalFormat("0.00");

		return name + ", startDate: " + startDate + ", endDate: " + endDate + ", years: "
				+ df.format(Utility.calculateBetweenDates(startDate, endDate));
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
