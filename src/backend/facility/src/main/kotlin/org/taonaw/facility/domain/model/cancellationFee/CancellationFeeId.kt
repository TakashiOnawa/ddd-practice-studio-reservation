package org.taonaw.facility.domain.model.cancellationFee

import de.huxhorn.sulky.ulid.ULID

data class CancellationFeeId(val value: String) {
    companion object {
        fun newId(): CancellationFeeId {
            return CancellationFeeId(ULID().nextULID())
        }
    }
}