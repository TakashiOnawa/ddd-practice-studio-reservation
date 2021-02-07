package org.taonaw.facility.domain.model.practiceType

data class MaxUserCount(val value: Int) {

    companion object {
        const val MIN = 1
        const val MAX = 99
    }

    init {
        require(value in MIN..MAX) {
            "最大利用者数は $MIN 人以上 $MAX 人以下でなければなりません。"
        }
    }
}