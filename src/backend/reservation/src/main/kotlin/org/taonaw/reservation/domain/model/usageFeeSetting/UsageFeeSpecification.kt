package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.shared.PracticeType
import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.usageFee.FeeType
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFeeDetails
import org.taonaw.reservation.domain.model.shared.BasicTimeRange
import org.taonaw.reservation.domain.model.shared.Fee
import org.taonaw.reservation.domain.model.shared.StudioId
import org.taonaw.reservation.domain.model.shared.TimeRange
import org.taonaw.reservation.domain.shared.Specification
import java.time.LocalTime

class UsageFeeSpecifications(private val items: List<UsageFeeSpecification>) {

    fun calculateUsageFee(condition: UsageFeeCondition): List<UsageFeeDetails> {
        val usageFeeDetailsList = mutableListOf<UsageFeeDetails>()

        // 時間帯ごとの利用料金仕様を取得する。
        val eachTimeRangeUsageFeeSpecifications = items
                // 条件に一致する利用料金仕様に絞り込む。
                .filter {
                    (it.practiceTypeTypeSpecification == null || it.practiceTypeTypeSpecification.isSatisfiedBy(condition)) &&
                            (it.studioSpecification == null || it.studioSpecification.isSatisfiedBy(condition)) &&
                            (it.dayTypeSpecification == null || it.dayTypeSpecification.isSatisfiedBy(condition)) &&
                            (it.userCountSpecification == null || it.userCountSpecification.isSatisfiedBy(condition)) &&
                            it.timeRangeSpecification.isSatisfiedBy(condition)
                }
                // 時間帯仕様ごとに分割する。
                .groupBy { it.timeRangeSpecification }

        // 時間帯仕様ごとに利用料金を計算する。
        for (timeRangeSpecification in eachTimeRangeUsageFeeSpecifications.keys) {
            // 当該時間帯の利用料金仕様を取得する
            val timeRangeUsageFeeSpecifications = eachTimeRangeUsageFeeSpecifications.getValue(timeRangeSpecification)

            // 当該時間帯の利用時間を取得する。（24 時間を超える利用時間の場合は利用時間内に当該時間帯が複数存在する可能性がある。）
            val timeRangeUsageTimes = condition.usageTime.extract(timeRangeSpecification.timeRange)

            // 当該時間帯の利用時間ごとに料金を計算する。
            for (usageTime in timeRangeUsageTimes) {
                // 利用時間仕様を満たすかつ利用時間仕様が最大の利用料金仕様を取得する。
                val usageFeeSpecification = timeRangeUsageFeeSpecifications
                        .filter { usageTime.durationAsMinutes() >= it.usageTimeSpecification.minutes }
                        .maxByOrNull { it.usageTimeSpecification.minutes }

                if (usageFeeSpecification == null)
                    continue

                if (usageFeeSpecification.usageTimeSpecification.type == UsageTimeSpecification.Type.GREATER_THAN) {
                    // 利用時間仕様が「以上の時間」を示す場合、全利用時間で料金を計算する。
                    usageFeeDetailsList.add(UsageFeeDetails(
                            usageTime,
                            usageFeeSpecification.fee,
                            usageFeeSpecification.feeType))
                } else if (usageFeeSpecification.usageTimeSpecification.type == UsageTimeSpecification.Type.FIXED) {
                    // 利用時間仕様が「固定時間」を示す場合、利用時間の開始から固定時間後までの時間で料金を計算する。
                    usageFeeDetailsList.add(UsageFeeDetails(
                            UsageTime(usageTime.start, usageTime.start.plusMinutes(usageFeeSpecification.usageTimeSpecification.minutes)),
                            usageFeeSpecification.fee,
                            usageFeeSpecification.feeType))
                }
            }
        }

        return usageFeeDetailsList
    }
}

data class UsageFeeSpecification(
        val practiceTypeTypeSpecification: PracticeTypeTypeSpecification?,
        val studioSpecification: StudioSpecification?,
        val dayTypeSpecification: DayTypeSpecification?,
        val userCountSpecification: UserCountSpecification?,
        val timeRangeSpecification: TimeRangeSpecification,
        val usageTimeSpecification: UsageTimeSpecification,
        val fee: Fee,
        val feeType: FeeType) {
}

data class PracticeTypeTypeSpecification(val practiceType: PracticeType) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.practiceType == practiceType
    }
}

data class StudioSpecification(val studioId: StudioId) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.studioId == studioId
    }
}

data class DayTypeSpecification(val dayType: DayType, private val publicHolidays: PublicHolidays) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return dayType.contains(condition.usageTime.start.toLocalDate(), publicHolidays)
    }
}

data class UserCountSpecification(val userCountMin: Int, private val userCountMax: Int) : Specification<UsageFeeCondition> {
    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.userCount.isBetween(userCountMin, userCountMax)
    }
}

data class TimeRangeSpecification(val timeRange: TimeRange) : Specification<UsageFeeCondition> {
    companion object {
        fun default(): TimeRangeSpecification {
            return TimeRangeSpecification(BasicTimeRange(LocalTime.MIN, LocalTime.MIN))
        }
    }

    override fun isSatisfiedBy(condition: UsageFeeCondition): Boolean {
        return condition.usageTime.contains(timeRange)
    }
}

data class UsageTimeSpecification(val minutes: Long, val type: Type) {
    enum class Type {
        GREATER_THAN, FIXED
    }

    companion object {
        fun default(): UsageTimeSpecification {
            return UsageTimeSpecification(UsageTime.MIN_MINUTES_UNIT, Type.GREATER_THAN)
        }
    }
}