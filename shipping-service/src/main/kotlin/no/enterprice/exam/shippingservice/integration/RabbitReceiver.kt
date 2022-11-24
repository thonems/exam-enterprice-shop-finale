package no.enterprice.exam.shippingservice.integration



import no.enterprice.exam.shippingservice.service.ShippingService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired

import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["shipping_queue"])
class RabbitReceiver(@Autowired private val shippingService: ShippingService) {

    @RabbitHandler
    fun receive(message: String) {
        println("shipping service receiving message from order service via rabbitmq")
        shippingService.createNewOrderFromMessage(message)
    }
}


