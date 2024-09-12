package com.olvera.payment.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class OrderResponse(
    val orderId: String?,

    @Schema(description = "Total ammount of all the products")
    val ammount: Double,

    @Schema(description = "Address of the ecommerce")
    @get:NotBlank(message = "orderResponse.shippingAddress must not be blank")
    val shippingAddress: String,

    @Schema(description = "Order belongs to userId")
    @get:NotNull(message = "orderResponse.userId must not be null")
    val userId: Int
)
