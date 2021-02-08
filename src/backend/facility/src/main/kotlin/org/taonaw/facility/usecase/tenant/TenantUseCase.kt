package org.taonaw.facility.usecase.tenant

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.tenant.Tenant
import org.taonaw.facility.domain.model.tenant.TenantRepository
import org.taonaw.facility.usecase.TenantNotFound
import org.taonaw.facility.usecase.tenant.changeTenant.ChangeTenantCommand
import org.taonaw.facility.usecase.tenant.registerTenant.RegisterTenantCommand

@Component
class TenantUseCase(
        val tenantRepository: TenantRepository) {

    fun handle(command: RegisterTenantCommand) {
        val tenant = Tenant.create(command.tenantName, command.openingHour)

        tenantRepository.save(tenant)
    }

    fun handle(command: ChangeTenantCommand) {
        var tenant = tenantRepository.findBy(command.tenantId) ?: throw TenantNotFound()

        tenant = tenant.change(command.tenantName, command.openingHour)

        tenantRepository.save(tenant)
    }
}