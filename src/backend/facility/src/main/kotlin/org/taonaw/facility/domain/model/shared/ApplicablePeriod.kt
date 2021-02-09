package org.taonaw.facility.domain.model.shared

import java.time.LocalDate

data class ApplicablePeriod(
        val start: LocalDate,
        val end: LocalDate) {

    init {
        require(start < end) { "終了日時を開始日時より後でなければなりません。" }
    }

    fun isOverlapping(other: ApplicablePeriod): Boolean {
        return start <= other.end && other.start <= end
    }
}