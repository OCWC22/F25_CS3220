package cs3220;

import cs3220.model.HolidaysModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class DataComponent {
    private final List<HolidaysModel> holidays = new ArrayList<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    public boolean isEmpty() { return holidays.isEmpty(); }

    public List<HolidaysModel> allSorted() {
        return holidays.stream()
                .sorted(Comparator.comparing(HolidaysModel::getHolidayDate))
                .collect(Collectors.toList());
    }

    public void add(String isoDate, String name) {
        HolidaysModel m = new HolidaysModel(isoDate, name);
        m.setId(seq.getAndIncrement());
        holidays.add(m);
    }

    public void add(LocalDate date, String name) {
        HolidaysModel m = new HolidaysModel(date, name);
        m.setId(seq.getAndIncrement());
        holidays.add(m);
    }

    public Optional<HolidaysModel> findById(int id) {
        return holidays.stream().filter(h -> h.getId() == id).findFirst();
    }

    public boolean existsOn(LocalDate date, Integer excludeId) {
        return holidays.stream().anyMatch(h ->
                h.getHolidayDate().equals(date) && (excludeId == null || h.getId() != excludeId));
    }

    public void update(int id, LocalDate date, String name) {
        findById(id).ifPresent(h -> {
            h.setHolidayDate(date);
            h.setHoliday(name);
        });
    }

    public void delete(int id) {
        holidays.removeIf(h -> h.getId() == id);
    }

    // Utilities for parsing dropdown strings like "January"
    public static LocalDate toDate(int day, String monthName, int year) {
        Month m = Month.valueOf(monthName.toUpperCase());
        return LocalDate.of(year, m, day);
    }
}
