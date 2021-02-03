package org.taonaw.reservation.domain.model.reservation.usageFee

data class RentalEquipmentFee(val detailsList: List<RentalEquipmentFeeDetails>) {

    companion object {
        fun nothing(): RentalEquipmentFee {
            return RentalEquipmentFee(listOf())
        }
    }
}