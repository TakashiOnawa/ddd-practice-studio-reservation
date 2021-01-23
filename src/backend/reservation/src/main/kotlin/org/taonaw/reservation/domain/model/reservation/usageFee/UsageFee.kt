package org.taonaw.reservation.domain.model.reservation.usageFee

data class UsageFee(
        val basicFees: List<BasicFee>,
        val packFee: PackFee,
        val rentalEquipmentFees: List<RentalEquipmentFee>) {
}