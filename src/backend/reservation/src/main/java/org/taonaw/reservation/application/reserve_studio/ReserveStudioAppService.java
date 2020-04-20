package org.taonaw.reservation.application.reserve_studio;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.reservation.common.CurrentDate;
import org.taonaw.reservation.domain.model.equipment.IEquipmentRepository;
import org.taonaw.reservation.domain.model.member.IMemberRepository;
import org.taonaw.reservation.domain.model.member.MemberId;
import org.taonaw.reservation.domain.model.reservation.*;
import org.taonaw.reservation.domain.model.reservationsetting.IReservationSettingRepository;
import org.taonaw.reservation.domain.model.studio.StudioId;

import java.time.LocalDateTime;

//import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class ReserveStudioAppService {
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IEquipmentRepository equipmentRepository;
    @Autowired
    private final IReservationSettingRepository reservationSettingRepository;
    @Autowired
    private final IMemberRepository memberRepository;
    @Autowired
    private final CurrentDate currentDate;

    //    @Transactional
    public ReserveStudioResult handle(ReserveStudioCommand command) {
        reservationRepository.lock();

        var currentDateTime = currentDate.now();

        var reservation = Reservation.newReservation(
                new StudioId(command.getStudioId()),
                new UseTime(command.getStartDateTime(), command.getEndDateTime()),
                new UserInformation(command.getUserName(), command.getUserPhoneNumber()),
                new NumberOfUsers(command.getNumberOfUsers()),
                PracticeType.from(command.getPracticeType()),
                UseEquipments.of(command.getEquipmentIds()));

        validate(reservation, currentDateTime);

        reservationRepository.add(reservation);

        return ReserveStudioResult.of(reservation);
    }

    public ReserveStudioResult handle(ReserveStudioByMemberCommand command) {
        reservationRepository.lock();

        var currentDateTime = currentDate.now();

        var member = memberRepository.findBy(new MemberId(command.getMemberId())).orElseThrow();

        var reservation = Reservation.newReservationByMember(
                new StudioId(command.getStudioId()),
                new UseTime(command.getStartDateTime(), command.getEndDateTime()),
                member,
                new NumberOfUsers(command.getNumberOfUsers()),
                PracticeType.from(command.getPracticeType()),
                UseEquipments.of(command.getEquipmentIds()));

        validate(reservation, currentDateTime);

        reservationRepository.add(reservation);

        return ReserveStudioResult.of(reservation);
    }

    private void validate(Reservation reservation, LocalDateTime currentDateTime) {
        new ReservationValidator(reservationSettingRepository, currentDateTime).validate(reservation);

        new CheckDuplicateReservationService(reservationRepository).validate(reservation);
        new CheckEquipmentsOutOfStocksService(reservationRepository, equipmentRepository).validate(reservation);
    }
}
