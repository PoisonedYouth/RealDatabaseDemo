package com.poisonedyouth.realdatabasedemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.time.OffsetDateTime


@RealDatabaseTest
class AccountRepositoryIntegrationTest {

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Test
    fun `getByAccountNumber is returning matching account`() {
        // given
        val creation = OffsetDateTime.now()
        val account = Account(12345, BigDecimal(123.0), creation)
        accountRepository.save(account)

        // when
        val actual = accountRepository.getByAccountNumber(12345)

        // then
        assertThat(actual!!.getId()).isEqualTo(account.getId())
        assertThat(actual.accountNumber).isEqualTo(account.accountNumber)
        assertThat(actual.value).isEqualByComparingTo(account.value)
        assertThat(actual.creation.toEpochSecond()).isEqualTo(account.creation.toEpochSecond())
    }
}