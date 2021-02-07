package org.taonaw.facility.domain.model.practiceType

class PracticeType private constructor(
        val type: Type,
        val maxUserCount: MaxUserCount,
        val acceptingReservationStartDate: AcceptingReservationStartDate) {

    enum class Type(val value: Int) {
        BAND(1),
        PERSONAL(2)
    }

    companion object {
        fun default(type: Type): PracticeType {
            return when(type) {
                Type.BAND -> PracticeType(
                        type,
                        MaxUserCount(MaxUserCount.MAX),
                        AcceptingReservationStartDate.DateType.MONTHS_AGO.create(2))
                Type.PERSONAL -> PracticeType(
                        type,
                        MaxUserCount(MaxUserCount.MAX),
                        AcceptingReservationStartDate.DateType.DAYS_AGO.create(1))
            }
        }
    }

    fun change(
            maxUserCount: MaxUserCount,
            acceptingReservationStartDate: AcceptingReservationStartDate): PracticeType {

        return PracticeType(type, maxUserCount, acceptingReservationStartDate)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as PracticeType

        if (type != other.type) return false

        return true
    }

    override fun hashCode(): Int {
        return type.hashCode()
    }
}