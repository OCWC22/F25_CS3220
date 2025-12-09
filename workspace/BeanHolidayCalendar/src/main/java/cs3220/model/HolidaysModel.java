package cs3220.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HolidaysModel {
    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd MMMM yyyy");

    private int id;
    private LocalDate holidayDate;
    private String holiday;

    public HolidaysModel() { }

    public HolidaysModel(String isoDate, String holiday) {
        this.holidayDate = LocalDate.parse(isoDate);
        this.holiday = holiday;
    }

    public HolidaysModel(LocalDate date, String holiday) {
        this.holidayDate = date;
        this.holiday = holiday;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getHolidayDate() { return holidayDate; }
    public void setHolidayDate(LocalDate holidayDate) { this.holidayDate = holidayDate; }

    public String getHoliday() { return holiday; }
    public void setHoliday(String holiday) { this.holiday = holiday; }

    public String getDisplayDate() {
        return holidayDate == null ? "" : holidayDate.format(DISPLAY_FORMAT);
    }
}
