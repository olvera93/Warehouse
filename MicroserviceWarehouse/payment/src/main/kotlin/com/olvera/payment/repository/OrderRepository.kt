package com.olvera.payment.repository

import com.olvera.payment.model.Order
import org.springframework.data.mongodb.repository.MongoRepository

interface OrderRepository: MongoRepository<Order, String> {
}