package com.poisonedyouth.realdatabasedemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


class CustomerRepositoryIntegrationTest : BaseDatabaseIntegrationTest() {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Test
    fun `getByFirstName is returning matching user`() {
        // given
        val customer = Customer("John", "Doe", 42)
        customerRepository.save(customer)

        // when
        val actual = customerRepository.getByFirstName("John")

        // then
        assertThat(actual!!.getId()).isEqualTo(1) // This can be a different value
        assertThat(actual.firstName).isEqualTo("John")
        assertThat(actual.lastName).isEqualTo("Doe")
        assertThat(actual.age).isEqualTo(42)
    }
}