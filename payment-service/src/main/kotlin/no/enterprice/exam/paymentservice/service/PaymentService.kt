package no.enterprice.exam.paymentservice.service

import no.enterprice.exam.paymentservice.integration.RabbitSender
import no.enterprice.exam.paymentservice.model.Payment
import no.enterprice.exam.paymentservice.model.exceptions.OrderNotFoundException
import no.enterprice.exam.paymentservice.repository.PaymentRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.time.LocalDateTime

@Service
class PaymentService(@Autowired private val paymentRepository: PaymentRepository, @Autowired private val rabbitSender: RabbitSender) {

    fun payTheOrderWithMoney(message: String): Payment? {
        println("inside paymentService. message:  $message")
        if(message==null){
            throw OrderNotFoundException("Order not found with that id")
        }
        val id = message.toIntOrNull()


        if(paymentRepository.existsById(id!!)){
            val oldPayment = paymentRepository.getPaymentById(id)
            val newPayment = Payment(id=id, orderId = oldPayment?.orderId, paid = true)
            println("Succesfully paid in Payment service. Sending info back to OrderService.")
            if (oldPayment != null) {
                confirmOrderIsPaidBySendingIdBackToOrderService(oldPayment.orderId!!)
                return paymentRepository.save(newPayment)
            }
        }
        return null
    }

    fun confirmOrderIsPaidBySendingIdBackToOrderService(id:Int){
        rabbitSender.sendMessage(id.toString())
    }

    fun addOrderToPayingRepository(message: String) {
        val newShippingOrder = Payment(orderId = message.toInt(), created = LocalDateTime.now())
        paymentRepository.save(newShippingOrder)
    }

    fun getPayingById(message: String?){
        if(message!= null){
            val paying = paymentRepository.findByIdOrNull(message.toInt())
            println(paying!!.id)
            println(paying!!.orderId)
        }

    }
}
