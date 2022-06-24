package com.poisonedyouth.realdatabasedemo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AccountRepository : JpaRepository<Account, Long> {
    fun getByAccountNumber(accountNumber: Number): Account?
}
