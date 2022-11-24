package no.enterprice.exam.orderservice.controller

import no.enterprice.exam.orderservice.integration.RabbitPaymentSender
import no.enterprice.exam.orderservice.integration.RabbitSender
import no.enterprice.exam.orderservice.integration.ShippingIntegrationService
import no.enterprice.exam.orderservice.model.NewOrder
import no.enterprice.exam.orderservice.model.Orders
import no.enterprice.exam.orderservice.model.exceptions.IllegalArgumentException
import no.enterprice.exam.orderservice.model.exceptions.OrderNotFoundException
import no.enterprice.exam.orderservice.service.OrdersService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam

@RestController
@RequestMapping("/api/order")
class OrderController( @Autowired private val rabbitPaymentSender: RabbitPaymentSender, @Autowired private val ordersService: OrdersService) {


    @GetMapping("/orders/{id}")
    fun getOrderById(@PathVariable id: Int?): ResponseEntity<Orders?> {
        if (id != null) {
            return ResponseEntity.ok(ordersService.getOrderById(id))
        }
        throw OrderNotFoundException("No order matching your request")
    }

    @PutMapping("/confirmed/{id}")
    fun orderChangingFromUnpaidToPaid(@PathVariable id: Int?): Orders? {
        when (id) {
            null -> throw IllegalArgumentException("Id cannot be null")
        }
        val order = ordersService.getOrderById(id!!)
        if(order!= null){
            return ordersService.changeOrderPaid(id.toString())
        }
        throw OrderNotFoundException("No order matching your request")
    }


    @GetMapping("/all")
    fun getAllOrders(@PathParam("page") page: Int): ResponseEntity<List<Orders>> {
        return ResponseEntity.ok(ordersService.getAllOrders(page).toList())
    }

    @PostMapping("/new")
    fun registerNewOrder(@RequestBody newOrder: NewOrder?): ResponseEntity<Orders?> {
        if( newOrder == null){
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null)
        }
        val response = ordersService.addOrder(newOrder)
        rabbitPaymentSender.sendMessage(response?.id.toString())
        return ResponseEntity.status(201).body(response)
    }

    //TODO FIX
    @DeleteMapping("/delete/{id}")
    fun deleteOrder(@PathVariable id: Int?): ResponseEntity<Orders?>{
        try {
            return ResponseEntity.ok().body(ordersService.deleteOrderWithId(id))
        } catch (ex:OrderNotFoundException){
            throw OrderNotFoundException("Could not find order with that id")
        }
    }
}

