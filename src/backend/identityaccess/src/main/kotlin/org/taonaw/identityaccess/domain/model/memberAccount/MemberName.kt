package org.taonaw.identityaccess.domain.model.memberAccount

data class MemberName(
        val firstName: String,
        val lastName: String) {

    init {
//        Assertion.argumentRange(firstName, 1, 50)
//        Assertion.argumentRange(lastName, 1, 50)
    }

    fun asFormattedName(): String? {
        return "$lastName $firstName"
    }
}