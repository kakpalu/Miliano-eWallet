package org.kwame.milianoewallet.repository

import org.kwame.milianoewallet.model.Transaction
import org.springframework.data.repository.CrudRepository

interface TransactionRepository : CrudRepository<Transaction, Long>

