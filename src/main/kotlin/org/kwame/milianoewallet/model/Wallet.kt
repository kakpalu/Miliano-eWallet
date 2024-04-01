package org.kwame.milianoewallet.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import java.util.Currency

@Entity
@Table(name = "wallet")
data class Wallet (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val balance: Double = 0.0,
    val currency: Currency = Currency.getInstance("RWF"),
    @NotNull @OneToOne val user: User = User()
)
