package org.taonaw.reservation.domain.shared.exception

class ErrNotification {
    private val errs: MutableList<Err> = mutableListOf()

    fun addErr(err: Err?) {
        err?.let { errs.add(it) }
    }

    fun addErr(errNotification: ErrNotification?) {
        errNotification?.let { errs.addAll(it.errs) }
    }

    fun hasErrs(): Boolean {
        return errs.isNotEmpty()
    }

    fun errMessage(): String {
        return errs.joinToString { it.message }
    }

    fun throwIfHasErrs(message: String) {
        if (hasErrs())
            throw DomainException(message, this)
    }

    override fun toString(): String {
        return "ErrNotification(errs=$errs)"
    }
}

open class Err(val message: String) {

    fun throwErr() {
        val errNotification = ErrNotification()
        errNotification.addErr(this)
        errNotification.throwIfHasErrs(message)
    }

    override fun toString(): String {
        return "Err(message='$message')"
    }
}