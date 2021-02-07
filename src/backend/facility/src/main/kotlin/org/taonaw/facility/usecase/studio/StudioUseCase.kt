package org.taonaw.facility.usecase.studio

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.studio.Studio
import org.taonaw.facility.domain.model.studio.StudioRepository
import org.taonaw.facility.usecase.studio.registerStudio.RegisterStudioCommand

@Component
class StudioUseCase(private val studioRepository: StudioRepository) {

    fun handle(command: RegisterStudioCommand) {
        val studio = Studio.create(command.studioName, command.startTime)

        studioRepository.save(studio)
    }
}