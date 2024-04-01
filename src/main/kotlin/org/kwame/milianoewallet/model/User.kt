package org.kwame.milianoewallet.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    var firstName: String = "",
    var lastName: String = "",
    var email: String = "",
    var password: String = ""
)


