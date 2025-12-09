package cs3220.controller;

import cs3220.DataComponent;
import cs3220.model.HolidayModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Controller
public class IndexController {

    private final DataComponent data;

    public IndexController(DataComponent data) {
        this.data = data;
    }

    /* ---------- Index ---------- */
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("holidays", data.allSorted());
        return "index";
    }

    /* ---------- Add ---------- */
    @GetMapping("/holidays/add")
    public String addForm(Model model) {
        populateDateLists(model);
        model.addAttribute("name", "");
        return "addHoliday";
    }

    @PostMapping("/holidays/add")
    public String addSubmit(
            @RequestParam(required=false) Integer day,
            @RequestParam(required=false) String month,
            @RequestParam(required=false) Integer year,
            @RequestParam(required=false) String name,
            Model model) {

        populateDateLists(model);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("name", name);

        if (day == null || month == null || year == null || name == null || name.trim().isEmpty()) {
            model.addAttribute("error", "All Fields are required");
            return "addHoliday";
        }

        LocalDate date;
        try {
            date = DataComponent.toDate(day, month, year);
        } catch (Exception e) {
            model.addAttribute("error", "All Fields are required");
            return "addHoliday";
        }

        if (data.existsOn(date, null)) {
            model.addAttribute("error", "Holiday Exist");
            return "addHoliday";
        }

        data.add(date, name.trim());
        return "redirect:/";
    }

    /* ---------- Update ---------- */
    @GetMapping("/holidays/{id}/edit")
    public String editForm(@PathVariable int id, Model model) {
        HolidayModel h = data.findById(id).orElseThrow();
        populateDateLists(model);
        model.addAttribute("id", id);
        model.addAttribute("day", h.getHolidayDate().getDayOfMonth());
        model.addAttribute("month", cap(h.getHolidayDate().getMonth()));
        model.addAttribute("year", h.getHolidayDate().getYear());
        model.addAttribute("name", h.getHoliday());
        return "updateHoliday";
    }

    @PostMapping("/holidays/{id}/edit")
    public String editSubmit(@PathVariable int id,
                             @RequestParam(required=false) Integer day,
                             @RequestParam(required=false) String month,
                             @RequestParam(required=false) Integer year,
                             @RequestParam(required=false) String name,
                             Model model) {

        populateDateLists(model);
        model.addAttribute("id", id);
        model.addAttribute("day", day);
        model.addAttribute("month", month);
        model.addAttribute("year", year);
        model.addAttribute("name", name);

        if (day == null || month == null || year == null || name == null || name.trim().isEmpty()) {
            model.addAttribute("error", "All Fields are required");
            return "updateHoliday";
        }

        LocalDate date;
        try {
            date = DataComponent.toDate(day, month, year);
        } catch (Exception e) {
            model.addAttribute("error", "All Fields are required");
            return "updateHoliday";
        }

        if (data.existsOn(date, id)) {
            model.addAttribute("error", "Holiday Exist");
            return "updateHoliday";
        }

        data.update(id, date, name.trim());
        return "redirect:/";
    }

    /* ---------- Delete ---------- */
    @GetMapping("/holidays/{id}/delete")
    public String delete(@PathVariable int id) {
        data.delete(id);
        return "redirect:/";
    }

    /* ---------- helpers ---------- */
    private void populateDateLists(Model model) {
        model.addAttribute("days", List.of(
                1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31));
        model.addAttribute("months", Month.values());
        model.addAttribute("years", List.of(2024, 2025, 2026));
    }

    private String cap(Month m) {
        String s = m.name().toLowerCase();
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }
}
