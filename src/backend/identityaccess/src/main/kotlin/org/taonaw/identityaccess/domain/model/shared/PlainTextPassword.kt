package org.taonaw.identityaccess.domain.model.shared

data class PlainTextPassword(private val value: String) {

    companion object {
        const val LENGTH_MIN = 8
        const val LENGTH_MAX = 20
        const val FORMAT_PATTERN = "[a-zA-Z0-9]*"
    }

    init {
        require(value.length in LENGTH_MIN..LENGTH_MAX) {
            "パスワードは $LENGTH_MIN 文字以上 $LENGTH_MAX 文字以下でなければなりません。"
        }
        require(Regex(FORMAT_PATTERN).matches(value)) {
            "パスワードは半角英数でなければなりません。"
        }
    }

    fun hash(passwordHashingService: PasswordHashingService): HashedPassword {
        return passwordHashingService.hash(value)
    }

    fun matches(hashedPassword: HashedPassword, passwordHashingService: PasswordHashingService): Boolean {
        return passwordHashingService.matches(value, hashedPassword)
    }
}