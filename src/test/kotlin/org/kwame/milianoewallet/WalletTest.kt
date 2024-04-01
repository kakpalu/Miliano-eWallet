package org.kwame.milianoewallet

import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import org.junit.Ignore
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.kwame.milianoewallet.controller.AuthenticationController
import org.kwame.milianoewallet.dto.RegisterDto
import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.model.Wallet
import org.kwame.milianoewallet.repository.UserRepository
import org.kwame.milianoewallet.repository.WalletRepository
import org.kwame.milianoewallet.service.HashService
import org.kwame.milianoewallet.service.TokenService
import org.kwame.milianoewallet.service.UserService
import org.kwame.milianoewallet.service.WalletService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
@WebMvcTest(AuthenticationController::class)
@Ignore
class WalletTest {

    private val userRepository: UserRepository = spyk()
    private val walletRepository: WalletRepository = spyk()

    @MockkBean
    @Autowired
    var hashService: HashService = mockk()

    @MockkBean
    @Autowired
    val tokenService: TokenService = mockk()

    @MockkBean
    @Autowired
    var userService: UserService = mockk()

    @MockkBean
    @Autowired
    var walletService: WalletService = mockk()

    val createdUser = User(1, "John", "Doe", "")
    val createdUserWallet = Wallet(1, 0.00,  Currency.getInstance("USD"), createdUser)

    @Autowired
    private var controller: AuthenticationController = mockk()

    init {
        every { userRepository.save(any()) } returns createdUser
        every { tokenService.createToken(any()) } returns "token"
        every { walletRepository.save(any()) } returns createdUserWallet
    }

    @Test
    fun testUserCreation() {
        controller = AuthenticationController(hashService, tokenService, userService, walletService)
        val response = controller.register(RegisterDto("John", "Doe", "email", "password"))
        assert(response.token == "token")
        assert(response.user.firstName == "John")
        assert(response.user.lastName == "Doe")

    }
}