package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.shared.exception.Err

class ReservationDuplicatedErr : Err("予約が重複しています。")

class RentalEquipmentsOutOfStocksErr(val equipmentIds: List<EquipmentId?>) : Err("レンタル機材の在庫に余りがありません。")


class CanNotChangeStudioErr(message: String) : Err(message)

class CanNotChangeUsageTimeErr(message: String) : Err(message)

class CanNotChangeUserCountErr(message: String) : Err(message)

class CanNotChangePracticeTypeErr(message: String) : Err(message)

class CanNotChangeRentalEquipmentsErr(message: String) : Err(message)