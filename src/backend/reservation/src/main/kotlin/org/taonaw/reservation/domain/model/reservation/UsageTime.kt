package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.DateTimeRange
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
        require(start.isBefore(end)) { "終了日時を開始日時より後でなければなりません。" }
    }

    fun splitMinUnit(): List<UsageTime> {
        // TODO: 実装する
        return listOf(this)
    }

    fun durationAsMinutes(): Long {
        return ChronoUnit.MINUTES.between(start, end)
    }

    fun isDurationAsMinutesEqual(minutes: Long): Boolean {
        return durationAsMinutes() == minutes
    }

    fun isDecrease(other: UsageTime): Boolean {
        return duration() < other.duration()
    }

    private fun duration(): Duration {
        return Duration.between(start, end)
    }
}