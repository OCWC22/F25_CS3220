package cs3220.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Model class representing a holiday entry with id, date, and name.
 */
public class HolidayEntry {
    private final int id;
    private LocalDate holidayDate;
    private String holiday;

    /**
     * Constructor using String date format (yyyy-MM-dd)
     */
    public HolidayEntry(String holidayDate, String holiday) {
        this(0, LocalDate.parse(holidayDate), holiday);
    }

    /**
     * Constructor with explicit ID and LocalDate
     */
    public HolidayEntry(int id, LocalDate holidayDate, String holiday) {
        this.id = id;
        this.holidayDate = holidayDate;
        this.holiday = holiday;
    }

    public int getId() {
        return id;
    }

    public LocalDate getHolidayDate() {
        return holidayDate;
    }

    public String getHoliday() {
        return holiday;
    }

    public void setHolidayDate(LocalDate holidayDate) {
        this.holidayDate = holidayDate;
    }

    public void setHoliday(String holiday) {
        this.holiday = holiday;
    }

    /**
     * Format date as "dd MMMM yyyy" (e.g., "01 January 2024")
     */
    public String getFormattedDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        return holidayDate.format(formatter);
    }
}
