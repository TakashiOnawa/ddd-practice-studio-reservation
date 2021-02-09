package org.taonaw.facility.domain.model.studio

class Studio private constructor(
        val studioId: StudioId,
        val studioName: StudioName,
        val studioSize: StudioSize,
        val startTime: StartTime,
        val studioUsableStatus: StudioUsableStatus,
        val maxRentalEquipmentQuantities: MaxRentalEquipmentQuantities) {

    companion object {
        fun create(
                studioName: StudioName,
                studioSize: StudioSize,
                startTime: StartTime,
                maxRentalEquipmentQuantities: MaxRentalEquipmentQuantities): Studio {

            maxRentalEquipmentQuantities.validateDuplicated()?.throwErr()

            return Studio(StudioId.newId(), studioName, studioSize, startTime, StudioUsableStatus.SUSPENDED, maxRentalEquipmentQuantities)
        }
    }

    fun change(
            studioName: StudioName,
            studioSize: StudioSize,
            startTime: StartTime,
            studioUsableStatus: StudioUsableStatus,
            maxRentalEquipmentQuantities: MaxRentalEquipmentQuantities): Studio {

        maxRentalEquipmentQuantities.validateDuplicated()?.throwErr()

        return Studio(studioId, studioName, studioSize, startTime, studioUsableStatus, maxRentalEquipmentQuantities)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Studio

        if (studioId != other.studioId) return false

        return true
    }

    override fun hashCode(): Int {
        return studioId.hashCode()
    }
}