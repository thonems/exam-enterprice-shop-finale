package no.enterprice.exam.shippingservice.repository

import no.enterprice.exam.shippingservice.model.Shipping
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository

@Repository
interface ShippingRepository: PagingAndSortingRepository<Shipping, Int> {

    override fun findAll(): List<Shipping>
}