package org.taonaw.facility.domain.model.equipment

data class EquipmentRentalFee(val value: Long) {

    companion object {
        const val MIN = 0L
        const val MAX = 9999999999L
    }

    init {
        require(value in MIN..MAX) {
            "機材レンタル料金は $MIN 円以上 $MAX 円以下でなければなりません。"
        }
    }
}