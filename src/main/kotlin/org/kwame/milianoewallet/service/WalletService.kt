package org.kwame.milianoewallet.service

import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.model.Wallet
import org.kwame.milianoewallet.repository.WalletRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class WalletService(private val walletRepository: WalletRepository) {
fun createWallet(wallet: Wallet): Wallet {
        return walletRepository.save(wallet)
    }

    fun getWallet(id: Long): Optional<Wallet> {
        return walletRepository.findById(id)
    }

    fun getWalletByUser(user: User): Optional<Wallet> {
       val walletList = walletRepository.findAll().toList()
         for (wallet in walletList) {
              if (wallet.user == user) {
                return Optional.of(wallet)
              }
         }
        return Optional.empty()
    }

    fun getWallets(): List<Wallet> {
        return walletRepository.findAll().toList()
    }

    fun updateWallet(wallet: Wallet): Wallet {
        return walletRepository.save(wallet)
    }

    fun deleteWallet(wallet: Wallet) {
        walletRepository.delete(wallet)
    }

}