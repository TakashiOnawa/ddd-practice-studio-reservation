package org.taonaw.facility.usecase.tenant.changeTenant

import org.taonaw.facility.domain.model.tenant.OpeningHour
import org.taonaw.facility.domain.model.tenant.TenantId
import org.taonaw.facility.domain.model.tenant.TenantName

data class ChangeTenantCommand(
        val tenantId: TenantId,
        val tenantName: TenantName,
        val openingHour: OpeningHour) {
}