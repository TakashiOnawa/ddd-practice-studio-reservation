package org.taonaw.studio_reservation.usecase.command.reservation;

import lombok.AllArgsConstructor;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.taonaw.studio_reservation.domain.model.equipment.Equipment;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentId;
import org.taonaw.studio_reservation.domain.model.equipment.EquipmentRepository;
import org.taonaw.studio_reservation.domain.model.memberAccount.MemberAccountRepository;
import org.taonaw.studio_reservation.domain.model.reservation.*;
import org.taonaw.studio_reservation.domain.model.reservation.usageEquipment.UsageEquipment;
import org.taonaw.studio_reservation.domain.shared.exception.Error;
import org.taonaw.studio_reservation.usecase.command.exception.EquipmentNotFoundException;
import org.taonaw.studio_reservation.usecase.command.exception.MemberAccountNotFoundException;
import org.taonaw.studio_reservation.usecase.command.exception.ReservationRuleFoundException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
public class ReserveStudioService {
    @Autowired
    private final ReservationRepository reservationRepository;
    @Autowired
    private final ReservationRuleFactory reservationRuleFactory;
    @Autowired
    private final EquipmentRepository equipmentRepository;
    @Autowired
    private final MemberAccountRepository memberAccountRepository;

    public void handle(@NonNull ReserveStudioCommand command) {
        reservationRepository.lock();

        var reservationRule = reservationRuleFactory.create(command.getStudioId(), command.getPracticeType())
                .orElseThrow(ReservationRuleFoundException::new);

        var equipments = getEquipments(command.getUsageEquipments());

        var reservation = Reservation.create(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                command.getUserInformation(),
                command.getPracticeType(),
                createUsageEquipments(command.getUsageEquipments(), equipments),
                reservationRule);

        validateReservation(reservation, equipments);

        reservationRepository.add(reservation);
    }

    public void handle(@NonNull ReserveStudioByMemberCommand command) {
        reservationRepository.lock();

        var memberAccount = memberAccountRepository.findBy(command.getMemberAccountId())
                .orElseThrow(MemberAccountNotFoundException::new);

        var reservationSetting = reservationRuleFactory.create(command.getStudioId(), command.getPracticeType())
                .orElseThrow(ReservationRuleFoundException::new);

        var equipments = getEquipments(command.getUsageEquipments());

        var reservation = Reservation.create(
                command.getStudioId(),
                command.getUsageTime(),
                command.getUserCount(),
                new UserInformation(memberAccount.getName(), memberAccount.getPhoneNumber()),
                command.getPracticeType(),
                createUsageEquipments(command.getUsageEquipments(), equipments),
                reservationSetting);

        validateReservation(reservation, equipments);

        reservationRepository.add(reservation);
    }

    private List<Equipment> getEquipments(List<UsageEquipmentDto> usageEquipmentDtoList) {
        var equipmentMap = new HashMap<EquipmentId, Equipment>();
        for (var usageEquipmentDto : usageEquipmentDtoList) {
            if (equipmentMap.containsKey(usageEquipmentDto.getEquipmentId()))
                continue;

            var equipment = equipmentRepository.findBy(usageEquipmentDto.getEquipmentId())
                    .orElseThrow(EquipmentNotFoundException::new);

            equipmentMap.put(equipment.getId(), equipment);
        }
        return new ArrayList<>(equipmentMap.values());
    }

    private UsageEquipments createUsageEquipments(
            List<UsageEquipmentDto> usageEquipmentDtoList,
            List<Equipment> equipments) {

        var usageEquipments = new ArrayList<UsageEquipment>();
        for (var usageEquipmentDto : usageEquipmentDtoList) {
            var equipment = equipments.stream()
                    .filter(item -> item.getId().equals(usageEquipmentDto.getEquipmentId()))
                    .findFirst()
                    .orElseThrow();

            var usageEquipment = UsageEquipment.create(
                    equipment.getId(),
                    equipment.getCategoryId(),
                    usageEquipmentDto.getQuantity());

            usageEquipments.add(usageEquipment);
        }
        return new UsageEquipments(usageEquipments);
    }

    private void validateReservation(Reservation reservation, List<Equipment> equipments) {
        var overlappedReservations = reservationRepository.findBy(reservation.getUsageTime());

        var validator = new ReservationValidator();
        validator.validateDuplication(reservation, overlappedReservations).ifPresent(Error::throwError);
        validator.validateEquipmentOutOfStocks(reservation, overlappedReservations, equipments).ifPresent(Error::throwError);
    }
}
