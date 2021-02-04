package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.reservation.UsageTime
import org.taonaw.reservation.domain.model.reservation.usageFee.UsageFeeDetails

class UsageFeeSpecifications(private val items: List<UsageFeeSpecification>) {

    fun calculateUsageFeeDetails(condition: UsageFeeCondition): List<UsageFeeDetails> {
        val usageFeeDetailsList = mutableListOf<UsageFeeDetails>()

        // 時間帯ごとの利用料金仕様を取得する。
        val eachTimeRangeUsageFeeSpecifications = items
                // 条件に一致する利用料金仕様に絞り込む。
                .filter { (it.practiceTypeTypeSpecification == null || it.practiceTypeTypeSpecification.isSatisfiedBy(condition))
                        .and(it.studioSpecification == null || it.studioSpecification.isSatisfiedBy(condition))
                        .and(it.dayTypeSpecification == null || it.dayTypeSpecification.isSatisfiedBy(condition))
                        .and(it.userCountSpecification == null || it.userCountSpecification.isSatisfiedBy(condition))
                        .and(it.timeRangeSpecification.isSatisfiedBy(condition))
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
                        ?: continue

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