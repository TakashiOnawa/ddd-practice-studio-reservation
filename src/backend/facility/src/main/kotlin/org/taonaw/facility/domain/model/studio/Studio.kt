package org.taonaw.facility.domain.model.studio

class Studio private constructor(
        val studioId: StudioId,
        val studioName: StudioName,
        val studioSize: StudioSize,
        val startTime: StartTime,
        val studioUsableStatus: StudioUsableStatus){

    companion object {
        fun create(studioName: StudioName, studioSize: StudioSize, startTime: StartTime): Studio {
            return Studio(StudioId.newId(), studioName, studioSize, startTime, StudioUsableStatus.SUSPENDED)
        }
    }

    fun change(
            studioName: StudioName,
            studioSize: StudioSize,
            startTime: StartTime,
            studioUsableStatus: StudioUsableStatus): Studio {

        return Studio(studioId, studioName, studioSize, startTime, studioUsableStatus)
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