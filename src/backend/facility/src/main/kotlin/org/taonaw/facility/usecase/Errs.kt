package org.taonaw.facility.usecase

class StudioNotFound() : Exception("スタジオが見つかりません。")
class EquipmentNotFound() : Exception("機材が見つかりません。")
class EquipmentCategoryNotFound() : Exception("機材カテゴリが見つかりません。")