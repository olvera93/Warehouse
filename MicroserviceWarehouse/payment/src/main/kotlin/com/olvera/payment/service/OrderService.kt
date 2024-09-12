package com.olvera.payment.service

import com.olvera.payment.dto.OrderResponse
import com.olvera.payment.model.Order
import com.olvera.payment.repository.OrderRepository
import org.springframework.stereotype.Service

@Service
class OrderService(private val orderRepository: OrderRepository) {

    fun addOrder(orderDto: OrderResponse): OrderResponse {

        val orderEntity = orderDto.let {

            if (it.userId == 0) {
                throw IllegalArgumentException("UserId can't be 0")
            }

            Order(null, it.ammount, it.shippingAddress, it.userId)
        }

        orderRepository.save(orderEntity)

        return orderEntity.let {
            OrderResponse(it.orderId, it.ammount, it.shippingAddress, it.userId)
        }
    }
}