package org.taonaw.facility.domain.model.tenant

import java.time.LocalTime

data class OpeningHour(
        val start: LocalTime,
        val end: LocalTime) {

    init {
        require(start.second == 0 && start.nano == 0) { "開始日時に秒の指定はできません。" }
        require(end.second == 0 && end.nano == 0) { "終了日時に秒の指定はできません。" }
    }
}