package org.taonaw.reservation.domain.model.usageFeeSetting

data class BasicFeeSetting(val usageFees: List<UsageFee>) {

    fun calculate(calculationCondition: UsageFeeCalculationCondition) {

        val dateTimeRangeFees = usageFees.map { it.getDateTimeRangeFee(calculationCondition) }


    }
}