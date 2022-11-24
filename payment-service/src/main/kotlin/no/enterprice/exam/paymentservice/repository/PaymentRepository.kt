package no.enterprice.exam.paymentservice.repository

import no.enterprice.exam.paymentservice.model.Payment
import org.springframework.data.repository.PagingAndSortingRepository
import java.util.*

interface PaymentRepository: PagingAndSortingRepository<Payment, Int> {
    override fun findAll(): List<Payment>
    fun getPaymentById(id:Int): Payment?
}