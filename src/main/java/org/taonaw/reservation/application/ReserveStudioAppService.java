package org.taonaw.reservation.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.common.domain.exception.DomainException;
import org.taonaw.reservation.common.domain.exception.DomainExceptionCodes;
import org.taonaw.reservation.application.command.ReserveStudioRequest;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.model.reservations.*;
import org.taonaw.reservation.domain.model.studios.StudioId;

@Service
@AllArgsConstructor
public class ReserveStudioAppService {

    @NonNull
    @Autowired
    private final CurrentDate currentDate;
    @NonNull
    @Autowired
    private final ReservationService reservationService;
    @NonNull
    @Autowired
    private final IReservationRepository reservationRepository;
    @NonNull
    @Autowired
    private final IEquipmentRepository equipmentRepository;

    @Transactional
    public void reserveStudio(@NonNull ReserveStudioRequest request) {

        // TODO:予約権限があるかどうか確認する。

        var reservation = Reservation.newReservation(
                new MemberId(request.getMemberId()),
                PracticeTypes.of(request.getPracticeType()),
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                new NumberOfUsers(request.getNumberOfUsers()));

        reservation.addEquipments(request.getEquipmentIds());

        reservation.validate(reservationRepository.getReservationValidator());

        if (reservationService.equipmentOutOfStock(reservation)) {
            throw new DomainException(DomainExceptionCodes.EquipmentOutOfStock);
        }

        if (reservationService.alreadyReserved(reservation)) {
            throw new DomainException(DomainExceptionCodes.StudioAlreadyReserved);
        }

        reservationRepository.save(reservation);
    }
}
