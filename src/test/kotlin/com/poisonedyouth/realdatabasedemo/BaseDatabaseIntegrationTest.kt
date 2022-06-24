package com.poisonedyouth.realdatabasedemo

import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.DynamicPropertyRegistry
import org.springframework.test.context.DynamicPropertySource
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers
import org.testcontainers.utility.DockerImageName

private class KMySQLContainer(image: DockerImageName) : MySQLContainer<KMySQLContainer>(image)

@SpringBootTest
@Testcontainers
abstract class BaseDatabaseIntegrationTes {

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
}
