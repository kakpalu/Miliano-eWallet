package org.kwame.milianoewallet.service

import org.kwame.milianoewallet.model.User
import org.kwame.milianoewallet.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserManager(private val userRepository: UserRepository) {

    fun createUser(user: User): User {
        return userRepository.save(user)
    }

    fun getAllUsers(): List<User> {
        return userRepository.findAll().toList()
    }

    fun getUser(id: Long): User {
        return userRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
    }

    fun findByEmail(email: String): User? {
       val  userList = userRepository.findAll().toList()
         for (user in userList) {
              if (user.email == email) {
                return user
              }
         }
        return null
    }

    fun updateUser(id: Long, firstName: String, lastName: String, email: String): User {
        val user = userRepository.findById(id).orElseThrow { IllegalArgumentException("User not found") }
        user.firstName = firstName
        user.lastName = lastName
        user.email = email
        return userRepository.save(user)
    }

    fun deleteUser(id: Long) {
        if (!userRepository.existsById(id)) throw IllegalArgumentException("User not found")
        userRepository.deleteById(id)
    }
}