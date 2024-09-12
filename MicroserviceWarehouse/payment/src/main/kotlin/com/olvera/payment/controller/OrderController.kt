package com.olvera.payment.controller

import com.olvera.payment.dto.ErrorResponse
import com.olvera.payment.dto.OrderResponse
import com.olvera.payment.service.OrderService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.media.Content
import io.swagger.v3.oas.annotations.media.Schema
import io.swagger.v3.oas.annotations.responses.ApiResponse
import io.swagger.v3.oas.annotations.responses.ApiResponses
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping(path = ["/v1/orders"], produces = [(MediaType.APPLICATION_JSON_VALUE)])
@CrossOrigin(value = ["*"])
class OrderController(private val orderService: OrderService) {


    @Operation(summary = "Save a order", description = "You can save order of a user")
    @ApiResponses(
        value = [ApiResponse(responseCode = "200", description = "Successful operation"), ApiResponse(
            responseCode = "404",
            description = "Not found order",
            content = arrayOf(Content(schema = Schema(implementation = ErrorResponse::class)))
        ), ApiResponse(
            responseCode = "400",
            description = "Bad request",
            content = arrayOf(Content(schema = Schema(implementation = ErrorResponse::class)))
        ), ApiResponse(
            responseCode = "500",
            description = "Error with the server",
            content = arrayOf(Content(schema = Schema(implementation = ErrorResponse::class)))
        )]
    )
    @PostMapping(value = ["/save"])
    fun addOrder(@RequestBody orderDto: OrderResponse): ResponseEntity<OrderResponse> {
        val result = orderService.addOrder(orderDto)
        return ResponseEntity.status(HttpStatus.CREATED).body(result)
    }

}