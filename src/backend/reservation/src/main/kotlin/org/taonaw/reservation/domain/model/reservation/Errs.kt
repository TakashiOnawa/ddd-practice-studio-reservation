package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.shared.exception.Err

class ReservationDuplicatedErr : Err("予約が重複しています。")

class RentalEquipmentsOutOfStocksErr(val equipmentIds: List<EquipmentId?>) : Err("レンタル機材の在庫に余りがありません。")
