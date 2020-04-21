package org.taonaw.reservation.api;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.reservation.application.cancel_reservation.CancelReservationAppService;
import org.taonaw.reservation.application.cancel_reservation.CancelReservationByMemberCommand;
import org.taonaw.reservation.application.cancel_reservation.CancelReservationCommand;
import org.taonaw.reservation.application.cancel_reservation.CancelReservationResult;
import org.taonaw.reservation.application.change_reservation.ChangeReservationAppService;
import org.taonaw.reservation.application.change_reservation.ChangeReservationCommand;
import org.taonaw.reservation.application.change_reservation.ChangeReservationResult;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioAppService;
import org.taonaw.reservation.application.reserve_studio.ReserveStudioCommand;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
public class ReservationController {
    @Autowired
    private final ReserveStudioAppService reserveStudioAppService;
    @Autowired
    private final ChangeReservationAppService changeReservationAppService;
    @Autowired
    private final CancelReservationAppService cancelReservationAppService;

    @PostMapping
    public ResponseEntity<Void> reserveStudio(
            @RequestBody ReserveStudioCommand request,
            UriComponentsBuilder uriComponentsBuilder) {
        var result = reserveStudioAppService.handle(request);
        var uri = uriComponentsBuilder.path("/reservations/{reservationId}").buildAndExpand(result.getReservationId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping(params = { "memberId" })
    public ResponseEntity<Void> reserveStudio(
            @RequestParam String memberId,
            @RequestBody ReserveStudioByMemberRequest request,
            UriComponentsBuilder uriComponentsBuilder) {
        var result = reserveStudioAppService.handle(request.getCommand(memberId));
        var uri = uriComponentsBuilder.path("/reservations/{reservationId}").buildAndExpand(result.getReservationId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{reservationId}")
    public ResponseEntity<ChangeReservationResult> changeReservation(
            @PathVariable String reservationId,
            @RequestBody ChangeReservationCommand request) {
        var result = changeReservationAppService.handle(request);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/{reservationId}/cancel")
    public ResponseEntity<CancelReservationResult> cancelReservation(@PathVariable String reservationId) {
        var command = CancelReservationCommand.builder()
                .reservationId(reservationId)
                .build();
        var result = cancelReservationAppService.handle(command);
        return ResponseEntity.ok(result);
    }

    @PostMapping(path = "/{reservationId}/cancel", params = { "memberId" })
    public ResponseEntity<CancelReservationResult> cancelReservation(
            @PathVariable String reservationId,
            @RequestParam String memberId) {
        var command = CancelReservationByMemberCommand.builder()
                .reservationId(reservationId)
                .memberId(memberId)
                .build();
        var result  =cancelReservationAppService.handle(command);
        return ResponseEntity.ok(result);
    }
}
