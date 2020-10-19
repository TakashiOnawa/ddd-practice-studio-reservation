package org.taonaw.studio_reservation.usecase.command.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeSettingRepository;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountRepository;
import org.taonaw.studio_reservation.domain.model.reservation.*;
import org.taonaw.studio_reservation.domain.shared.exception.Error;
import org.taonaw.studio_reservation.shared.CurrentDate;
import org.taonaw.studio_reservation.usecase.command.exception.MemberAccountNotFoundException;
import org.taonaw.studio_reservation.usecase.command.exception.ReservationNotFoundException;
import org.taonaw.studio_reservation.usecase.command.reservation.changeReservation.ChangeReservationByMemberCommand;
import org.taonaw.studio_reservation.usecase.command.reservation.reserveStudio.ReserveStudioByMemberCommand;
import org.taonaw.studio_reservation.usecase.command.reservation.reserveStudio.ReserveStudioCommand;

@AllArgsConstructor
public class ReserveService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ReservationRuleFactory reservationRuleFactory;
    @Autowired
    private final MemberAccountRepository memberAccountRepository;
    @Autowired
    private final CancellationFeeSettingRepository cancellationFeeSettingRepository;
    @Autowired
    private final CurrentDate currentDate;

    public void handle(@NonNull ReserveStudioCommand command) {
        reservationRepository.lock();

        var reservationRule = reservationRuleFactory.create(
                command.getStudioId(),
                command.getPracticeType(),
                command.getUsageEquipmentIds())
                .orElseThrow();

        var reservation = Reservation.create(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                command.getUserInformation(),
                command.getPracticeType(),
                new UsageEquipments(command.getUsageEquipments()),
                reservationRule,
                currentDate.now());

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        reservationRule.validateReservationDuplication(reservation, overlappedReservations)
                .ifPresent(Error::throwError);

        reservationRule.validateUsageEquipmentsOutOfStocks(ReservedUsageEquipments.create(overlappedReservations, reservation))
                .ifPresent(Error::throwError);

        reservationRepository.add(reservation);
    }

    public void handle(@NonNull ReserveStudioByMemberCommand command) {
        reservationRepository.lock();

        var memberAccount = memberAccountRepository.findBy(command.getMemberAccountId())
                .orElseThrow(MemberAccountNotFoundException::new);

        var reservationRule = reservationRuleFactory.create(
                command.getStudioId(),
                command.getPracticeType(),
                command.getUsageEquipmentIds())
                .orElseThrow();

        var reservation = Reservation.createByMember(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                memberAccount,
                command.getPracticeType(),
                new UsageEquipments(command.getUsageEquipments()),
                reservationRule,
                currentDate.now());

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        reservationRule.validateReservationDuplication(reservation, overlappedReservations)
                .ifPresent(Error::throwError);

        reservationRule.validateUsageEquipmentsOutOfStocks(ReservedUsageEquipments.create(overlappedReservations, reservation))
                .ifPresent(Error::throwError);

        reservationRepository.add(reservation);
    }

    public void handle(@NonNull ChangeReservationByMemberCommand command) {
        reservationRepository.lock();

        var reservation = reservationRepository.findBy(command.getReservationId())
                .orElseThrow(ReservationNotFoundException::new);

        var reservationRule = reservationRuleFactory.create(
                command.getStudioId(),
                command.getPracticeType(),
                command.getUsageEquipmentIds())
                .orElseThrow();

        var cancellationFeeSetting = cancellationFeeSettingRepository.find();

        reservation.changeByMember(
                command.getMemberAccountId(),
                command.getStudioId(),
                command.getUserCount(),
                command.getPracticeType(),
                new UsageEquipments(command.getUsageEquipments()),
                cancellationFeeSetting.cancellationFeeRates(),
                currentDate.now());

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        reservationRule.validateReservationDuplication(reservation, overlappedReservations)
                .ifPresent(Error::throwError);

        reservationRule.validateUsageEquipmentsOutOfStocks(ReservedUsageEquipments.create(overlappedReservations, reservation))
                .ifPresent(Error::throwError);

        reservationRepository.add(reservation);
    }
}
