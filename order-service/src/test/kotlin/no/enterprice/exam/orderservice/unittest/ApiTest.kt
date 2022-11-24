package no.enterprice.exam.orderservice.unittest


import org.codehaus.jettison.json.JSONObject
import org.hamcrest.Matchers.*
import org.junit.jupiter.api.MethodOrderer
import org.junit.jupiter.api.Order
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestMethodOrder
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.MockMvc
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.post

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@AutoConfigureMockMvc
@ActiveProfiles("test")
@TestMethodOrder(MethodOrderer.OrderAnnotation::class)
class ApiTest(@Autowired private val mockMvc: MockMvc) {

    val newUrl = "http://localhost:8080/api/order/new"
    val getUrl = "http://localhost:8080/api/order/orders/"

    @Test
    @Order(1)
    fun shouldCreateCat() {
        val catPayload = JSONObject()
            .put("shipped", "false")
            .put("paid", "false")

        mockMvc.post(newUrl) {
            contentType = MediaType.APPLICATION_JSON
            content = catPayload
        }
            .andExpect { status { 200 } }
            .andReturn()
    }


    @Test
    @Order(2)
    fun shouldGetOrderWithIdTwo(){
        mockMvc.get("$getUrl/2") {
            contentType = MediaType.APPLICATION_JSON
        }
            .andExpect { status { isOk() } }
            .andExpect { content { contentType(MediaType.APPLICATION_JSON) } }
            .andExpect { content { "{\"paid\":false}" }  }
            .andExpect { content { "{\"shipped\":false}" } }
            .andReturn()
            //.andExpect { jsonPath("$.shipped", contains("false")) }
            //.andExpect { jsonPath("$.shipped", contains("false")) }
            //.andExpect { jsonPath("$.shipped", false)) }

    }


}

