
import java.util.Arrays;
import java.util.Scanner;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Employee {

	private String name;
	private String surname;
	private String role;
	private PastCompany[] pastCompanies;
	private double yearsOfWork = 0;
	private int vacationDays = 20;

	public Employee(String name, String surname, String role, int numOfPastCompanies) {
		super();
		this.name = name;
		this.surname = surname;
		this.role = role;
		this.pastCompanies = new PastCompany[numOfPastCompanies];
	}

	public void addManulaPastCompany(PastCompany p1) {
		for (int i = 0; i < pastCompanies.length; i++) {
			if (pastCompanies[i] == null) {
				pastCompanies[i] = p1;
				return;
			}
		}
	}

	public static boolean isDigits(String check) {

		char[] array = check.toCharArray();

		for (int i = 0; i < array.length; i++) {
			if (Character.isDigit(array[i])) {
				return true;
			}
		}

		return false;

	}

	public static String inputString() {
		Scanner input = new Scanner(System.in);
		while (true) {
			try {
				String newInput = input.nextLine();
				if (newInput.length() <= 3 || newInput == null) {
					throw new InvalidInputException("Invalid input, can't be null , 4 characters minimum");
				} else if (isDigits(newInput) == true) {
					throw new InvalidInputException("Invalid input,contains numbers");
				} else {

					return newInput;
				}
			} catch (java.util.InputMismatchException e) {
				System.err.println(e.getMessage());
				continue;
			} catch (InvalidInputException e) {
				System.err.println(e.getMessage());
				continue;
			}
		}
	}

	public static int inputInt() {
		Scanner input = new Scanner(System.in);

		while (true) {

			while (!input.hasNextInt()) {
				System.err.println("Invalid value,it must be a integer");
				input = new Scanner(System.in);
			}
			try {
				int newInput = input.nextInt();
				if (newInput < 0) {
					throw new InvalidInputException(
							"Invalid input, number of companies must be more 0 or more than 0!");
				} else {
					return newInput;
				}
			} catch (java.util.InputMismatchException e) {
				System.err.println(e.getMessage());
				continue;
			} catch (InvalidInputException e) {
				System.err.println(e.getMessage());
				continue;
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
		LocalDate nowDate = LocalDate.now();

		if (newArraySize == 0) {
			System.out.println("No past experience");
			return false;
		} else {

			int[] array = new int[newArraySize];

			for (int i = 0; i < array.length; i++) {

				System.out.println("Company name number " + (i + 1));
				String compName = Employee.inputString();

				if (checkDuplicateCompanyName(compName)) {
					System.err.println("Company with that name already exists , try again");
					i--;
					continue;
				}

				System.out.println("Add start date in uuuu-MM-dd format");
				LocalDate start = Employee.inputDate();

				System.out.println("Add end date in uuuu-MM-dd format");
				LocalDate end = Employee.inputDate();

				if (start.isAfter(nowDate)) {
					System.err.println("Start date is invalid,try again");
					i--;
					continue;
				} else if (end.isBefore(start) || end.isAfter(nowDate)) {
					System.err.println("End date is invalid,try again");
					i--;
					continue;
				} else if (start.isEqual(end)) {
					System.err.println("Start and end date can't be on the same day,try again");
					i--;
					continue;
				}

				PastCompany pc = new PastCompany(compName, start, end);

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

	public void addPastCompanyDatePeriod(PastCompany p1) {

		for (int i = 0; i < pastCompanies.length; i++) {
			if (pastCompanies[i] == null) {
				pastCompanies[i] = p1;
				return;
			}
		}

	}

	public boolean isDateValid(PastCompany p1) {

		for (int i = 0; i < pastCompanies.length; i++) {

			if (pastCompanies[i] == null && i == 0) {
				return true;
			}

			if (pastCompanies[i] != null) {
				if ((p1.getStartDate().isAfter(pastCompanies[i].getEndDate())
						&& p1.getEndDate().isAfter(pastCompanies[i].getEndDate()))
						|| (p1.getStartDate().isBefore(pastCompanies[i].getStartDate())
								&& p1.getEndDate().isBefore(pastCompanies[i].getStartDate()))) {
					return true;
				}
			}
		}

		return false;

	}

	public static LocalDate inputDate() {

		Scanner input = new Scanner(System.in);

		String date = "";

		while (true) {
			date = input.next().trim();
			try {
				LocalDate l1 = LocalDate.parse(date);
				return l1;

			} catch (DateTimeParseException e) {
				System.err.println("Invalid date try again");
			}

		}

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

	public static double calculateBetweenDates(LocalDate start, LocalDate end) {

		double years = 0;
		double months = 0;
		double days = 0;

		years = Period.between(start, end).getYears();
		months = Period.between(start, end).getMonths();
		days = Period.between(start, end).getDays();

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

	public PastCompany[] getPastCompanies() {
		return pastCompanies;
	}

	public void setPastCompanies(PastCompany[] pastCompanies) {
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
