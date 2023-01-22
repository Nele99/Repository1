package GuiFolder;

import java.time.LocalDate;

public class Date {

    private final LocalDate startDate;
    private final LocalDate endDate;


    public Date(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

}