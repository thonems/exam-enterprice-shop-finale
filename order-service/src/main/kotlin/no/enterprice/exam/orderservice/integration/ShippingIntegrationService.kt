package no.enterprice.exam.orderservice.integration

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForEntity

@Service
class ShippingIntegrationService(@Autowired private val restTemplate: RestTemplate) {

    val shippingUrl = "http://shipping-service/api/shipping/http"

    fun sendHttpCallToShippingService(message: String) {
        val response: ResponseEntity<String> = restTemplate.getForEntity("$shippingUrl/$message", ResponseEntity::class)
        println("response from shipping service: ${response.body}")
    }

}