package org.kwame.milianoewallet.repository

import org.kwame.milianoewallet.model.Wallet
import org.springframework.data.repository.CrudRepository

interface WalletRepository : CrudRepository<Wallet, Long>




