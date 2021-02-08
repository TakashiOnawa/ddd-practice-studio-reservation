package org.taonaw.facility.domain.model.room

data class RoomName(val value: String) {

    companion object {
        const val LENGTH_MIN = 1
        const val LENGTH_MAX = 20
    }

    init {
        require(value.length in LENGTH_MIN..LENGTH_MAX) {
            "スタジオ名は $LENGTH_MIN 文字以上 $LENGTH_MAX 文字以下でなければなりません。"
        }
    }
}