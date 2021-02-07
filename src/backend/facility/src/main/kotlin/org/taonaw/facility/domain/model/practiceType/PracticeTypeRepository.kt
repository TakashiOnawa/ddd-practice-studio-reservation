package org.taonaw.facility.domain.model.practiceType

interface PracticeTypeRepository {
    fun findBy(type: PracticeType.Type): PracticeType
    fun save(practiceType: PracticeType)
}