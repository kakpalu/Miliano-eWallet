package org.kwame.milianoewallet.controller

import org.kwame.milianoewallet.dto.*
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.model.Wallet
import org.kwame.milianoewallet.service.HashService
import org.kwame.milianoewallet.service.TokenService
import org.kwame.milianoewallet.service.UserService
import org.kwame.milianoewallet.service.WalletService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.Currency
import java.util.Optional


@RestController
@RequestMapping("/api")
class AuthenticationController(
    val hashService: HashService,
    val tokenService: TokenService,
    val userService: UserService,
    val walletService: WalletService
) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userService.findByEmail(payload.email) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User not found"
        )

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
            user = UserDto(
                id = user.id,
                firstName = user.firstName,
                lastName = user.lastName,
                email = user.email,
                wallet = WalletDto(
                    id = walletService.getWalletByUser(user).get().id,
                    balance = walletService.getWalletByUser(user).get().balance,
                    currency = walletService.getWalletByUser(user).get().currency
                )
            )
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        if (userService.findByEmail(payload.email) != null) {
            throw ResponseStatusException(HttpStatus.CONFLICT, "User already exists")
        }

        val user = User(
            email = payload.email,
            password = hashService.hashBcrypt(payload.password),
        )
        val savedUser = userService.createUser(user)

        //create Wallet for the created user
        val wallet = walletService.createWallet(
            Wallet(
                user = savedUser,
                currency = Currency.getInstance("RWF")
            )
        )
        walletService.createWallet(wallet)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
            user = UserDto(
                id = savedUser.id,
                firstName = savedUser.firstName,
                lastName = savedUser.lastName,
                email = savedUser.email,
                wallet = WalletDto(
                    id = wallet.id,
                    balance = wallet.balance,
                    currency = wallet.currency
                )
            )
        )
    }
}