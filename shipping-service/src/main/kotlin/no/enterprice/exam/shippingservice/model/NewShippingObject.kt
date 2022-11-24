package no.enterprice.exam.shippingservice.model

import java.time.LocalDateTime

data class NewShippingObject(val orderId :Int, val created: LocalDateTime?, val shipped: Boolean = false) {


}