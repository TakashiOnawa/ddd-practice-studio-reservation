package org.taonaw.reservation.usecase.reservation

import org.springframework.stereotype.Component
import org.taonaw.reservation.domain.model.reservation.Reservation
import org.taonaw.reservation.domain.model.reservation.ReservationRepository
import org.taonaw.reservation.domain.model.reservationPolicy.ReservationPolicyRepository
import org.taonaw.reservation.usecase.reservation.reserveStudio.ReserveStudioCommand
import java.time.LocalDateTime

@Component
class ReservationUseCase(
        private val reservationRepository: ReservationRepository,
        private val reservationPolicyRepository: ReservationPolicyRepository) {

    fun handle(command: ReserveStudioCommand) {

        // TODO: 排他制御

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

        // TODO: 機材の在庫チェック

        reservationRepository.save(reservation)
    }
}