package org.taonaw.facility.domain.model.equipment

data class EquipmentName(val value: String) {

    companion object {
        const val LENGTH_MIN = 1
        const val LENGTH_MAX = 50
    }

    init {
        require(value.length in LENGTH_MIN..LENGTH_MAX) {
            "機材名は $LENGTH_MIN 文字以上 $LENGTH_MAX 文字以下でなければなりません。"
        }
    }
}