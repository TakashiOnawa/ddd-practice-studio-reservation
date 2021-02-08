package org.taonaw.facility.usecase.room

import org.springframework.stereotype.Component
import org.taonaw.facility.domain.model.room.Room
import org.taonaw.facility.domain.model.room.RoomRepository
import org.taonaw.facility.usecase.RoomNotFound
import org.taonaw.facility.usecase.room.changeRoom.ChangeRoomCommand
import org.taonaw.facility.usecase.room.registerRoom.RegisterRoomCommand

@Component
class RoomUseCase(private val roomRepository: RoomRepository) {

    fun handle(command: RegisterRoomCommand) {
        val room = Room.create(command.roomName, command.startTime)

        roomRepository.save(room)
    }

    fun handle(command: ChangeRoomCommand) {
        var room = roomRepository.findBy(command.roomId) ?: throw RoomNotFound()

        room = room.change(command.roomName, command.startTime, command.roomUsableStatus)

        roomRepository.save(room)
    }
}