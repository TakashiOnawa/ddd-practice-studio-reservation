package org.taonaw.studio_reservation.usecase.command.reservation.reserveStudio;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountRepository;
import org.taonaw.studio_reservation.domain.model.reservation.*;
import org.taonaw.studio_reservation.domain.shared.exception.Error;
import org.taonaw.studio_reservation.usecase.command.exception.MemberAccountNotFoundException;

@AllArgsConstructor
public class ReserveStudioService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ReservationRuleFactory reservationRuleFactory;
    @Autowired
    private final MemberAccountRepository memberAccountRepository;

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
                reservationRule);

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

        var reservation = Reservation.create(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                new UserInformation(memberAccount.getName(), memberAccount.getPhoneNumber()),
                command.getPracticeType(),
                new UsageEquipments(command.getUsageEquipments()),
                reservationRule);

        var overlappedReservations = reservationRepository.findBy(reservation.usageTime());

        reservationRule.validateReservationDuplication(reservation, overlappedReservations)
                .ifPresent(Error::throwError);

        reservationRule.validateUsageEquipmentsOutOfStocks(ReservedUsageEquipments.create(overlappedReservations, reservation))
                .ifPresent(Error::throwError);

        reservationRepository.add(reservation);
    }
}
