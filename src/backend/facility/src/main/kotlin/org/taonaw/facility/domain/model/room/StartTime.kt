package org.taonaw.facility.domain.model.room

enum class StartTime(
        private val value: Int,
        private val startMinutes: Int) {

    ON_THE_HOUR(1, 0),
    ON_THE_HALF_HOUR(2, 30);
}