package no.enterprice.exam.paymentservice.integration

import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class RabbitSender(@Autowired private val rabbitTemplate: RabbitTemplate) {

    fun sendMessage(message: String) {
        println("payment service sending message to order service via rabbitmq")
        rabbitTemplate.convertAndSend("orders_paid_queue", message)
    }

}
