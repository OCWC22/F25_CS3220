package cs3220;

import cs3220.model.HolidayModel;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.Month;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Component
public class DataComponent {
    private final List<HolidayModel> holidays = new ArrayList<>();
    private final AtomicInteger seq = new AtomicInteger(1);

    public boolean isEmpty() { return holidays.isEmpty(); }

    public List<HolidayModel> allSorted() {
        return holidays.stream()
                .sorted(Comparator.comparing(HolidayModel::getHolidayDate))
                .collect(Collectors.toList());
    }

    public void add(String isoDate, String name) {
        HolidayModel m = new HolidayModel(isoDate, name);
        m.setId(seq.getAndIncrement());
        holidays.add(m);
    }

    public void add(LocalDate date, String name) {
        HolidayModel m = new HolidayModel(date, name);
        m.setId(seq.getAndIncrement());
        holidays.add(m);
    }

    public Optional<HolidayModel> findById(int id) {
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
