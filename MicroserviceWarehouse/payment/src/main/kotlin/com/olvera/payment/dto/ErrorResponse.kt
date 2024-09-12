package com.olvera.payment.dto

import io.swagger.v3.oas.annotations.media.Schema
import java.time.LocalDateTime

data class ErrorResponse(

    @Schema(description = "API path invoked by client")
    val apiPath: String,

    @Schema(description = "Error code representing the error happened")
    val errorCode: String,

    @Schema(description = "Error message representing the error happened")
    val errorMessage: String,

    @Schema(description = "Time representing when the error happened")
    val errorTime: LocalDateTime
)
