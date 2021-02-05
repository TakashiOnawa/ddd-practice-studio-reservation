package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.model.shared.PracticeType
import org.taonaw.reservation.domain.model.shared.StudioId

interface ReservationPolicyRepository {
    fun findBy(studioId: StudioId, practiceType: PracticeType, equipments: List<EquipmentId>): ReservationPolicy?
}