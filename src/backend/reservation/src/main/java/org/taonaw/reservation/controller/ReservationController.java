package org.taonaw.reservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.taonaw.reservation.application.reservestudio.ReserveStudioAppService;
import org.taonaw.reservation.query.reservation.IReservationQuery;
import org.taonaw.reservation.query.reservation.ReservationDto;

import java.util.Date;
import java.util.List;

@RestController
@AllArgsConstructor
public class ReservationController {

    @Autowired
    private final ReserveStudioAppService reserveStudioAppService;
    @Autowired
    private final IReservationQuery reservationQuery;

    @GetMapping(value = "/reservations", params = { "start", "end" })
    public ResponseEntity<List<ReservationDto>> getReservations(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
                                                                @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
        var response = reservationQuery.getReservations(start, end);
        return ResponseEntity.ok(response);
    }

    public void reserveStudio() {
    }
}
