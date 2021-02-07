package org.taonaw.facility.usecase.practiceType

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.practiceType.PracticeTypeRepository
import org.taonaw.facility.usecase.practiceType.changePracticeType.ChangePracticeTypeCommand

@Component
class PracticeTypeUseCase(val practiceTypeRepository: PracticeTypeRepository) {

    fun handle(command: ChangePracticeTypeCommand) {
        var practiceType = practiceTypeRepository.findBy(command.type)

        practiceType.change(command.maxUserCount, command.acceptingReservationStartDate)

        practiceTypeRepository.save(practiceType)
    }
}