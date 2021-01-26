package org.taonaw.reservation.domain.model.reservation

import org.taonaw.reservation.domain.model.shared.MemberId

/**
 * 利用者
 */
sealed class User {

    /**
     * 会員
     */
    class Member(val memberId: MemberId): User() {
        override fun equals(other: Any?): Boolean {
            if (this === other) return true
            if (javaClass != other?.javaClass) return false

            other as Member

            if (memberId != other.memberId) return false

            return true
        }

        override fun hashCode(): Int {
            return memberId.hashCode()
        }
    }

    /**
     * 非会員
     */
    data class NonMember(val name: String, val phoneNumber: String): User() {

        companion object {
            const val USER_NAME_LENGTH_MIN: Int = 1
            const val USER_NAME_LENGTH_MAX: Int = 41
            const val PHONE_NUMBER_LENGTH_MIN: Int = 6
            const val PHONE_NUMBER_LENGTH_MAX: Int = 14
        }

        init {
            require(name.length in USER_NAME_LENGTH_MIN..USER_NAME_LENGTH_MAX) {
                "氏名は $USER_NAME_LENGTH_MIN 以上 $USER_NAME_LENGTH_MAX 以下でなければなりません。"
            }
            require(phoneNumber.length in PHONE_NUMBER_LENGTH_MIN..PHONE_NUMBER_LENGTH_MAX) {
                "電話番号は $PHONE_NUMBER_LENGTH_MIN 以上 $PHONE_NUMBER_LENGTH_MAX 以下でなければなりません。"
            }
        }
    }
}