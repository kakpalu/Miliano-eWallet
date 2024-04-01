package org.kwame.milianoewallet.controller

import org.kwame.milianoewallet.config.toUser
import org.kwame.milianoewallet.dto.UserDto
import org.kwame.milianoewallet.dto.WalletDto
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.service.UserService
import org.kwame.milianoewallet.service.WalletService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.security.core.Authentication

@RestController
@RequestMapping("/api")
class UserController (val userService: UserService, val wallet: WalletService) {

    @GetMapping("/user/profile")
    fun getUserProfile(authentication: Authentication): ResponseEntity<UserDto> {
        val authUser = authentication.toUser()
        val userWallet = wallet.getWalletByUser(authUser)
        val userDto = UserDto(
            id = authUser.id,
            firstName = authUser.firstName,
            lastName = authUser.lastName,
            email = authUser.email,
            wallet = WalletDto(id = userWallet.get().id, balance = userWallet.get().balance, currency = userWallet.get().currency)
        )
        return ResponseEntity(userDto, HttpStatus.OK)
    }

    @PutMapping("/user/profile")
    fun updateUserProfile(authentication: Authentication, @RequestBody user: User): ResponseEntity<User> {

        val authUser = authentication.toUser()
        val updatedUser = authUser.copy(firstName = user.firstName, lastName = user.lastName, email = user.email)
        userService.updateUser(
            id = updatedUser.id,
            firstName =  updatedUser.firstName,
            lastName =  updatedUser.lastName,
            email =  updatedUser.email
        )
        return ResponseEntity(updatedUser, HttpStatus.OK)
    }
}