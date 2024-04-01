package org.kwame.milianoewallet.controller

import org.kwame.milianoewallet.dto.LoginDto
import org.kwame.milianoewallet.dto.LoginResponseDto
import org.kwame.milianoewallet.dto.RegisterDto
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.model.Wallet
import org.kwame.milianoewallet.service.HashService
import org.kwame.milianoewallet.service.TokenService
import org.kwame.milianoewallet.service.UserManager
import org.kwame.milianoewallet.service.WalletManager
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.Currency


@RestController
@RequestMapping("/api")
class AuthenticationController(val hashService: HashService, val tokenService: TokenService, val userManager: UserManager, val walletManager: WalletManager) {

    @PostMapping("/login")
    fun login(@RequestBody payload: LoginDto): LoginResponseDto {
        val user = userManager.findByEmail(payload.email) ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "User not found"
        )

        if (!hashService.checkBcrypt(payload.password, user.password)) {
            throw ResponseStatusException(HttpStatus.UNAUTHORIZED, "Login failed")
        }

        return LoginResponseDto(
            token = tokenService.createToken(user),
        )
    }

    @PostMapping("/register")
    fun register(@RequestBody payload: RegisterDto): LoginResponseDto {
        userManager.findByEmail(payload.email) ?: throw ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "A user with that email already exists"
        )

        val user = User(
            email = payload.email,
            password = hashService.hashBcrypt(payload.password),
        )
        val savedUser = userManager.createUser(user)

        //create Wallet for the created user
        val wallet = Wallet(
            user = savedUser,
            currency = Currency.getInstance("RWF")
        )
        walletManager.createWallet(wallet)

        return LoginResponseDto(
            token = tokenService.createToken(savedUser),
        )
    }
}