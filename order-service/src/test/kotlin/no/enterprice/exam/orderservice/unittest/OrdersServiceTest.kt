package no.enterprice.exam.orderservice.unittest

import io.mockk.mockk
import no.enterprice.exam.orderservice.integration.RabbitSender
import no.enterprice.exam.orderservice.repository.OrdersRepository
import no.enterprice.exam.orderservice.service.OrdersService
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.beans.factory.annotation.Autowired

class OrdersServiceTest {

    private val ordersRepository = mockk<OrdersRepository>()
    //private val rabbitTemplate: RabbitTemplate
    //private val rabbitSender = mockk<RabbitSender(rabbitTemplate)>

    //private val ordersService = OrdersService(ordersRepository,rabbitSender )
}