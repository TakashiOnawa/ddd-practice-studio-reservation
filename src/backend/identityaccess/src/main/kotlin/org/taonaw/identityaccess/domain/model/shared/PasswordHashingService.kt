package org.taonaw.identityaccess.domain.model.shared

interface PasswordHashingService {
    fun hash(plainTextPassword: String): HashedPassword
    fun matches(plainTextPassword: String, hashedPassword: HashedPassword): Boolean
}