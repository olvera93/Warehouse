package com.olvera.payment.model

import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "Orders")
data class Order(

    @Id
    val orderId: String,

    val ammount: Double,

    val shippingAddress: String,

    val userId: Int
)
