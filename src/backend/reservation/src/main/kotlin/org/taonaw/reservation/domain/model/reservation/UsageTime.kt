package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.DateTimeRange
import org.taonaw.reservation.domain.model.shared.TimeRange
import java.time.Duration
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

/**
 * 利用時間
 */
data class UsageTime(
        override val start: LocalDateTime,
        override val end: LocalDateTime) : DateTimeRange {

    companion object {
        const val MIN_MINUTES_UNIT: Long = 60
    }

    init {
        require(!hasStartSeconds()) { "開始日時に秒の指定はできません。" }
        require(!hasEndSeconds()) { "終了日時に秒の指定はできません。" }
        require(durationAsMinutes() % MIN_MINUTES_UNIT == 0L) { "$MIN_MINUTES_UNIT 分単位でなければなりません。" }

        // TODO: このバリデーションは契約とすべき？それともドメイン例外とすべき？
        // 契約 = バグと考えると、呼び出し元でのチェックを強要するが、フロントで前後のチェックをするより、ここでチェックした方が良いように思う。
        // よって、ドメイン例外にして呼び出し元でキャッチできるようにする。
        require(start < end) { "終了日時を開始日時より後でなければなりません。" }
    }

    fun durationAsMinutes(): Long {
        return ChronoUnit.MINUTES.between(start, end)
    }

    fun isDecrease(other: UsageTime): Boolean {
        return duration() < other.duration()
    }

    fun duration(): Duration {
        return Duration.between(start, end)
    }

    fun contains(timeRange: TimeRange): Boolean {
        var currentDate = start.toLocalDate()
        while (currentDate <= end.toLocalDate()) {
            if (timeRange.toDateTimeRange(currentDate).isOverlapping(this))
                return true

            currentDate = currentDate.plusDays(1)
        }
        return false
    }

    fun extract(timeRange: TimeRange): List<UsageTime> {
        val extracted = mutableListOf<UsageTime>()

        // 利用日時の日付を 1 日ずつずらしていく
        var extractCurrentDate = start.toLocalDate()
        while (extractCurrentDate <= end.toLocalDate()) {
            // 現在の切り出し日付における切り出し日時を取得する
            val extractDateTimeRange = timeRange.toDateTimeRange(extractCurrentDate)

            // 切り出し日時が利用日時に被っていない場合は切り出せない
            if (!extractDateTimeRange.isOverlapping(this))
                continue

            // 切り出し開始日時を決定する
            val extractStart = if (extractDateTimeRange.start < start) {
                start
            } else{
                extractDateTimeRange.start
            }

            // 切り出し終了日時を決定する
            val extractEnd = if (extractDateTimeRange.end < end) {
                extractDateTimeRange.end
            } else {
                end
            }

            // 切り出す
            extracted.add(UsageTime(extractStart, extractEnd))

            // 次の日付にずらす
            extractCurrentDate = extractCurrentDate.plusDays(1)
        }

        return extracted
    }

    fun except(other: UsageTime): List<UsageTime> {
        if (start < other.start) {
            if (end > other.start) {
                if (end <= other.end) {
                    return listOf(UsageTime(start, other.start))
                }
                return listOf(UsageTime(start, other.start), UsageTime(other.end, end))
            }
        }

        if (start >= other.start) {
            if (start < other.end) {
                if (end <= other.end) {
                    return listOf()
                }
                return listOf(UsageTime(other.end, end))
            }
        }

        return listOf(this)
    }

    fun except(others: List<UsageTime>): List<UsageTime> {
        val excepted = mutableListOf<UsageTime>()

        var currentStart = start
        var currentEnd = start

        while (currentEnd < end) {
            currentEnd = currentEnd.plusMinutes(MIN_MINUTES_UNIT)
            val currentUsageTime = UsageTime(currentStart, currentEnd)


        }

        TODO()
        return excepted
    }
}