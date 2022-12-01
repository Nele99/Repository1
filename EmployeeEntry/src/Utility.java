import org.apache.commons.lang3.math.NumberUtils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Utility {

    public static boolean choiceControl(int choice) {
        if (choice == 1 || choice == 2) {
            return true;
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
                    throw new InvalidInputException("Invalid input,contains numbers only");
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
        if (NumberUtils.isDigits(check)) {
            return true;
        }
        return false;
    }


    public static double calculateBetweenDates(LocalDate start, LocalDate end) {

        return (double) Period.between(start, end).getYears()
                + (double) Period.between(start, end).getMonths() / 12
                + (double) Period.between(start, end).getDays() / 31;
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
                            "Invalid input, must be  0 or more than 0!");
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
