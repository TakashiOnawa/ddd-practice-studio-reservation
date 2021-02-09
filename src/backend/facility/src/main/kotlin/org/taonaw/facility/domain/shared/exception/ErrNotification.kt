package org.taonaw.facility.domain.shared.exception

class ErrNotification private constructor(private val errs: List<Err>){

    constructor() : this(emptyList()) {
    }

    fun addErr(err: Err?): ErrNotification {
        return if (err != null) {
            ErrNotification(errs.plus(err))
        } else {
            this
        }
    }

    fun addErr(errNotification: ErrNotification?): ErrNotification {
        return if (errNotification != null) {
            ErrNotification(errs.plus(errNotification.errs))
        } else {
            this
        }
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
        ErrNotification().addErr(this).throwIfHasErrs(message)
    }

    override fun toString(): String {
        return "Err(message='$message')"
    }
}