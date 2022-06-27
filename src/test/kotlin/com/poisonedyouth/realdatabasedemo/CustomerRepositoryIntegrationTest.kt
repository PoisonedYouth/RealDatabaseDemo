package com.poisonedyouth.realdatabasedemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired


@RealDatabaseTest
class CustomerRepositoryIntegrationTest {

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
        assertThat(actual!!.getId()).isEqualTo(customer.getId())
        assertThat(actual.firstName).isEqualTo(customer.firstName)
        assertThat(actual.lastName).isEqualTo(customer.lastName)
        assertThat(actual.age).isEqualTo(customer.age)
    }
}