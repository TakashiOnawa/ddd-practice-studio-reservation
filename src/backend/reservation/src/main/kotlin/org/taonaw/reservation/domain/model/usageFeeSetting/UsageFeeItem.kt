package org.taonaw.reservation.domain.model.usageFeeSetting

import org.taonaw.reservation.domain.model.shared.Fee

data class UsageFeeItem(
        val fee: Fee,
        val usageFeeSpecifications: List<UsageFeeSpecification>) {
}