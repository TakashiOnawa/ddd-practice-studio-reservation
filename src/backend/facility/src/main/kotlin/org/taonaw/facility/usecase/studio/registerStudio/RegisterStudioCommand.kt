package org.taonaw.facility.usecase.studio.registerStudio

import org.taonaw.facility.domain.model.studio.MaxRentalEquipmentQuantities
import org.taonaw.facility.domain.model.studio.StartTime
import org.taonaw.facility.domain.model.studio.StudioName
import org.taonaw.facility.domain.model.studio.StudioSize

data class RegisterStudioCommand(
        val studioName: StudioName,
        val studioSize: StudioSize,
        val startTime: StartTime,
        val maxRentalEquipmentQuantities: MaxRentalEquipmentQuantities) {
}