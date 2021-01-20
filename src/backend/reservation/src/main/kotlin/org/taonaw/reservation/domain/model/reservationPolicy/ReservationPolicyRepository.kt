package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.reservation.PracticeType
import org.taonaw.reservation.domain.model.shared.EquipmentId
import org.taonaw.reservation.domain.model.shared.StudioId

interface ReservationPolicyRepository {
    fun findBy(studioId: StudioId, practiceType: PracticeType, equipments: List<EquipmentId>): ReservationPolicy
}