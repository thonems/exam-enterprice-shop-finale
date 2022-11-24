package no.enterprice.exam.paymentservice.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "payment")
class Payment(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "payment_id_sequence")
    @SequenceGenerator(name = "payment_id_sequence",
        allocationSize = 1
    )
    @Column(name = "payment_id")
    val id: Int? = 0,

    @Column(name = "payment_orders_id")
    val orderId: Int?,

    @Column(name = "payment_paid")
    val paid: Boolean = false,

    @Column(name = "payment_created")
    val created: LocalDateTime? = LocalDateTime.now()

)

