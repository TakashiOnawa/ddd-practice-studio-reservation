package org.taonaw.identityaccess.domain.model.memberAccount

import org.taonaw.identityaccess.domain.shared.EmailAddress
import org.taonaw.identityaccess.domain.shared.PhoneNumber

data class ContractInformation(
        val emailAddress: EmailAddress,
        val phoneNumber: PhoneNumber) {
}