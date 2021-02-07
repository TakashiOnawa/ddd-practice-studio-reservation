package org.taonaw.identityaccess.infrastructure.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.model.shared.HashedPassword
import org.taonaw.identityaccess.domain.model.shared.PasswordHashingService

@Component
class BCryptPasswordHashingService : PasswordHashingService {

    override fun hash(plainTextPassword: String): HashedPassword {
        return HashedPassword(BCryptPasswordEncoder().encode(plainTextPassword))
    }

    override fun matches(plainTextPassword: String, hashedPassword: HashedPassword): Boolean {
        return BCryptPasswordEncoder().matches(plainTextPassword, hashedPassword.value)
    }
}