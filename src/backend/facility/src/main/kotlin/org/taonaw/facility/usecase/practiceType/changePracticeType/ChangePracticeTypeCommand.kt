package org.taonaw.facility.usecase.practiceType.changePracticeType

import org.taonaw.facility.domain.model.practiceType.AcceptingReservationStartDate
import org.taonaw.facility.domain.model.practiceType.MaxUserCount
import org.taonaw.facility.domain.model.practiceType.PracticeType

data class ChangePracticeTypeCommand(
        val type: PracticeType.Type,
        val maxUserCount: MaxUserCount,
        val acceptingReservationStartDate: AcceptingReservationStartDate) {
}