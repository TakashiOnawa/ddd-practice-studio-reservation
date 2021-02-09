package org.taonaw.facility.domain.model.studio

import org.taonaw.facility.domain.model.equipmentCategory.EquipmentCategoryId
import org.taonaw.facility.domain.shared.exception.Err

class MaxRentalEquipmentQuantityDuplicatedErr(val equipmentCategoryIds: List<EquipmentCategoryId>) : Err("最大レンタル機材数が重複しています。")