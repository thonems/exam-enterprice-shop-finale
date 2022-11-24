package no.enterprice.exam.orderservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RabbitPaymentSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage(message: String) {
        println("Order service sending message to payment service via rabbitmq")
        rabbitTemplate.convertAndSend("payment_queue", message)
    }
}
