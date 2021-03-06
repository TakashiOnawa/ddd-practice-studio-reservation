package org.taonaw.studio_reservation.api;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/reservations")
@AllArgsConstructor
@ControllerAdvice
public class ReservationController {
//    @Autowired
//    private final ReserveStudioAppService reserveStudioAppService;
//    @Autowired
//    private final ChangeReservationAppService changeReservationAppService;
//    @Autowired
//    private final CancelReservationAppService cancelReservationAppService;
//
//    @PostMapping
//    public ResponseEntity<Void> reserveStudio(
//            @RequestBody ReserveStudioCommand request,
//            UriComponentsBuilder uriComponentsBuilder) {
//        var result = reserveStudioAppService.handle(request);
//        var uri = uriComponentsBuilder.path("/reservations/{reservationId}").buildAndExpand(result.getReservationId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PostMapping(params = { "memberId" })
//    public ResponseEntity<Void> reserveStudio(
//            @RequestParam String memberId,
//            @RequestBody ReserveStudioByMemberRequest request,
//            UriComponentsBuilder uriComponentsBuilder) {
//        var result = reserveStudioAppService.handle(request.getCommand(memberId));
//        var uri = uriComponentsBuilder.path("/reservations/{reservationId}").buildAndExpand(result.getReservationId()).toUri();
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PutMapping("/{reservationId}")
//    public ResponseEntity<ChangeReservationResult> changeReservation(
//            @PathVariable String reservationId,
//            @RequestBody ChangeReservationCommand request) {
//        var result = changeReservationAppService.handle(request);
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping("/{reservationId}/cancel")
//    public ResponseEntity<CancelReservationResult> cancelReservation(@PathVariable String reservationId) {
//        var command = CancelReservationCommand.builder()
//                .reservationId(reservationId)
//                .build();
//        var result = cancelReservationAppService.handle(command);
//        return ResponseEntity.ok(result);
//    }
//
//    @PostMapping(path = "/{reservationId}/cancel", params = { "memberId" })
//    public ResponseEntity<CancelReservationResult> cancelReservation(
//            @PathVariable String reservationId,
//            @RequestParam String memberId) {
//        var command = CancelReservationByMemberCommand.builder()
//                .reservationId(reservationId)
//                .memberId(memberId)
//                .build();
//        var result  =cancelReservationAppService.handle(command);
//        return ResponseEntity.ok(result);
//    }
//
//
//
//
//    @ExceptionHandler(ReservationDuplicatedException.class)
//    public ResponseEntity<ErrorResponse> handleException(ReservationDuplicatedException e) {
//        var response = new ErrorResponse(ErrorCode.ReservationDuplicated);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
//
//    @ExceptionHandler(EquipmentOutOfStocksException.class)
//    public ResponseEntity<ErrorResponse> handleException(EquipmentOutOfStocksException e) {
//        var error = new ErrorInformation(ErrorCode.EquipmentOutOfStocks) {
//            @Getter private final List<String> equipmentIds = new ArrayList<>(e.getEquipmentIds());
//        };
//        var response = new ErrorResponse(error);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
//
//    @ExceptionHandler(ReservationValidationException.class)
//    public ResponseEntity<ErrorResponse> handleException(ReservationValidationException e) {
//        var errorList = new ArrayList<ErrorInformation>();
//        if (e.isReasonExists(ReservationValidationException.OverMaxNumberOfUsers.class)) {
//            errorList.add(new ErrorInformation(ErrorCode.OverMaxNumberOfUsers));
//        }
//        if (e.isReasonExists(ReservationValidationException.OutOfOpeningHours.class)) {
//            errorList.add(new ErrorInformation(ErrorCode.OutOfOpeningHours));
//        }
//        if (e.isReasonExists(ReservationValidationException.ReservationNotStarted.class)) {
//            errorList.add(new ErrorInformation(ErrorCode.ReservationNotStarted));
//        }
//        if (e.isReasonExists(ReservationValidationException.StartTimeTypeNotSatisfied.class)) {
//            errorList.add(new ErrorInformation(ErrorCode.StartTimeTypeNotSatisfied));
//        }
//        var response = new ErrorResponse(errorList);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
//
//    @ExceptionHandler(CanNotChangeUseTimeException.class)
//    public ResponseEntity<ErrorResponse> handleException(CanNotChangeUseTimeException e) {
//        var response = new ErrorResponse(ErrorCode.CanNotChangeUseTime);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
//
//    @ExceptionHandler(CanNotChangeUserInformationException.class)
//    public ResponseEntity<ErrorResponse> handleException(CanNotChangeUserInformationException e) {
//        var response = new ErrorResponse(ErrorCode.CanNotChangeUserInformation);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
//
//    @ExceptionHandler(CanNotCancelReservationException.class)
//    public ResponseEntity<ErrorResponse> handleException(CanNotCancelReservationException e) {
//        var errorList = new ArrayList<ErrorInformation>();
//        if (e.isReasonExists(CanNotCancelReservationException.CancelByDifferentMember.class)) {
//            errorList.add(new ErrorInformation(ErrorCode.CanNotCancelByDifferentMember));
//        }
//        if (e.isReasonExists(CanNotCancelReservationException.ThereIsCancellationFee.class)) {
//            errorList.add(new ErrorInformation(ErrorCode.CanNotCancelThereIsCancellationFee));
//        }
//        var response = new ErrorResponse(errorList);
//        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
//    }
}
