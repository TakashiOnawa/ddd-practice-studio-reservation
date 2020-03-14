package org.taonaw.managementsite.application.reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestOperations;
import org.taonaw.managementsite.application.reservation.getreservations.GetReservationsRequest;
import org.taonaw.managementsite.application.reservation.getreservations.GetReservationsResponse;
import org.taonaw.managementsite.application.reservation.reservestudio.ReserveStudioRequest;

@Service
@AllArgsConstructor
public class ReservationService {

    @Autowired
    @Qualifier("reservationRestOptions")
    private final RestOperations reservationRestOptions;

    public ResponseEntity<GetReservationsResponse> getReservations(GetReservationsRequest request) {
        return null;
    }

    public ResponseEntity<Void> reserveStudio(ReserveStudioRequest request) {
        var uri = "/reservations";
        return reservationRestOptions.postForEntity(uri, request, Void.class);
    }
}
