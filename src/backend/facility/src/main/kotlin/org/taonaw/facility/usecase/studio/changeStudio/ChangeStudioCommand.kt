package org.taonaw.facility.usecase.studio.changeStudio

import org.taonaw.facility.domain.model.studio.StartTime
import org.taonaw.facility.domain.model.studio.StudioId
import org.taonaw.facility.domain.model.studio.StudioName
import org.taonaw.facility.domain.model.studio.StudioUsableStatus

data class ChangeStudioCommand(
        val studioId: StudioId,
        val studioName: StudioName,
        val studioUsableStatus: StudioUsableStatus,
        val startTime: StartTime) {
}