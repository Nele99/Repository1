package GuiFolder;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;

public class Utility {

    static Date[] dates = new Date[Gui.getNumPastCompanies()];
    static int counter = 0;

    public static void addCount(int num ){
       counter += num;
    }

    public static int getCounter() {
        return counter;
    }

    public static boolean inputDate(String input) {

        String date = input.trim();
        try {
            LocalDate l1 = LocalDate.parse(date);
            return true;

        } catch (DateTimeParseException e) {
            return false;
        }

    }

    public static boolean checkDatesValid(String date1, String date2) {

        LocalDate start = LocalDate.parse(date1);
        LocalDate end = LocalDate.parse(date2);

        if (start.isAfter(LocalDate.now()) || end.isAfter(LocalDate.now())) {
            return false;
        } else return !end.isBefore(start);
    }


    public static boolean correctDateRange(String date1, String date2) {

        LocalDate start = LocalDate.parse(date1);
        LocalDate end = LocalDate.parse(date2);

        Date d1 = new Date(start, end);

        for (int i = 0; i < dates.length; i++) {

            if (dates[i] == null && i == 0) {
                return true;
            }

            if (dates[i] != null) {
                if ((d1.getStartDate().isAfter(dates[i].getEndDate()) && d1.getEndDate().isAfter(dates[i].getEndDate()))
                        || (d1.getStartDate().isBefore(dates[i].getStartDate()) && d1.getEndDate().isBefore(dates[i].getStartDate()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static void addDate(String date1, String date2) {

        LocalDate start = LocalDate.parse(date1);
        LocalDate end = LocalDate.parse(date2);

        Date finalDate = new Date(start, end);

        for (int i = 0; i < Gui.getNumPastCompanies(); i++) {
            if (dates[i] == null) {
                dates[i] = finalDate;
                return;
            }
        }
    }

    public static double calculateBetweenDates(LocalDate start, LocalDate end) {

        return (double) Period.between(start, end).getYears()
                + (double) Period.between(start, end).getMonths() / 12
                + (double) Period.between(start, end).getDays() / 31;
    }

    public static double allYears() {
        double years = 0;
        for (int i = 0; i < dates.length; i++) {
            if (dates[i] != null) {
                years += Utility.calculateBetweenDates(dates[i].getStartDate(), dates[i].getEndDate());
            }
        }
        return years;
    }

    public static int vacationDays(double years) {

        int vacation = 20;

        for (int i = 5; i < 50; i += 5) {
            if (years > i) {
                vacation += 1;
            }
        }
        return vacation;
    }


}




