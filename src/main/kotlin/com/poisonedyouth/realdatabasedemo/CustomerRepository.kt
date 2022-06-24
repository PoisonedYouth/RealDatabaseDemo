package com.poisonedyouth.realdatabasedemo

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CustomerRepository : JpaRepository<Customer, Long> {
    fun getByFirstName(firstName: String): Customer?
}
