package cs3220.model;

import java.time.LocalDate;

public class HolidayModel {
    private int id;
    private LocalDate holidayDate;
    private String holiday;

    public HolidayModel() { }

    public HolidayModel(String isoDate, String holiday) {
        this.holidayDate = LocalDate.parse(isoDate);
        this.holiday = holiday;
    }

    public HolidayModel(LocalDate date, String holiday) {
        this.holidayDate = date;
        this.holiday = holiday;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDate getHolidayDate() { return holidayDate; }
    public void setHolidayDate(LocalDate holidayDate) { this.holidayDate = holidayDate; }

    public String getHoliday() { return holiday; }
    public void setHoliday(String holiday) { this.holiday = holiday; }
}
