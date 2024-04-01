package org.kwame.milianoewallet.repository

import org.kwame.milianoewallet.model.User
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>