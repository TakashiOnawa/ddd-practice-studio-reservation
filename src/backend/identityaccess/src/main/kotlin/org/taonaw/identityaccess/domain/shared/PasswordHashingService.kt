package org.taonaw.identityaccess.domain.shared

interface PasswordHashingService {
    fun hash(plainTextPassword: String): HashedPassword
}