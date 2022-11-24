package no.enterprice.exam.orderservice.extension

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.github.tomakehurst.wiremock.WireMockServer
import com.github.tomakehurst.wiremock.client.WireMock.*
import com.github.tomakehurst.wiremock.core.WireMockConfiguration
import org.junit.jupiter.api.extension.AfterAllCallback
import org.junit.jupiter.api.extension.BeforeAllCallback
import org.junit.jupiter.api.extension.ExtensionContext

class WireMockExtension : BeforeAllCallback, AfterAllCallback {

    private val wm = WireMockServer(WireMockConfiguration().port(8080))

    override fun beforeAll(context: ExtensionContext?) {

        wm.start()
        wm.stubFor(
            get(urlEqualTo("http://localhost:8080/api/order/orders/1")).willReturn(
                ok(

                )
            )
        )

        wm.stubFor(
            post(urlEqualTo("http://localhost:8080/api/order/new")).willReturn(
                ok(

                )

            )
        )

        wm.stubFor(
            get(urlEqualTo("/api/order/orders/1"))
                .willReturn(
                    ok(
                        jacksonObjectMapper().writeValueAsString(
                            mapOf("orders_date" to "21:00:00", "paid" to "false", "shipped" to "false")
                        )
                    )
                )
        )
    }

    override fun afterAll(context: ExtensionContext?) {
        wm.stop()
    }
}