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

        var reservation = Reservation.newReservation(
                new StudioId(command.getStudioId()),
                new UseTime(command.getStartDateTime(), command.getHourQuantity()),
                new UserInformation(command.getUserName(), command.getUserPhoneNumber()),
                new NumberOfUsers(command.getNumberOfUsers()),
                PracticeType.from(command.getPracticeType()),
                UseEquipments.of(command.getEquipmentIds()));

        validate(reservation);

        reservationRepository.add(reservation);

        return ReserveStudioResult.builder()
                .reservationId(reservation.getReservationId().getValue())
                .build();
    }

    public ReserveStudioResult handle(ReserveStudioByMemberCommand command) {
        reservationRepository.lock();

        var member = memberRepository.findBy(new MemberId(command.getMemberId())).orElseThrow();

        var reservation = Reservation.newReservationByMember(
                new StudioId(command.getStudioId()),
                new UseTime(command.getStartDateTime(), command.getHourQuantity()),
                member,
                new NumberOfUsers(command.getNumberOfUsers()),
                PracticeType.from(command.getPracticeType()),
                UseEquipments.of(command.getEquipmentIds()));

        validate(reservation);

        reservationRepository.add(reservation);

        return ReserveStudioResult.builder()
                .reservationId(reservation.getReservationId().getValue())
                .build();
    }

    private void validate(Reservation reservation) {
        new ReservationValidator(reservationSettingRepository, currentDate).validate(reservation);

        new CheckDuplicateReservationService(reservationRepository).validate(reservation);
        new CheckEquipmentsOutOfStocksService(reservationRepository, equipmentRepository).validate(reservation);
    }
}
