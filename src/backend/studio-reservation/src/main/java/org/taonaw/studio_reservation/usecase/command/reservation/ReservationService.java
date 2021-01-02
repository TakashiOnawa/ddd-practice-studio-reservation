package org.taonaw.studio_reservation.usecase.command.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.cancellationFeeSetting.CancellationFeeSettingRepository;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountRepository;
import org.taonaw.studio_reservation.domain.model.reservation.Reservation;
import org.taonaw.studio_reservation.domain.model.reservation.ReservationRepository;
import org.taonaw.studio_reservation.domain.model.reservation.ReservationRuleFactory;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.basicUsageFeeSetting.BasicUsageFeeSettingRepository;
import org.taonaw.studio_reservation.domain.model.usageFeeSetting.packFeeSetting.PackFeeSettingRepository;
import org.taonaw.studio_reservation.domain.shared.exception.Error;
import org.taonaw.studio_reservation.domain.shared.exception.ErrorNotification;
import org.taonaw.studio_reservation.shared.CurrentDate;
import org.taonaw.studio_reservation.usecase.command.exception.MemberAccountNotFoundException;
import org.taonaw.studio_reservation.usecase.command.exception.ReservationNotFoundException;
import org.taonaw.studio_reservation.usecase.command.exception.ReservationOptimisticLockException;
import org.taonaw.studio_reservation.usecase.command.reservation.cancelReservation.CancelReservationByMemberCommand;
import org.taonaw.studio_reservation.usecase.command.reservation.changeReservation.ChangeReservationByMemberCommand;
import org.taonaw.studio_reservation.usecase.command.reservation.reserveStudio.ReserveStudioByMemberCommand;
import org.taonaw.studio_reservation.usecase.command.reservation.reserveStudio.ReserveStudioCommand;

@AllArgsConstructor
public class ReservationService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ReservationRuleFactory reservationRuleFactory;
    @Autowired
    private final MemberAccountRepository memberAccountRepository;
    @Autowired
    private final BasicUsageFeeSettingRepository basicUsageFeeSettingRepository;
    @Autowired
    private final PackFeeSettingRepository packFeeSettingRepository;
    @Autowired
    private final CancellationFeeSettingRepository cancellationFeeSettingRepository;
    @Autowired
    private final CurrentDate currentDate;

    public void handle(@NonNull ReserveStudioCommand command) {
        reservationRepository.lock();

        var reservationRule = reservationRuleFactory.create(
                command.getStudioId(),
                command.getPracticeType(),
                command.getUsageEquipments().getUsageEquipmentIds())
                .orElseThrow();

        var basicUsageFeeSetting = basicUsageFeeSettingRepository.findAll();

        var packFeeSetting = packFeeSettingRepository.findAll();

        var reservation = Reservation.create(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                command.getUserInformation(),
                command.getPracticeType(),
                command.getUsageEquipments(),
                reservationRule,
                currentDate.now());

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        overlappedReservations.validateDuplicated(reservation)
                .ifPresent(Error::throwError);

        overlappedReservations.validateUsageEquipmentsOutOfStocks(reservation, reservationRule)
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
                command.getUsageEquipments().getUsageEquipmentIds())
                .orElseThrow();

        var reservation = Reservation.createByMember(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                memberAccount,
                command.getPracticeType(),
                command.getUsageEquipments(),
                reservationRule,
                currentDate.now());

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        overlappedReservations.validateDuplicated(reservation)
                .ifPresent(Error::throwError);

        overlappedReservations.validateUsageEquipmentsOutOfStocks(reservation, reservationRule)
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
                command.getUsageEquipments().getUsageEquipmentIds())
                .orElseThrow();

        var cancellationFeeSetting = cancellationFeeSettingRepository.get();

        var currentDateTime = currentDate.now();

        var errorNotification = new ErrorNotification();

        reservation.changeStudioByMember(
                command.getMemberAccountId(),
                command.getStudioId(),
                cancellationFeeSetting.cancellationFeeRates(),
                currentDateTime,
                errorNotification);

        reservation.changeUsageTimeByMember(
                command.getMemberAccountId(),
                command.getUsageTime(),
                cancellationFeeSetting.cancellationFeeRates(),
                currentDateTime,
                errorNotification);

        reservation.changeUserCountByMember(
                command.getMemberAccountId(),
                command.getUserCount(),
                currentDateTime);

        reservation.changePracticeTypeByMember(
                command.getMemberAccountId(),
                command.getPracticeType(),
                cancellationFeeSetting.cancellationFeeRates(),
                currentDateTime,
                errorNotification);

        reservation.changeUsageEquipmentsByMember(
                command.getMemberAccountId(),
                command.getUsageEquipments(),
                currentDateTime);

        errorNotification.throwIfHasErrors("予約を変更できません。");

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        overlappedReservations.validateDuplicated(reservation)
                .ifPresent(Error::throwError);

        overlappedReservations.validateUsageEquipmentsOutOfStocks(reservation, reservationRule)
                .ifPresent(Error::throwError);

        reservationRepository.update(reservation);
    }

    public void handle(@NonNull CancelReservationByMemberCommand command) {
        var reservation = reservationRepository.findBy(command.getReservationId())
                .orElseThrow(ReservationNotFoundException::new);

        if (reservation.isChangedByOther(command.getVersion()))
            throw new ReservationOptimisticLockException();

        var cancellationFeeSetting = cancellationFeeSettingRepository.get();

        var errorNotification = new ErrorNotification();

        reservation.cancelByMember(
                command.getMemberAccountId(),
                currentDate.now(),
                cancellationFeeSetting.cancellationFeeRates(),
                errorNotification);

        errorNotification.throwIfHasErrors("予約をキャンセルできません。");

        var updateResult = reservationRepository.update(reservation);

        if (updateResult == ReservationRepository.UpdateResults.CHANGED_BY_OTHER)
            throw new ReservationOptimisticLockException();
    }
}
