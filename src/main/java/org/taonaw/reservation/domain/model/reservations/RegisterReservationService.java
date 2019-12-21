package org.taonaw.reservation.domain.model.reservations;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.taonaw.common.domain.exception.DomainException;
import org.taonaw.common.domain.exception.DomainExceptionCodes;

@AllArgsConstructor
public class RegisterReservationService {
    @NonNull
    private final ReservationRepository reservationRepository;

    public void register(Reservation reservation) {
        if (isDuplicatedReservation(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationDuplication);
        }

        // TODO:機材の在庫チェック

        reservationRepository.save(reservation);
    }

    private boolean isDuplicatedReservation(Reservation reservation) {
        var sameStudioReservations = reservationRepository.findByStudio(reservation.studioId());
        return sameStudioReservations.stream().anyMatch(other -> reservation.isDuplicated(other));
    }
}
