package org.taonaw.reservation.application.reservestudio;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.equipments.Equipment;
import org.taonaw.reservation.domain.model.members.IMemberRepository;
import org.taonaw.reservation.domain.shared.exception.DomainException;
import org.taonaw.reservation.domain.shared.exception.DomainExceptionCodes;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.model.reservations.*;
import org.taonaw.reservation.domain.model.studios.StudioId;

@Service
@AllArgsConstructor
public class ReserveStudioAppService {

    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IMemberRepository memberRepository;

//    @Transactional
    public ReserveStudioResponse handle(ReserveStudioRequest request) {
        var reservation = Reservation.newReservation(
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                new UserInformation(request.getUserName(), request.getUserPhoneNumber()),
                new NumberOfUsers(request.getNumberOfUsers()),
                PracticeTypes.of(request.getPracticeType()),
                EquipmentOfUsages.of(request.getEquipmentIds()));

        saveReservation(reservation);

        return ReserveStudioResponse.builder()
                .reservationId(reservation.reservationId().getValue())
                .build();
    }

    //    @Transactional
    public ReserveStudioResponse handle(ReserveStudioByMemberRequest request) {

        var member = memberRepository
                .findBy(new MemberId(request.getMemberId()))
                .orElseThrow();

        var reservation = Reservation.reservedByMember(
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                member,
                new NumberOfUsers(request.getNumberOfUsers()),
                PracticeTypes.of(request.getPracticeType()),
                EquipmentOfUsages.of(request.getEquipmentIds()));

        reservation.addEquipments(request.getEquipmentIds());

        saveReservation(reservation);

        return ReserveStudioResponse.builder()
                .reservationId(reservation.reservationId().getValue())
                .build();
    }

    private void saveReservation(Reservation reservation) {
        reservation.validate(reservationRepository.getReservationValidator());

        if (reservationService.equipmentOutOfStock(reservation)) {
            throw new DomainException(DomainExceptionCodes.EquipmentOutOfStock);
        }

        if (reservationService.isDuplicated(reservation)) {
            throw new DomainException(DomainExceptionCodes.ReservationDuplicated);
        }

        reservationRepository.save(reservation);
    }
}
