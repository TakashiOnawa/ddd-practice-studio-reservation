package org.taonaw.facility.domain.model.cancellationFee

import org.taonaw.facility.domain.shared.exception.Err

class CancellationFeeDuplicatedErr : Err("キャンセル料金が重複しています。")
class CancellationFeeRateDuplicatedErr(val cancellationFeeRates: CancellationFeeRates) : Err("キャンセル料金レートが重複しています。")
class CancellationFeeRateNotRiseError() : Err("キャンセル料金レートは徐々に高くならなければなりません。")