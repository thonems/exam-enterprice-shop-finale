package no.enterprice.exam.orderservice.unittest

import io.mockk.every
import io.mockk.mockk
import no.enterprice.exam.orderservice.model.Orders
import no.enterprice.exam.orderservice.service.OrdersService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.get
import java.time.LocalDateTime
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Pageable


@WebMvcTest
@AutoConfigureMockMvc(addFilters = false)
class OrdersControllerTest {

    @TestConfiguration
    class ControllerTestConfig {
        @Bean
        fun ordersService() = mockk<OrdersService>()
    }

    @Autowired
    private lateinit var ordersService: OrdersService


    @Autowired
    private lateinit var mockMvc: MockMvc

    @Test
    fun shouldGetOrderOneTest(){

        val order1 = Orders(date = LocalDateTime.now(), paid = true, shipped = false)
        every { ordersService.getOrderById(1)} answers {
            order1
        }

        mockMvc.get("/api/order/1/"){

        }
            .andExpect { status {isOk() } }
            .andExpect { content {contentType(MediaType.APPLICATION_JSON)} }


    }



}