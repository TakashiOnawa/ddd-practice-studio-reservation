package org.taonaw.reservation.domain.model.reservationPolicy

import org.taonaw.reservation.domain.model.equipment.EquipmentId
import org.taonaw.reservation.domain.shared.exception.Err

class OpeningHourErr : Err("営業時間外です。")

class StartTimeErr : Err("開始時間が不正です。")

class AcceptingReservationStartDateErr : Err("予約受付を開始していません。")

class MaxUserCountErr : Err("最大利用人数を超えています。")

class MaxRentalEquipmentQuantityErr(val equipmentIds: List<EquipmentId>) : Err("最大レンタル機材数を超えています。")


