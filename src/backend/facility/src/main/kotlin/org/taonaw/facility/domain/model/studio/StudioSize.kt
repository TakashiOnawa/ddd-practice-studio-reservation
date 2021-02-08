package org.taonaw.facility.domain.model.studio

data class StudioSize(val value: Double) {

    companion object {
        const val MIN = 0.1
        const val MAX = 100.0
    }

    init {
        require(value in MIN..MAX) {
            "スタジオの広さは $MIN 畳以上 $MAX 畳以下でなければなりません。"
        }
    }
}