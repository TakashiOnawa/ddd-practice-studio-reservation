package org.taonaw.facility.usecase.room.changeRoom

import org.taonaw.facility.domain.model.room.StartTime
import org.taonaw.facility.domain.model.room.RoomId
import org.taonaw.facility.domain.model.room.RoomName
import org.taonaw.facility.domain.model.room.RoomUsableStatus

data class ChangeRoomCommand(
        val roomId: RoomId,
        val roomName: RoomName,
        val roomUsableStatus: RoomUsableStatus,
        val startTime: StartTime) {
}