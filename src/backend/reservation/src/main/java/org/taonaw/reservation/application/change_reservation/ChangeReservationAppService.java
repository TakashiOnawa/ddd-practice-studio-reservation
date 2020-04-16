package org.taonaw.reservation.application.change_reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.reservation.common.CurrentDate;
import org.taonaw.reservation.domain.model.cancellationfeesetting.ICancellationFeeSettingRepository;
import org.taonaw.reservation.domain.model.reservation.*;
import org.taonaw.reservation.domain.model.reservationsetting.IReservationSettingRepository;

@Service
@AllArgsConstructor
public class ChangeReservationAppService {
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final IReservationSettingRepository reservationSettingRepository;
    @Autowired
    private final ICancellationFeeSettingRepository cancellationFeeSettingRepository;
    @Autowired
    private final CurrentDate currentDate;

    //    @Transactional
    public void handle(ChangeReservationCommand command) {
        reservationRepository.lock();

        var currentDateTime = currentDate.now();
        var cancellationFeeSetting = cancellationFeeSettingRepository.get();

        var reservation = reservationRepository.findBy(new ReservationId(command.getReservationId())).orElseThrow();
        reservation.changeUseTime(
                new UseTime(command.getStartDateTime(), command.getEndDateTime()),
                cancellationFeeSetting,
                currentDateTime.toLocalDate());
        reservation.changeUserInformation(new UserInformation(command.getUserName(), command.getUserPhoneNumber()));
        reservation.changeNumberOfUsers(new NumberOfUsers(command.getNumberOfUsers()));
        reservation.changePracticeType(PracticeType.from(command.getPracticeType()));
        reservation.changeUseEquipments(UseEquipments.of(command.getEquipmentIds()));

        new ReservationValidator(reservationSettingRepository, currentDateTime).validate(reservation);

        reservationRepository.update(reservation);
    }
}
