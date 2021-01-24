package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.MemberId

sealed class User {
    data class Member(val memberId: MemberId): User() {
    }

    data class NonMember(val name: String, val phoneNumber: String): User() {

        companion object {
            const val USER_NAME_MIN_LENGTH: Int = 1
            const val USER_NAME_MAX_LENGTH: Int = 41
            const val PHONE_NUMBER_MIN_LENGTH: Int = 6
            const val PHONE_NUMBER_MAX_LENGTH: Int = 6
        }

        init {
            require(name.length < USER_NAME_MIN_LENGTH || name.length > USER_NAME_MAX_LENGTH) {
                "氏名は $USER_NAME_MIN_LENGTH 以上 $USER_NAME_MAX_LENGTH 以下でなければなりません。"
            }
            require(phoneNumber.length < PHONE_NUMBER_MIN_LENGTH || phoneNumber.length > PHONE_NUMBER_MAX_LENGTH) {
                "氏名は $PHONE_NUMBER_MIN_LENGTH 以上 $PHONE_NUMBER_MAX_LENGTH 以下でなければなりません。"
            }
        }
    }
}