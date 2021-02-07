package org.taonaw.identityaccess.domain.model.memberAccount

data class PhoneNumber(val value: String) {

    companion object {
        const val LENGTH_MIN = 1
        const val LENGTH_MAX = 15
        const val FORMAT_PATTERN = "[0-9]*"
    }

    init {
        require(value.length in LENGTH_MIN..LENGTH_MAX) {
            "電話番号は $LENGTH_MIN 以上 $LENGTH_MAX 以下でなければなりません。"
        }
        require(Regex(FORMAT_PATTERN).matches(value)) {
            "電話番号は半角数字でなければなりません。"
        }
    }
}