package no.enterprice.exam.shippingservice.controller

import no.enterprice.exam.shippingservice.integration.RabbitSender
import no.enterprice.exam.shippingservice.model.Shipping
import no.enterprice.exam.shippingservice.service.ShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam


@RestController
@RequestMapping("/api/shipping")
class ShippingController(@Autowired private val rabbitSender: RabbitSender, @Autowired private val shippingService: ShippingService) {

    @GetMapping("/all")
    fun getAllOrders(@PathParam("page") page: Int): ResponseEntity<List<Shipping>> {
        return ResponseEntity.ok(shippingService.getAllOrders(page).toList())
    }

    @PutMapping("/ship-order/{id}")
    fun changeBooleanShippedOnShippingObject(@PathVariable id: Int?){
        when (id){
            null -> throw IllegalArgumentException("Id cannot be null")
        }
            val shippingObject = id?.let { shippingService.shipShippingObject(it) }
        val orderId = shippingObject?.orderId.toString()
        rabbitSender.sendMessage(orderId)
    }
}
