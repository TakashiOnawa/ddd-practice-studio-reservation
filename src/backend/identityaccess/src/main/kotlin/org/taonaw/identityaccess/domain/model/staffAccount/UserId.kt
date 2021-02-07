package org.taonaw.identityaccess.domain.model.staffAccount

data class UserId(val value: String) {

    companion object {
        const val LENGTH_MIN = 1
        const val LENGTH_MAX = 16
        const val FORMAT_PATTERN = "[a-zA-Z0-9]*"
    }

    init {
        require(value.length in LENGTH_MIN..LENGTH_MAX) {
            "ユーザー ID は $LENGTH_MIN 以上 $LENGTH_MAX 以下でなければなりません。"
        }
        require(Regex(FORMAT_PATTERN).matches(value)) {
            "ユーザー ID は半角英数でなければなりません。"
        }
    }
}