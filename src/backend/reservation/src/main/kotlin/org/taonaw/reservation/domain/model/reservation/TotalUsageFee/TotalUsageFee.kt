package org.taonaw.reservation.domain.model.reservation.TotalUsageFee

data class TotalUsageFee(
        val basicFees: List<BasicFee>,
        val packFee: PackFee,
        val rentalEquipmentFees: List<RentalEquipmentFee>) {
}