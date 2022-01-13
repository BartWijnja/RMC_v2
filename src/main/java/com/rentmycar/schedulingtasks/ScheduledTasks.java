package com.rentmycar.schedulingtasks;

import com.rentmycar.enums.ReservationStatus;
import com.rentmycar.model.Reservation;
import com.rentmycar.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.time.*;
import java.util.Date;

@Component
public class ScheduledTasks {

    @Autowired
    private ReservationRepository reservationRepository;

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private static final int ONE_MINUTE = 1000 * 60;
    private static final int ONE_HOUR = 1000 * 60 * 60;
    private static final int ONE_DAY = 1000 * 60 * 60 * 24;

    @Scheduled(fixedRate = ONE_MINUTE)
    public void reportCurrentTime() {
        System.out.println("[SCHEDULING]: The time is now " + dateFormat.format(new Date()));
    }

    // Checkt actieve reservations of de tijd verlopen is, zo ja veranderd de STATUS
    @Scheduled(fixedRate = ONE_DAY)
    public void checkActiveReservations() {
        reservationRepository.findAll();

        Reservation reservation = new Reservation();
        
        int daysReserved = reservation.getDaysReserved();

        ZonedDateTime zdt = ZonedDateTime.of(reservation.getCreatedAt(), ZoneId.systemDefault());
        long createdAt = zdt.toInstant().toEpochMilli();

        long dateNow = Instant.now().toEpochMilli();

        long diff = dateNow - createdAt;

        long expiredTime = diff - daysReserved;

        if (diff > daysReserved) {
            reservation.setStatus(ReservationStatus.EXPIRED);
            reservationRepository.save(reservation);
            System.out.println("Reservation has been expired by" + expiredTime);
        } else {
            System.out.println("No reservations are expired");
        }
    }

}
