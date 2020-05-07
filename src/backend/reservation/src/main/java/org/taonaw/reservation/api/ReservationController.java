package org.taonaw.reservation.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import org.taonaw.reservation.api.error.ErrorCode;
import org.taonaw.reservation.api.error.ErrorInformation;
import org.taonaw.reservation.api.error.ErrorResponse;
import org.taonaw.reservation.application.command.cancel_reservation.CancelReservationAppService;
import org.taonaw.reservation.application.command.cancel_reservation.CancelReservationByMemberCommand;
import org.taonaw.reservation.application.command.cancel_reservation.CancelReservationCommand;
import org.taonaw.reservation.application.command.cancel_reservation.CancelReservationResult;
import org.taonaw.reservation.application.command.change_reservation.ChangeReservationAppService;
import org.taonaw.reservation.application.command.change_reservation.ChangeReservationCommand;
import org.taonaw.reservation.application.command.change_reservation.ChangeReservationResult;
import org.taonaw.reservation.application.command.reserve_studio.ReserveStudioAppService;
import org.taonaw.reservation.application.command.reserve_studio.ReserveStudioCommand;
import org.taonaw.reservation.domain.exception.EquipmentOutOfStocksException;
import org.taonaw.reservation.domain.exception.ReservationDuplicatedException;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
@ControllerAdvice
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




    @ExceptionHandler(ReservationDuplicatedException.class)
    public ResponseEntity<ErrorResponse> handleException(ReservationDuplicatedException exception) {
        var error = ErrorInformation.builder()
                .code(ErrorCode.ReservationDuplicated.getCode())
                .message("予約が重複しています。")
                .build();
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    @ExceptionHandler(EquipmentOutOfStocksException.class)
    public ResponseEntity<ErrorResponse> handleException(EquipmentOutOfStocksException exception) {
        var error = new ErrorInformation(ErrorCode.EquipmentOutOfStocks.getCode(), "予約機材の在庫が余っていません") {
            @Getter private final List<String> equipmentIds = new ArrayList<>(exception.getEquipmentIds());
        };
        var response = new ErrorResponse(error);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }
}
