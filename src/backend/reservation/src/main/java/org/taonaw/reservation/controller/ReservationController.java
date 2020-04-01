package org.taonaw.reservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioAppService;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioRequest;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioResponse;

@RestController
@AllArgsConstructor
public class ReservationController {
    @Autowired
    private final ReserveStudioAppService reserveStudioAppService;

//    @GetMapping(value = "/reservations", params = { "start", "end" })
//    public ResponseEntity<List<ReservationDto>> getReservations(@RequestParam("start") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date start,
//                                                                @RequestParam("end") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date end) {
//        var response = reservationQuery.getReservations(start, end);
//        return ResponseEntity.ok(response);
//    }

    @PostMapping("/reservations")
    public ResponseEntity<ReserveStudioResponse> reserveStudio(@RequestBody ReserveStudioRequest request,
                                                               UriComponentsBuilder uriComponentsBuilder) {
        var response = reserveStudioAppService.handle(request);
        var uri = uriComponentsBuilder.path("/reservations/{reservationId}").buildAndExpand(response.getReservationId()).toUri();
        return ResponseEntity.created(uri).body(response);
    }
}
