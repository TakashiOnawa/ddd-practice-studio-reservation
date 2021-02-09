package org.taonaw.facility.usecase

class TenantNotFound() : Exception("店舗が見つかりません。")
class StudioNotFound() : Exception("スタジオが見つかりません。")
class EquipmentNotFound() : Exception("機材が見つかりません。")
class EquipmentCategoryNotFound() : Exception("機材カテゴリが見つかりません。")
class CancellationFeeNotFound() : Exception("キャンセル料金が見つかりません。")