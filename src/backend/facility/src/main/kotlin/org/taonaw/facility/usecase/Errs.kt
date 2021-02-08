package org.taonaw.facility.usecase

class RoomNotFound() : Exception("部屋が見つかりません。")
class EquipmentNotFound() : Exception("機材が見つかりません。")
class EquipmentCategoryNotFound() : Exception("機材カテゴリが見つかりません。")