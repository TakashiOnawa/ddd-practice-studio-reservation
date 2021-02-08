package org.taonaw.facility.usecase.room.registerRoom

import org.taonaw.facility.domain.model.room.StartTime
import org.taonaw.facility.domain.model.room.RoomName

data class RegisterRoomCommand(
        val roomName: RoomName,
        val startTime: StartTime) {
}