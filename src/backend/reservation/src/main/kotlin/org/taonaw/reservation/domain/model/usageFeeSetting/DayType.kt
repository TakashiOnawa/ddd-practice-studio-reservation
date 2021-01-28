package org.taonaw.reservation.domain.model.usageFeeSetting

import java.time.DayOfWeek
import java.time.LocalDate

enum class DayType(val value: Int) {
    /** 平日 */
    WEEK_DAYS(1),
    /** 土日 */
    WEEKEND(2),
    /** 祝日 */
    PUBLIC_HOLIDAY(3),
    /** 土日祝日 */
    WEEKEND_AND_PUBLIC_HOLIDAY(4),

    SUNDAY(5),
    MONDAY(6),
    TUESDAY(7),
    WEDNESDAY(8),
    THURSDAY(9),
    FRIDAY(10),
    SATURDAY(11);

    fun contains(date: LocalDate, publicHolidays: PublicHolidays): Boolean {
        if (this == PUBLIC_HOLIDAY || this == WEEKEND_AND_PUBLIC_HOLIDAY) {
            if (publicHolidays.isPublicHoliday(date))
                return true
        }

        return when (date.dayOfWeek) {
            DayOfWeek.SUNDAY    -> when (this) { WEEKEND, WEEKEND_AND_PUBLIC_HOLIDAY, SUNDAY -> true else -> false }
            DayOfWeek.MONDAY    -> when (this) { WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY, MONDAY -> true else -> false }
            DayOfWeek.TUESDAY   -> when (this) { WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY, TUESDAY -> true else -> false }
            DayOfWeek.WEDNESDAY -> when (this) { WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY, WEDNESDAY -> true else -> false }
            DayOfWeek.THURSDAY  -> when (this) { WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY, THURSDAY -> true else -> false }
            DayOfWeek.FRIDAY    -> when (this) { WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY, FRIDAY -> true else -> false }
            DayOfWeek.SATURDAY  -> when (this) { WEEK_DAYS, WEEKEND_AND_PUBLIC_HOLIDAY, SATURDAY -> true else -> false }
            else -> throw Error()
        }
    }
}