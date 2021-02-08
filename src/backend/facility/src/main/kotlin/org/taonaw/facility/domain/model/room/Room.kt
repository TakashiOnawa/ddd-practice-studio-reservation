package org.taonaw.facility.domain.model.room

class Room private constructor(
        val roomId: RoomId,
        val roomName: RoomName,
        val startTime: StartTime,
        val roomUsableStatus: RoomUsableStatus){

    companion object {
        fun create(roomName: RoomName, startTime: StartTime): Room {
            return Room(RoomId.newId(), roomName, startTime, RoomUsableStatus.SUSPENDED)
        }
    }

    fun change(
            roomName: RoomName,
            startTime: StartTime,
            roomUsableStatus: RoomUsableStatus): Room {

        return Room(roomId, roomName, startTime, roomUsableStatus)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Room

        if (roomId != other.roomId) return false

        return true
    }

    override fun hashCode(): Int {
        return roomId.hashCode()
    }
}