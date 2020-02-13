package org.taonaw.reservation.application;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;
import org.taonaw.reservation.application.command.ReserveStudioByMemberRequest;
import org.taonaw.reservation.common.date.CurrentDate;
import org.taonaw.reservation.domain.model.members.IMemberRepository;
import org.taonaw.reservation.domain.shared.exception.DomainException;
import org.taonaw.reservation.domain.shared.exception.DomainExceptionCodes;
import org.taonaw.reservation.application.command.ReserveStudioRequest;
import org.taonaw.reservation.domain.model.equipments.IEquipmentRepository;
import org.taonaw.reservation.domain.model.members.MemberId;
import org.taonaw.reservation.domain.model.reservations.*;
import org.taonaw.reservation.domain.model.studios.StudioId;

@Service
@AllArgsConstructor
public class ReserveStudioAppService {

    @Autowired
    private final CurrentDate currentDate;
    @Autowired
    private final ReservationService reservationService;
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IEquipmentRepository equipmentRepository;
    @Autowired
    private final IMemberRepository memberRepository;

//    @Transactional
    public void reserveStudio(@NonNull ReserveStudioRequest request) {

        var reservation = Reservation.newReservation(
                new UserInformation(request.getUserName(), request.getUserPhoneNumber()),
                PracticeTypes.of(request.getPracticeType()),
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                new NumberOfUsers(request.getNumberOfUsers()));

        reservation.addEquipments(request.getEquipmentIds());

        saveReservation(reservation);
    }

    //    @Transactional
    public void reserveStudioByMember(@NonNull ReserveStudioByMemberRequest request) {

        var member = memberRepository
                .findBy(new MemberId(request.getMemberId()))
                .orElseThrow();

        var reservation = Reservation.reservedByMember(
                member,
                PracticeTypes.of(request.getPracticeType()),
                new StudioId(request.getStudioId()),
                new TimePeriodOfUsage(request.getStartDateTime(), request.getEndDateTime()),
                new NumberOfUsers(request.getNumberOfUsers()));

        reservation.addEquipments(request.getEquipmentIds());

        saveReservation(reservation);
    }

    private void saveReservation(Reservation reservation) {
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
