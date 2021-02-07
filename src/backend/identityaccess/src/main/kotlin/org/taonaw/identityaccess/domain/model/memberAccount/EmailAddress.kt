package org.taonaw.identityaccess.domain.model.memberAccount

data class EmailAddress(val value: String) {

    companion object {
        const val LENGTH_MIN = 1
        const val LENGTH_MAX = 100
        const val FORMAT_PATTERN = "\\w+([-+.']\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*"
    }

    init {
        require(value.length in LENGTH_MIN..LENGTH_MAX) {
            "メールアドレスは $LENGTH_MIN 文字以上 $LENGTH_MAX 文字以下でなければなりません。"
        }
        require(Regex(FORMAT_PATTERN).matches(value)) {
            "メールアドレスのフォーマットが不正です。"
        }
    }
}