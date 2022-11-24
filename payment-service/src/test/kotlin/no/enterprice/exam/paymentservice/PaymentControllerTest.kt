package no.enterprice.exam.paymentservice

import no.enterprice.exam.paymentservice.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.web.servlet.MockMvc

@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class PaymentControllerTest {
/*
    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun paymentService() = mockk<PaymentService>()
    }


    @Autowired
    private lateinit var paymentService: PaymentService

    @Autowired
    private lateinit var mockMvc: MockMvc


    */

}