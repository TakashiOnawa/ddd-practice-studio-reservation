package org.taonaw.reservation

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class ReservationApplication

fun main(args: Array<String>) {
	runApplication<ReservationApplication>(*args)
}
