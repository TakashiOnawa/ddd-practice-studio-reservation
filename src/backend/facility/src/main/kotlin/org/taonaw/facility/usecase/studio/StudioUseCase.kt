package org.taonaw.facility.usecase.studio

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.studio.Studio
import org.taonaw.facility.domain.model.studio.StudioRepository
import org.taonaw.facility.usecase.StudioNotFound
import org.taonaw.facility.usecase.studio.changeStudio.ChangeStudioCommand
import org.taonaw.facility.usecase.studio.registerStudio.RegisterStudioCommand

@Component
class StudioUseCase(private val studioRepository: StudioRepository) {

    fun handle(command: RegisterStudioCommand) {
        val studio = Studio.create(command.studioName, command.studioSize, command.startTime)

        studioRepository.save(studio)
    }

    fun handle(command: ChangeStudioCommand) {
        var studio = studioRepository.findBy(command.studioId) ?: throw StudioNotFound()

        studio = studio.change(command.studioName, command.studioSize, command.startTime, command.studioUsableStatus)

        studioRepository.save(studio)
    }
}