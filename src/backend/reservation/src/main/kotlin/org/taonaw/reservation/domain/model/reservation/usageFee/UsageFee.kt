package org.taonaw.reservation.domain.model.reservation.usageFee

data class UsageFee(
        val basicFees: BasicFee,
        val packFee: PackFee,
        val rentalEquipmentFee: RentalEquipmentFee) {
}