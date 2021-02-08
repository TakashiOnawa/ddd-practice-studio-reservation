package org.taonaw.facility.domain.model.tenant

class Tenant private constructor(
        val tenantId: TenantId,
        val tenantName: TenantName,
        val openingHour: OpeningHour) {

    companion object {
        fun create(tenantName: TenantName, openingHour: OpeningHour): Tenant {
            return Tenant(TenantId.newId(), tenantName, openingHour)
        }
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tenant

        if (tenantId != other.tenantId) return false

        return true
    }

    override fun hashCode(): Int {
        return tenantId.hashCode()
    }
}