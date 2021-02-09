package org.taonaw.facility.usecase.studio.changeStudio

import org.taonaw.facility.domain.model.studio.*

data class ChangeStudioCommand(
        val studioId: StudioId,
        val studioName: StudioName,
        val studioSize: StudioSize,
        val studioUsableStatus: StudioUsableStatus,
        val startTime: StartTime,
        val maxRentalEquipmentQuantities: MaxRentalEquipmentQuantities) {
}