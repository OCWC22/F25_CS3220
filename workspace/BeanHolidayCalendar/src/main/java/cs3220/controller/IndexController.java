package cs3220.controller;

import cs3220.DataComponent;
import cs3220.model.HolidayForm;
import cs3220.model.HolidaysModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping
public class IndexController {

    private static final List<Integer> DAYS = IntStream.rangeClosed(1, 31)
            .boxed()
            .collect(Collectors.toUnmodifiableList());
    private static final List<Month> MONTHS = List.of(Month.values());
    private static final List<Integer> YEARS = List.of(2024, 2025, 2026);

    private final DataComponent data;

    public IndexController(DataComponent data) {
        this.data = data;
    }

    /* ---------- Shared dropdown data ---------- */
    @ModelAttribute("days")
    public List<Integer> days() {
        return DAYS;
    }

    @ModelAttribute("months")
    public List<Month> months() {
        return MONTHS;
    }

    @ModelAttribute("years")
    public List<Integer> years() {
        return YEARS;
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
        if (!model.containsAttribute("holidayForm")) {
            model.addAttribute("holidayForm", HolidayForm.defaults());
        }
        return "addHoliday";
    }

    @PostMapping("/holidays/add")
    public String addSubmit(@Valid @ModelAttribute("holidayForm") HolidayForm form,
                            BindingResult result) {

        LocalDate date = toDateOrReject(form, result);

        if (!result.hasErrors() && data.existsOn(date, null)) {
            result.rejectValue("day", "holiday.exists", "Holiday already exists for that date");
        }

        if (result.hasErrors()) {
            return "addHoliday";
        }

        data.add(date, form.getName());
        return "redirect:/";
    }

    /* ---------- Update ---------- */
    @GetMapping("/holidays/{id}/edit")
    public String editForm(@PathVariable("id") int id, Model model) {
        HolidaysModel holiday = data.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Holiday not found"));

        model.addAttribute("holidayId", id);
        if (!model.containsAttribute("holidayForm")) {
            model.addAttribute("holidayForm", HolidayForm.fromModel(holiday));
        }
        return "updateHoliday";
    }

    @PostMapping("/holidays/{id}/edit")
    public String editSubmit(@PathVariable("id") int id,
                             @Valid @ModelAttribute("holidayForm") HolidayForm form,
                             BindingResult result,
                             Model model) {

        model.addAttribute("holidayId", id);
        data.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Holiday not found"));

        LocalDate date = toDateOrReject(form, result);

        if (!result.hasErrors() && data.existsOn(date, id)) {
            result.rejectValue("day", "holiday.exists", "Holiday already exists for that date");
        }

        if (result.hasErrors()) {
            return "updateHoliday";
        }

        data.update(id, date, form.getName());
        return "redirect:/";
    }

    /* ---------- Delete ---------- */
    @GetMapping("/holidays/{id}/delete")
    public String delete(@PathVariable("id") int id) {
        data.delete(id);
        return "redirect:/";
    }

    /* ---------- helpers ---------- */
    private LocalDate toDateOrReject(HolidayForm form, BindingResult result) {
        if (result.hasErrors()) {
            return null;
        }

        try {
            return form.toDate();
        } catch (Exception e) {
            result.rejectValue("day", "invalid.date", "Select a valid date for the chosen month");
            return null;
        }
    }
}
