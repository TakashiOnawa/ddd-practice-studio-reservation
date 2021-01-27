package org.taonaw.reservation.domain.model.usageFeeSetting

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
}