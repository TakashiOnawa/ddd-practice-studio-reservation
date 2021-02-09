package org.taonaw.facility.usecase.cancellationFee

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.cancellationFee.CancellationFee
import org.taonaw.facility.domain.model.cancellationFee.CancellationFeeRepository
import org.taonaw.facility.usecase.CancellationFeeNotFound
import org.taonaw.facility.usecase.cancellationFee.changeCancellationFee.ChangeCancellationFeeCommand
import org.taonaw.facility.usecase.cancellationFee.registerCancellationFee.RegisterCancellationFeeCommand

@Component
class CancellationFeeUseCase(
        val cancellationFeeRepository: CancellationFeeRepository) {

    fun handle(command: RegisterCancellationFeeCommand) {
        cancellationFeeRepository.lock()

        val cancellationFee = CancellationFee.create(command.applicablePeriod, command.cancellationFeeRates)

        save(cancellationFee)
    }

    fun handle(command: ChangeCancellationFeeCommand) {
        var cancellationFee = cancellationFeeRepository.findBy(command.cancellationFeeId) ?: throw CancellationFeeNotFound()

        cancellationFee = cancellationFee.change(command.applicablePeriod, command.cancellationFeeRates)

        save(cancellationFee)
    }

    fun save(cancellationFee: CancellationFee) {
        val allCancellationFees = cancellationFeeRepository.findAll()
        allCancellationFees.validateDuplicated(cancellationFee)?.throwErr()
    }
}