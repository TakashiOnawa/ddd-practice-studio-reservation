package org.taonaw.reservation.domain.shared

interface Specification<T> {
    fun isSatisfiedBy(condition: T): Boolean
}