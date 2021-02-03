package org.taonaw.reservation.domain.model.reservation.usageFee

data class UsageFee(
        val basicFees: BasicFee,
        val packFee: PackFee,
        val rentalEquipmentFee: RentalEquipmentFee) {

    companion object {
        fun nothing(): UsageFee {
            return UsageFee(BasicFee.nothing(), PackFee.nothing(), RentalEquipmentFee.nothing())
        }
    }
}