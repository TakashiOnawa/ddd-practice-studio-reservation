package org.taonaw.reservation.application.cancel_reservation;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.taonaw.reservation.common.CurrentDate;
import org.taonaw.reservation.domain.model.cancellationfeesetting.ICancellationFeeSettingRepository;
import org.taonaw.reservation.domain.model.member.MemberId;
import org.taonaw.reservation.domain.model.reservation.IReservationRepository;
import org.taonaw.reservation.domain.model.reservation.ReservationId;

@Service
@AllArgsConstructor
public class CancelReservationAppService {
    @Autowired
    private final IReservationRepository reservationRepository;
    @Autowired
    private final ICancellationFeeSettingRepository cancellationFeeSettingRepository;
    @Autowired
    private final CurrentDate currentDate;

    //    @Transactional
    public CancelReservationResult handle(CancelReservationCommand command) {
        var reservation = reservationRepository.findBy(new ReservationId(command.getReservationId())).orElseThrow();

        reservation.cancel();

        reservationRepository.update(reservation);

        return CancelReservationResult.of(reservation);
    }

    //    @Transactional
    public CancelReservationResult handle(CancelReservationByMemberCommand command) {
        var currentDateTime = currentDate.now();
        var cancellationFeeSetting = cancellationFeeSettingRepository.get();

        var reservation = reservationRepository.findBy(new ReservationId(command.getReservationId())).orElseThrow();

        var memberId = new MemberId(command.getMemberId());

        reservation.cancelByMember(memberId, cancellationFeeSetting, currentDateTime.toLocalDate());

        reservationRepository.update(reservation);

        return CancelReservationResult.of(reservation);
    }
}
