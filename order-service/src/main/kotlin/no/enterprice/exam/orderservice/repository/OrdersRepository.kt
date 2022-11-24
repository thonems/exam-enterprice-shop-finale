package no.enterprice.exam.orderservice.repository

import no.enterprice.exam.orderservice.model.Orders

import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface OrdersRepository: PagingAndSortingRepository<Orders, Int> {
    override fun findAll(): List<Orders>
    fun getOrdersById(id: Int): Orders?
    fun deleteOrdersById(id:Int): Orders?
}