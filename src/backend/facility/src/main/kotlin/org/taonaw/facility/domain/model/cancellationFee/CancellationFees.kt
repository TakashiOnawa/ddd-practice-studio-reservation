package org.taonaw.facility.domain.model.cancellationFee

import org.taonaw.facility.domain.shared.exception.Err

class CancellationFees(items: List<CancellationFee>) {
    val items: List<CancellationFee> = items.toList()

    fun validateDuplicated(cancellationFee: CancellationFee): Err? {
        if (items.any { it.isDuplicated(cancellationFee) }) return CancellationFeeDuplicatedErr()
        return null
    }
}