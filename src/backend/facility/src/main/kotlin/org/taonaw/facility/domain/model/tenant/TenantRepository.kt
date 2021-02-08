package org.taonaw.facility.domain.model.tenant

interface TenantRepository {
    fun findBy(tenantId: TenantId): Tenant?
    fun save(tenant: Tenant)
}