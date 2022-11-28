import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utility{

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

	public static boolean isDigits(String check) {
	
		char[] array = check.toCharArray();
	
		for (int i = 0; i < array.length; i++) {
			if (Character.isDigit(array[i])) {
				return true;
			}
		}
	
		return false;
	
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
	
	

}
