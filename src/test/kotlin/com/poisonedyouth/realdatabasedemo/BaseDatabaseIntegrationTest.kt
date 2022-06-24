package com.poisonedyouth.realdatabasedemo

import com.poisonedyouth.realdatabasedemo.BaseDatabaseIntegrationTest.DataSourceConfiguration
import javax.sql.DataSource
import org.springframework.boot.jdbc.DataSourceBuilder
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import org.springframework.test.context.ContextConfiguration
import org.testcontainers.containers.MySQLContainer
import org.testcontainers.utility.DockerImageName

private class KMySQLContainer(image: DockerImageName) : MySQLContainer<KMySQLContainer>(image)

@SpringBootTest
@ContextConfiguration(classes = [DataSourceConfiguration::class])
class BaseDatabaseIntegrationTest {

    @TestConfiguration
    class DataSourceConfiguration {
        @Bean
        fun dataSource(): DataSource {
            return DatabaseContainer.datasource
        }
    }
}

private object DatabaseContainer {

    private var mySQLContainer: KMySQLContainer = KMySQLContainer(
        DockerImageName.parse("mysql:5.7")

    ).apply {
        withUsername("root")
        withPassword("password")
    }
    val datasource: DataSource
        get() = DataSourceBuilder
            .create()
            .url(mySQLContainer.jdbcUrl)
            .password(mySQLContainer.password)
            .username(mySQLContainer.username)
            .build()

    init {
        mySQLContainer.start()
    }
}
