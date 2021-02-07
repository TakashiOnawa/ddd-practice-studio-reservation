package org.taonaw.facility.domain.model.studio

interface StudioRepository {
    fun findBy(studioId: StudioId): Studio?
    fun save(studio: Studio)
}