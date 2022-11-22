import java.text.DecimalFormat;
import java.time.LocalDate;

public class PastCompany {

	private String name;
	private LocalDate startDate;
	private LocalDate endDate;

	public PastCompany(String name, LocalDate startDate, LocalDate endDate) {
		super();
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public PastCompany(String name, String start, String end) {
		super();
		this.name = name;
		this.startDate = LocalDate.parse(start);
		this.endDate = LocalDate.parse(end);
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

	@Override
	public String toString() {

		DecimalFormat df = new DecimalFormat("0.00");

		return name + ", startDate: " + startDate + ", endDate: " + endDate + ", years: "
				+ df.format(Employee.calculateBetweenDates(startDate, endDate));
	}

}
