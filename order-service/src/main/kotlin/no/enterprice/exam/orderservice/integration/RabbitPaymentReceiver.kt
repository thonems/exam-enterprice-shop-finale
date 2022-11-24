package no.enterprice.exam.orderservice.integration

import no.enterprice.exam.orderservice.service.OrdersService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["orders_paid_queue"])
class RabbitPaymentReceiver(@Autowired private val ordersService: OrdersService) {

    @RabbitHandler
    fun receive(message: String) {
        println("Order service receiving message from payment service via rabbitmq")
        ordersService.changeOrderPaid(message)
    }
}

