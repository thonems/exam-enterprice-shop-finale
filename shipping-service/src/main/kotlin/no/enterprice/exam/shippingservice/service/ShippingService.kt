package no.enterprice.exam.shippingservice.service


import no.enterprice.exam.shippingservice.integration.RabbitSender
import no.enterprice.exam.shippingservice.model.Shipping
import no.enterprice.exam.shippingservice.repository.ShippingRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.time.LocalDateTime

@Service
@CacheConfig(cacheNames = ["shipping"])
class ShippingService(@Autowired private val  shippingRepository : ShippingRepository,@Autowired private val rabbitSender: RabbitSender) {

    fun createNewOrderFromMessage(message: String?): Shipping?{
        if(message!=null){
            try {
                val id = message.toInt()
                val newOrder = Shipping(orderId = id, created = LocalDateTime.now(), shipped = false )
                val p = shippingRepository.save(newOrder)
                rabbitSender.sendMessage(p.orderId.toString())
                println("sending back from shipping to order: ")
                println(p.orderId.toString())

            } catch (exception: RuntimeException){
                println(exception)
            }
        }
        return null
    }

    fun shipShippingObject(id:Int): Shipping?{
        val oldShippingObject = shippingRepository.findByIdOrNull(id)
        //i know it is not empty
        val newShippingObject = Shipping(id=id, orderId = oldShippingObject!!.orderId, created = oldShippingObject!!.created,shipped = true)

        return shippingRepository.save(newShippingObject)

    }

    fun getAllOrders(page:Int): Page<Shipping> {
        return shippingRepository.findAll(Pageable.ofSize(5).withPage(page))
    }

}