package no.enterprice.exam.orderservice.model.exceptions

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler


@ControllerAdvice
class HealthStatusController {

    @ExceptionHandler
    fun handleMeasurementNotFound(ex: MissingMeasurementException): ResponseEntity<String> {
        val exceptionPayload = ExceptionPayload(HttpStatus.BAD_REQUEST.value(), ex.message?: "Measurement must not be null")
        return ResponseEntity(jacksonObjectMapper().writeValueAsString(exceptionPayload), HttpStatus.BAD_REQUEST)
    }
    @ExceptionHandler
    fun handleInvalidInput(ex: IllegalArgumentException): ResponseEntity<String> {
        val exceptionPayload = ExceptionPayload(HttpStatus.BAD_REQUEST.value(), ex.message?: "Not a valid argument for input")
        return ResponseEntity(jacksonObjectMapper().writeValueAsString(exceptionPayload), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler
    fun handleOrderNotFound(ex: OrderNotFoundException): ResponseEntity<String> {
        val exceptionPayload = ExceptionPayload(HttpStatus.NOT_FOUND.value(), ex.message?: "Order not found")
        return ResponseEntity(jacksonObjectMapper().writeValueAsString(exceptionPayload), HttpStatus.NOT_FOUND)
    }
}

data class ExceptionPayload(
    val statusCode: Int,
    val message: String
)
