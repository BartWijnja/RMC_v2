package avans.avd.rmc_v2.service;

import avans.avd.rmc_v2.model.Timeslot;
import avans.avd.rmc_v2.repository.TimeslotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotService {
    private final TimeslotRepository timeslotRepository;

    @Autowired
    public TimeslotService(TimeslotRepository timeslotRepository) {
        this.timeslotRepository = timeslotRepository;
    }

    public List<Timeslot> getAllTimeSlots() {
        return timeslotRepository.findAll();
    }
}
