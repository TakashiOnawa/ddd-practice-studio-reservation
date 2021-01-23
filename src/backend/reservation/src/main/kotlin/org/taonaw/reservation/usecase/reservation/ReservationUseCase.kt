package org.taonaw.reservation.usecase.reservation

import org.springframework.stereotype.Component
import org.taonaw.reservation.domain.model.cancellationFeeSetting.CancellationFeeSettingRepository
import org.taonaw.reservation.domain.model.equipment.EquipmentRepository
import org.taonaw.reservation.domain.model.reservation.Reservation
import org.taonaw.reservation.domain.model.reservation.ReservationRepository
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicyRepository
import org.taonaw.reservation.domain.model.shared.DateTimeGenerator
import org.taonaw.reservation.domain.model.usageFeeSetting.UsageFeeSettingRepository
import org.taonaw.reservation.usecase.reservation.changeReservation.ChangeReservationCommand
import org.taonaw.reservation.usecase.reservation.reserveStudio.ReserveStudioCommand

@Component
class ReservationUseCase(
        private val dateTimeGenerator: DateTimeGenerator,
        private val reservationRepository: ReservationRepository,
        private val reservationPolicyRepository: ReservationPolicyRepository,
        private val equipmentRepository: EquipmentRepository,
        private val usageFeeSettingRepository: UsageFeeSettingRepository,
        private val cancellationFeeSettingRepository: CancellationFeeSettingRepository) {

    fun handle(command: ReserveStudioCommand) {
        reservationRepository.lock()

        val reservationPolicy = reservationPolicyRepository.findBy(
                command.studioId,
                command.practiceType,
                command.rentalEquipments.equipmentIds())

        val usageFeeSetting = usageFeeSettingRepository.get()

        val equipments = equipmentRepository.findBy(command.rentalEquipments.equipmentIds())

        val reservation = Reservation.create(
                command.memberId,
                command.studioId,
                command.usageTime,
                command.userCount,
                command.practiceType,
                command.rentalEquipments,
                reservationPolicy,
                usageFeeSetting,
                equipments,
                dateTimeGenerator.currentDateTime())

        val overlappingReservations = reservationRepository.findBy(command.usageTime)
        overlappingReservations.validateDuplicated(reservation)
        overlappingReservations.validateUsageEquipmentsOutOfStocks(reservation, equipments)

        reservationRepository.save(reservation)
    }

    fun handle(command: ChangeReservationCommand) {
        reservationRepository.lock()

        val reservation = reservationRepository.findBy(command.reservationId) ?: throw Exception()

        val reservationPolicy = reservationPolicyRepository.findBy(
                command.studioId,
                command.practiceType,
                command.rentalEquipments.equipmentIds())

        val cancellationFeeSetting = cancellationFeeSettingRepository.get()

        val usageFeeSetting = usageFeeSettingRepository.get()

        val equipments = equipmentRepository.findBy(command.rentalEquipments.equipmentIds())

        reservation.change(
                command.memberId,
                command.studioId,
                command.usageTime,
                command.userCount,
                command.practiceType,
                command.rentalEquipments,
                reservationPolicy,
                cancellationFeeSetting,
                usageFeeSetting,
                equipments,
                dateTimeGenerator.currentDateTime())

        val overlappingReservations = reservationRepository.findBy(command.usageTime)
        overlappingReservations.validateDuplicated(reservation)
        overlappingReservations.validateUsageEquipmentsOutOfStocks(reservation, equipments)

        reservationRepository.save(reservation)
    }
}