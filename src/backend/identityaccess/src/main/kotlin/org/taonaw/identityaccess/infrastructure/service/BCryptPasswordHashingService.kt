package org.taonaw.identityaccess.infrastructure.service

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component
import org.taonaw.identityaccess.domain.shared.HashedPassword
import org.taonaw.identityaccess.domain.shared.PasswordHashingService

@Component
class BCryptPasswordHashingService : PasswordHashingService {

    override fun hash(plainTextPassword: String): HashedPassword {
        return HashedPassword(BCryptPasswordEncoder().encode(plainTextPassword))
    }
}