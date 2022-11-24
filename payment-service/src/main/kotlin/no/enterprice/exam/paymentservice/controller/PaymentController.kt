package no.enterprice.exam.paymentservice.controller

import no.enterprice.exam.paymentservice.integration.RabbitSender
import no.enterprice.exam.paymentservice.model.Payment
import no.enterprice.exam.paymentservice.model.exceptions.OrderNotFoundException
import no.enterprice.exam.paymentservice.service.PaymentService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import javax.websocket.server.PathParam


@RestController
@RequestMapping("/api/payment")
class PaymentController(@Autowired private val paymentService: PaymentService) {

    @PostMapping("/order/confirm/{id}")
    fun confirmPaymentOnOrder(@PathVariable id: String?):ResponseEntity<Payment?>{
        if(id!=null){
            return ResponseEntity.ok(paymentService.payTheOrderWithMoney(id))
        }
        throw OrderNotFoundException("Could not find order with that id, you were not charged money. Contact customer service if problem persist.")
    }


    @PostMapping("/testing/order/confirm/{id}")
    fun testingRabbitSendingWithOrderIdBackToOrderService(@PathVariable id: Int?){
        if (id != null) {
            paymentService.confirmOrderIsPaidBySendingIdBackToOrderService(id)
        }
    }

}
