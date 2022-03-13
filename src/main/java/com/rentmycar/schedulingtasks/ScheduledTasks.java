package com.rentmycar.schedulingtasks;

import com.rentmycar.controller.ReservationController;
import com.rentmycar.enums.ReservationStatus;
import com.rentmycar.model.Reservation;
import com.rentmycar.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;
import java.util.List;

@Component
public class ScheduledTasks {

    @Autowired
    private ReservationRepository reservationRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
    private static final int ONE_MINUTE = 1000 * 60;
    private static final int ONE_HOUR = 1000 * 60 * 60;
    private static final int ONE_DAY = 1000 * 60 * 60 * 24;

    @Scheduled(fixedRate = ONE_HOUR)
    public void reportCurrentTime() {
        System.out.println("[SCHEDULING]: The time is now " + dateFormat.format(new Date()));
    }

    // Checking reservations every one minute
    @Scheduled(fixedRate = ONE_MINUTE)
    public void checkActiveReservations() {
        List<Reservation> reservationList = reservationRepository.findAll();

        for (Reservation reservation : reservationList) {
            if (reservation.getStatus() == ReservationStatus.PENDING) {
                System.out.println("Checking reservation status of Id number : " + reservation.getId());
                System.out.println(reservation.getStatus());
                int daysReserved = reservation.getDaysReserved();

                int daysReservedMillis = ONE_DAY * daysReserved;
                ZoneId zone = ZoneId.of("Europe/Amsterdam");

                LocalDateTime dateNow = LocalDateTime.now();
                LocalDateTime createdDate = dateNow.minusDays(daysReserved);
                ZonedDateTime zoneDate = dateNow.atZone(zone);
                ZonedDateTime zoneCreatedDate = createdDate.atZone(zone);

                long diff = zoneDate.toInstant().toEpochMilli() - zoneCreatedDate.toInstant().toEpochMilli();

                if (diff > daysReservedMillis) {
                    reservation.setStatus(ReservationStatus.EXPIRED);
                    reservationRepository.save(reservation);
                    System.out.println("Reservation Id " + reservation.getId() + " has been expired.");
                }
            }
        }
    }
}
