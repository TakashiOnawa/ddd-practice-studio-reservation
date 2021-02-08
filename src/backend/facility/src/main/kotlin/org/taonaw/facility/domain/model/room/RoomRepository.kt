package org.taonaw.facility.domain.model.room

interface RoomRepository {
    fun findBy(roomId: RoomId): Room?
    fun save(room: Room)
}