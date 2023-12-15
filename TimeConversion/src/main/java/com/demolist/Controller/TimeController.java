package com.demolist.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Controller
public class TimeController {

    private final List<String> supportedTimezones;

    public TimeController() {
        // Get a list of all available time zones
        String[] timezonesArray = ZoneId.getAvailableZoneIds().toArray(new String[0]);
        List<String> allTimezones = new ArrayList<>(List.of(timezonesArray));
        // Sort the time zones alphabetically for better user experience
        Collections.sort(allTimezones);
        // Set the supportedTimezones list
        supportedTimezones = allTimezones;
    }

    @GetMapping("/")
    public String showForm(Model model) {
        model.addAttribute("timezones", supportedTimezones);
        return "timeForm";
    }

    @PostMapping("/convert")
    public String convertTime(String selectedTimezone, Model model) {
        ZoneId zoneId = ZoneId.of(selectedTimezone);
        LocalDateTime localDateTime = LocalDateTime.now(zoneId);

        model.addAttribute("selectedTimezone", selectedTimezone);
        model.addAttribute("convertedTime", localDateTime);

        return "timeResult";
    }
}
