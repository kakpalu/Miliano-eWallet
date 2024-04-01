package org.kwame.milianoewallet.controller

import org.kwame.milianoewallet.config.toUser
import org.kwame.milianoewallet.dto.LoginResponseDto
import org.kwame.milianoewallet.dto.UserDto
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.model.Wallet
import org.kwame.milianoewallet.service.UserManager
import org.kwame.milianoewallet.service.WalletManager
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.Authentication
import java.util.Optional

@RestController
@RequestMapping("/api")
class UserController (val userManager: UserManager, val wallet: WalletManager) {

    @GetMapping("/user/profile")
    fun getUserProfile(authentication: Authentication): ResponseEntity<UserDto> {
        val authUser = authentication.toUser()
        val userWallet = wallet.getWalletByUser(authUser)
        val userDto = UserDto(
            id = authUser.id,
            firstName = authUser.firstName,
            lastName = authUser.lastName,
            email = authUser.email,
            wallet = userWallet
        )
        return ResponseEntity(userDto, HttpStatus.OK)
    }

    @PutMapping("/user/profile")
    fun updateUserProfile(authentication: Authentication, @RequestBody user: User): ResponseEntity<User> {

        val authUser = authentication.toUser()
        val updatedUser = authUser.copy(firstName = user.firstName, lastName = user.lastName, email = user.email)
        userManager.updateUser(
            id = updatedUser.id,
            firstName =  updatedUser.firstName,
            lastName =  updatedUser.lastName,
            email =  updatedUser.email
        )
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
}