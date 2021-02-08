package org.taonaw.facility.usecase.tenant

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.tenant.Tenant
import org.taonaw.facility.domain.model.tenant.TenantRepository
import org.taonaw.facility.usecase.tenant.registerTenant.RegisterTenantCommand

@Component
class TenantUseCase(
        val tenantRepository: TenantRepository) {

    fun handle(command: RegisterTenantCommand) {
        val studio = Tenant.create(command.tenantName, command.openingHour)

        tenantRepository.save(studio)
    }
}