package org.taonaw.facility.usecase.studio.registerStudio

import org.taonaw.facility.domain.model.studio.StudioSize
import org.taonaw.facility.domain.model.studio.StartTime
import org.taonaw.facility.domain.model.studio.StudioName

data class RegisterStudioCommand(
        val studioName: StudioName,
        val studioSize: StudioSize,
        val startTime: StartTime) {
}