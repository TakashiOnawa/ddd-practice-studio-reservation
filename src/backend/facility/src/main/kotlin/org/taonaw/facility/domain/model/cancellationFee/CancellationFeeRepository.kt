package org.taonaw.facility.domain.model.cancellationFee

interface CancellationFeeRepository {
    fun lock()
    fun findAll(): CancellationFees
    fun findBy(cancellationFeeId: CancellationFeeId): CancellationFee?
    fun save(cancellationFee: CancellationFee)
}