package org.taonaw.reservation.controller;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioAppService;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioResult;

@RestController
@AllArgsConstructor
public class ReservationController {
    @Autowired
    private final ReserveStudioAppService reserveStudioAppService;

    @PostMapping("/reservations")
    public ResponseEntity<Void> reserveStudio(
            @RequestBody ReserveStudioRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        ReserveStudioResult result;
        if (request.isReserveByMember()) {
            result = reserveStudioAppService.handle(request.getReserveStudioByMemberCommand());
        }
        else {
            result = reserveStudioAppService.handle(request.getReserveStudioCommand());
        }
        var uri = uriComponentsBuilder.path("/reservations/{reservationId}").buildAndExpand(result.getReservationId()).toUri();
        return ResponseEntity.created(uri).build();
    }
}
