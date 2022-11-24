package no.enterprice.exam.orderservice.model

import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "orders")
class Orders(
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_sequence")
    @SequenceGenerator(name = "orders_id_sequence",
        allocationSize = 1
    )
    @Column(name = "orders_id")
    val id: Int? = 0,

    @Column(name = "orders_date")
    val date: LocalDateTime? = LocalDateTime.now(),

    @Column(name = "orders_paid")
    val paid: Boolean = false,

    @Column(name = "orders_is_shipped")
    val shipped: Boolean?

)

