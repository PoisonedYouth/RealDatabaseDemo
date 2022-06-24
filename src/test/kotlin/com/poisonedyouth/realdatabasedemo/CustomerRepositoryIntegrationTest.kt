package com.poisonedyouth.realdatabasedemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Testcontainers
class CustomerRepositoryIntegrationTest {

    @Autowired
    lateinit var customerRepository: CustomerRepository

    companion object {
        @Container
        private val mysqlContainer = MySQLContainer<Nothing>("mysql:5.7")

        @DynamicPropertySource
        @JvmStatic
        fun registerDynamicProperties(registry: DynamicPropertyRegistry) {
            registry.add("spring.datasource.url", mysqlContainer::getJdbcUrl)
            registry.add("spring.datasource.username", mysqlContainer::getUsername)
            registry.add("spring.datasource.password", mysqlContainer::getPassword)
        }
    }

    @Test
    fun `getByFirstName is returning matching user`() {
        // given
        val customer = Customer("John", "Doe", 42)
        customerRepository.save(customer)

        // when
        val actual = customerRepository.getByFirstName("John")

        // then
        assertThat(actual!!.getId()).isEqualTo(1)
        assertThat(actual.firstName).isEqualTo("John")
        assertThat(actual.lastName).isEqualTo("Doe")
        assertThat(actual.age).isEqualTo(42)
    }
}