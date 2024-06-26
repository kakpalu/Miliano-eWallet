package org.kwame.milianoewallet.controller

import org.kwame.milianoewallet.config.toUser
import org.kwame.milianoewallet.dto.TransactionDto
import org.kwame.milianoewallet.model.Transaction
import org.kwame.milianoewallet.service.*
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api")
class TransactionController(
    val transactionService: TransactionService,
) {

    @PostMapping("/transactions")
    fun createTransaction(
        @RequestBody payload: TransactionDto,
        authentication: Authentication
    ): ResponseEntity<Transaction> {
        val transaction = transactionService.createTransaction(
            sender = authentication.toUser(),
            transactionData = payload
        )
        return ResponseEntity(transaction, HttpStatus.OK)
    }

    @GetMapping("/transactions")
    fun getTransactions(): ResponseEntity<List<Transaction>> {
        val transactions = transactionService.getTransactions()
        return ResponseEntity(transactions, HttpStatus.OK)
    }


}