package cs3220.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;
import java.time.Month;

/**
 * Form backing bean used by both add and update holiday flows.
 * Provides simple validation annotations and helpers to translate
 * dropdown selections into {@link LocalDate} instances.
 */
public class HolidayForm {

    @NotNull(message = "Day is required")
    private Integer day;

    @NotBlank(message = "Month is required")
    private String month;

    @NotNull(message = "Year is required")
    private Integer year;

    @NotBlank(message = "Holiday name is required")
    private String name;

    public Integer getDay() {
        return day;
    }

    public void setDay(Integer day) {
        this.day = day;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    /**
     * Converts the captured dropdown selections into a LocalDate.
     *
     * @return holiday date
     */
    public LocalDate toDate() {
        if (month == null || year == null || day == null) {
            throw new IllegalArgumentException("Incomplete date selection");
        }

        Month parsedMonth = Month.valueOf(month.trim().toUpperCase());
        return LocalDate.of(year, parsedMonth, day);
    }

    /**
     * Creates a form pre-populated from an existing holiday model.
     */
    public static HolidayForm fromModel(HolidaysModel model) {
        HolidayForm form = new HolidayForm();
        LocalDate date = model.getHolidayDate();
        form.setDay(date.getDayOfMonth());
        form.setMonth(capitalize(date.getMonth()));
        form.setYear(date.getYear());
        form.setName(model.getHoliday());
        return form;
    }

    /**
     * Default form values used for the add screen.
     */
    public static HolidayForm defaults() {
        HolidayForm form = new HolidayForm();
        form.setDay(1);
        form.setMonth("January");
        form.setYear(2024);
        form.setName("");
        return form;
    }

    private static String capitalize(Month month) {
        String lower = month.name().toLowerCase();
        return Character.toUpperCase(lower.charAt(0)) + lower.substring(1);
    }
}
