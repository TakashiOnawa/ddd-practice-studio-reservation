package org.taonaw.identityaccess.domain.shared

data class PlainTextPassword(private val value: String) {

    fun hash(passwordHashingService: PasswordHashingService): HashedPassword {
        return passwordHashingService.hash(value)
    }
}