package no.enterprice.exam.orderservice.service


import no.enterprice.exam.orderservice.integration.RabbitSender
import no.enterprice.exam.orderservice.model.NewOrder
import no.enterprice.exam.orderservice.model.Orders
import no.enterprice.exam.orderservice.model.exceptions.IllegalArgumentException
import no.enterprice.exam.orderservice.model.exceptions.OrderNotFoundException
import no.enterprice.exam.orderservice.repository.OrdersRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.CachePut

import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
@CacheConfig(cacheNames = ["orders"])
class OrdersService(@Autowired private val ordersRepository: OrdersRepository,@Autowired private val rabbitSender: RabbitSender) {


    @Cacheable(key = "#id")
    fun getOrderById(id: Int): Orders?{
        return ordersRepository.findByIdOrNull(id)
    }

    fun getAllOrders(page:Int): Page<Orders> {
        return ordersRepository.findAll(Pageable.ofSize(5).withPage(page))
    }


    fun addOrder(newOrder: NewOrder): Orders? {
        return try{
            val newOrder = Orders(date = newOrder.date, paid = newOrder.paid, shipped = newOrder.shipped)
            ordersRepository.save(newOrder)
        } catch (exception: IllegalArgumentException){
            return null
        }
    }

    @CachePut(key = "#orderId.id")
    fun changeOrderPaid(message: String?): Orders?{
        //getting message from rabbit and changing order
        println(message)

        try {

            if(message!= null){
                val id = message.toInt()
                if (ordersRepository.existsById(id)) {
                    val oldOrder = ordersRepository.getOrdersById(id)
                    val date = oldOrder?.date
                    val shipped = oldOrder?.shipped
                    val newOrder = Orders(id = id, date = date, paid = true, shipped = shipped)
                    rabbitSender.sendMessage(newOrder.id.toString())
                    return ordersRepository.save(newOrder)
                }
        }
        } catch (error:RuntimeException){
            throw IllegalArgumentException("Id of the order cannot be null")
        }
        return null
    }



    @CachePut(key = "#orderId.id")
    fun changeShippedValueOnOrder(orderId: String): Orders {

        if((orderId.toInt())!=0){
            val id = orderId.toInt()
            val oldOrder = ordersRepository.findByIdOrNull(id)
            val date = oldOrder?.date
            val shipped = true
            val newOrder = Orders(id = id, date = date, paid = true, shipped = shipped)

            return ordersRepository.save(newOrder)
        } else{
            throw IllegalArgumentException("order id cannot be null")
        }
    }

    fun deleteOrderWithId(id: Int?): Orders? {
        id?.let {
            if ((ordersRepository.findByIdOrNull(it)) != null) {
               return ordersRepository.deleteOrdersById(id)
                }
        }
        throw OrderNotFoundException("Cannot find order")
    }
}