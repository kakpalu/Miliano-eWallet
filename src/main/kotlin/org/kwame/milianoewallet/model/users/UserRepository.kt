package org.kwame.milianoewallet.model.users

import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<User, Long>