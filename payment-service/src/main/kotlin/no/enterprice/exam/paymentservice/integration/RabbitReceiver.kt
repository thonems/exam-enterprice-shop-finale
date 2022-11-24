package no.enterprice.exam.paymentservice.integration


import no.enterprice.exam.paymentservice.service.PaymentService
import org.springframework.amqp.rabbit.annotation.RabbitHandler
import org.springframework.amqp.rabbit.annotation.RabbitListener
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
@RabbitListener(queues = ["payment_queue"])
class RabbitReceiver(@Autowired private val paymentService: PaymentService) {

    @RabbitHandler
    fun receive(message: String) {
        println("payment service receiving message from order service via rabbitmq")
        paymentService.addOrderToPayingRepository(message)

        paymentService.getPayingById(message)
        paymentService.payTheOrderWithMoney(message)
    }
}
