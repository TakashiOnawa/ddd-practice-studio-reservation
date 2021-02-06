package org.taonaw.identityaccess.domain.shared

data class PhoneNumber(
        val areaCode: String,
        val localNumber: String,
        val subscriberNumber: String) {

    init {
//        Assertion.argumentPattern(areaCode, "[0-9]*", "市外局番は半角数字でなければなりません。")
//        Assertion.argumentRange(areaCode, 2, 4)
//        Assertion.argumentPattern(localNumber, "[0-9]*", "市内局番半角数字でなければなりません。")
//        Assertion.argumentRange(localNumber, 2, 4)
//        Assertion.argumentPattern(subscriberNumber, "[0-9]*", "加入者番号半角数字でなければなりません。")
//        Assertion.argumentRange(subscriberNumber, 2, 4)
    }

    fun asFormattedNumber(): String {
        return "$areaCode-$localNumber-$subscriberNumber"
    }
}