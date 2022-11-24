package no.enterprice.exam.shippingservice.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "shipping")
class Shipping(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "shipping_id_sequence")
    @SequenceGenerator(name = "shipping_id_sequence",
        allocationSize = 1
    )
    @Column(name = "shipping_id")
    val id: Int? = 0,

    @Column(name = "shipping_order_id")
    val orderId: Int?,

    @Column(name = "shipping_created")
    val created: LocalDateTime = LocalDateTime.now(),

    @Column(name = "shipping_shipped")
    val shipped: Boolean? = false

)

