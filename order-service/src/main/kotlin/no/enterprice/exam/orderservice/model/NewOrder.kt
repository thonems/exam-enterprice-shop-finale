package no.enterprice.exam.orderservice.model

import java.time.LocalDateTime

data class NewOrder(
    val date: LocalDateTime? = LocalDateTime.now(),
    val paid: Boolean = false,
    val shipped: Boolean = false
)
