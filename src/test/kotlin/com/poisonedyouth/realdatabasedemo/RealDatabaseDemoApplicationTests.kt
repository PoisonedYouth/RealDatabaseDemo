package com.poisonedyouth.realdatabasedemo

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration

@ExtendWith(CleanDatabaseDataSourceExtension::class)
@SpringBootTest
@ContextConfiguration(classes = [TestContextDataSourceConfiguration::class])
class RealDatabaseDemoApplicationTests {

    @Test
    fun contextLoads() {
    }

}
