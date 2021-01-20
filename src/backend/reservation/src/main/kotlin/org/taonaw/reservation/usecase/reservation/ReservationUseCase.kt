package org.taonaw.reservation.usecase.reservation

import org.springframework.stereotype.Component
import org.taonaw.reservation.domain.model.equipment.EquipmentRepository
import org.taonaw.reservation.domain.model.reservation.Reservation
import org.taonaw.reservation.domain.model.reservation.ReservationRepository
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicyRepository
import org.taonaw.reservation.usecase.reservation.reserveStudio.ReserveStudioCommand
import java.time.LocalDateTime

@Component
class ReservationUseCase(
        private val reservationRepository: ReservationRepository,
        private val reservationPolicyRepository: ReservationPolicyRepository,
        private val equipmentRepository: EquipmentRepository) {

    fun handle(command: ReserveStudioCommand) {
        reservationRepository.lock()

        val reservationPolicy = reservationPolicyRepository.findBy(
                command.studioId,
                command.practiceType,
                command.rentalEquipments.equipmentIds())

        val reservation = Reservation.create(
                command.memberId,
                command.studioId,
                command.usageTime,
                command.userCount,
                command.practiceType,
                command.rentalEquipments,
                LocalDateTime.now(),
                reservationPolicy)

        val overlappingReservations = reservationRepository.findBy(command.usageTime)
        overlappingReservations.validateDuplicated(reservation)

        val equipments = equipmentRepository.findBy(command.rentalEquipments.equipmentIds())
        overlappingReservations.validateUsageEquipmentsOutOfStocks(reservation, equipments)

        reservationRepository.save(reservation)
    }
}