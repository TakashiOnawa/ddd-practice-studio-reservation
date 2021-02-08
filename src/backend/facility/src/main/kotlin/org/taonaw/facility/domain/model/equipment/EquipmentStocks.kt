package org.taonaw.facility.domain.model.equipment

data class EquipmentStocks(val value: Int) {

    companion object {
        const val MIN = 0
        const val MAX = 99
    }

    init {
        require(value in MIN..MAX) {
            "機材在庫数は $MIN 個以上 $MAX 個以下でなければなりません。"
        }
    }
}