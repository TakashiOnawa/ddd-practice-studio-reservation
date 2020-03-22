package org.taonaw.reservation.domain.model.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.reservation.domain.shared.exception.DomainException;
import org.taonaw.reservation.domain.shared.exception.DomainExceptionCodes;

@AllArgsConstructor
public class CheckDuplicateReservationService {
    private final IReservationRepository reservationRepository;

    public boolean isDuplicated(@NonNull Reservation reservation) {
        return Reservations.createOverlappedOtherReservations(reservationRepository, reservation).exists();
    }

    public void validate(@NonNull Reservation reservation) {
        if (isDuplicated(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationDuplicated);
        }
    }
}
