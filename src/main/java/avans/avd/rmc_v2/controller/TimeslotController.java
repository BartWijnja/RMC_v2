package avans.avd.rmc_v2.controller;

import avans.avd.rmc_v2.model.Timeslot;
import avans.avd.rmc_v2.service.TimeslotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/timeslot")
public class TimeslotController {
    private final TimeslotService timeslotService;

    @Autowired
    public TimeslotController(TimeslotService timeslotService) {
        this.timeslotService = timeslotService;
    }

    @GetMapping("/")
    public List<Timeslot> getTimeSlotList() {
        return timeslotService.getAllTimeSlots();
    }
}
