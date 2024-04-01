package org.kwame.milianoewallet.config
import org.kwame.milianoewallet.model.User
import org.springframework.security.core.Authentication
fun Authentication.toUser(): User {
    return principal as User
}