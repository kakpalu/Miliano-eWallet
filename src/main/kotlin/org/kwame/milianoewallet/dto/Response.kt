package org.kwame.milianoewallet.dto

import jakarta.persistence.ManyToOne
import org.jetbrains.annotations.NotNull
import org.kwame.milianoewallet.enums.TransactionStatusType
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.model.Wallet
import org.springframework.web.server.ResponseStatusException
import java.time.LocalDateTime
import java.util.*

/**
 * This file contains all outgoing DTOs.
 *
 */

data class LoginResponseDto(
    val token: String,
)

data class UserDto(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val wallet: Optional<Wallet>,
)

data class TransactionDto(
    val id: Long,
    val amount: Double,
    val description: String,
    val reference: String,
    val currency: Currency,
    val recipient: User,
)