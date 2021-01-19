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

        val reservationPolicy = reservationPolicyRepository.get()

        val reservation = Reservation.create(
                command.memberId,
                command.studioId,
                command.usageDateTime,
                command.userCount,
                command.practiceType,
                command.rentalEquipments,
                LocalDateTime.now(),
                reservationPolicy)

        // TODO: 予約重複チェック

        // TODO: レンタル機材重複チェック

        reservationRepository.save(reservation)
    }
}