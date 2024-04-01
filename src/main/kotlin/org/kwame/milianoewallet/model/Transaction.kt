package org.kwame.milianoewallet.model

import jakarta.persistence.*
import org.jetbrains.annotations.NotNull
import org.kwame.milianoewallet.enums.TransactionStatusType
import java.time.LocalDateTime
import java.util.Currency

@Entity
@Table(name = "transactions")
data class Transaction (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    val amount: Double = 0.0,
    val dateTime: LocalDateTime = LocalDateTime.now(),

    @Column(name = "status", columnDefinition = "smallint")
    val status: TransactionStatusType = TransactionStatusType.pending,

    val description: String = "",
    val reference: String = "",
    val fee: Double = 0.0,
    val currency: Currency = Currency.getInstance("RWF"),
    @NotNull @ManyToOne val sender: User = User(),
    @NotNull @ManyToOne val recipient: User = User(),
)