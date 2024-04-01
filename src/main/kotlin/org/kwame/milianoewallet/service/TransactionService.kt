package org.kwame.milianoewallet.service

import org.kwame.milianoewallet.dto.TransactionDto
import org.kwame.milianoewallet.model.Transaction
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.repository.TransactionRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class TransactionService (private val transactionRepository: TransactionRepository) {
    fun createTransaction(sender: User, transactionData: TransactionDto): Transaction {
        val transaction = Transaction(
            sender = sender,
            recipient = transactionData.recipient,
            amount = transactionData.amount,
            currency = transactionData.currency,
            description = transactionData.description,
            reference = transactionData.reference,
        )
        return transactionRepository.save(transaction)
    }

    fun getTransaction(id: Long): Optional<Transaction> {
        return transactionRepository.findById(id)
    }

    fun getTransactions(): List<Transaction> {
        return transactionRepository.findAll().toList()
    }

    fun updateTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }

    fun deleteTransaction(transaction: Transaction) {
        transactionRepository.delete(transaction)
    }
}