package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.MemberId

sealed class User {
    data class Member(val memberId: MemberId): User() {
    }

    data class NonMember(val name: String, val PhoneNumber: String): User() {
    }
}