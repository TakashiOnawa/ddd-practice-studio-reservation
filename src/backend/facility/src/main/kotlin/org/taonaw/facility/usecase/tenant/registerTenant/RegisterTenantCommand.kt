package org.taonaw.facility.usecase.tenant.registerTenant

import org.taonaw.facility.domain.model.tenant.OpeningHour
import org.taonaw.facility.domain.model.tenant.TenantName

data class RegisterTenantCommand(
        val tenantName: TenantName,
        val openingHour: OpeningHour) {
}