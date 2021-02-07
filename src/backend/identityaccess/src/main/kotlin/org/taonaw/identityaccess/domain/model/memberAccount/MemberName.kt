package org.taonaw.identityaccess.domain.model.memberAccount

data class MemberName(
        val firstName: String,
        val lastName: String) {

    companion object {
        const val FIRST_NAME_LENGTH_MIN: Int = 1
        const val FIRST_NAME_LENGTH_MAX: Int = 20
        const val LAST_NAME_LENGTH_MIN: Int = 1
        const val LAST_NAME_LENGTH_MAX: Int = 20
    }

    init {
        require(lastName.length in LAST_NAME_LENGTH_MIN..LAST_NAME_LENGTH_MAX) {
            "姓 $LAST_NAME_LENGTH_MIN 以上 $LAST_NAME_LENGTH_MAX 以下でなければなりません。"
        }
        require(firstName.length in FIRST_NAME_LENGTH_MIN..FIRST_NAME_LENGTH_MAX) {
            "名は $FIRST_NAME_LENGTH_MIN 以上 $FIRST_NAME_LENGTH_MAX 以下でなければなりません。"
        }
    }

    fun asFormattedName(): String? {
        return "$lastName $firstName"
    }
}