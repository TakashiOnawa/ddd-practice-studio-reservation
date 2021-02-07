package org.taonaw.facility.domain.model.studio

class Studio private constructor(
        val studioId: StudioId,
        val studioName: StudioName,
        val studioUsableStatus: StudioUsableStatus,
        val startTime: StartTime){

    companion object {
        fun create(studioName: StudioName, startTime: StartTime): Studio {
            return Studio(StudioId.newId(), studioName, StudioUsableStatus.SUSPENDED, startTime)
        }
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