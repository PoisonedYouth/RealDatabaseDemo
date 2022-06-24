package com.poisonedyouth.realdatabasedemo

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import java.math.BigDecimal
import java.time.OffsetDateTime


class AccountRepositoryIntegrationTest : BaseDatabaseIntegrationTest() {

    @Autowired
    lateinit var accountRepository: AccountRepository

    @Test
    fun `getByFirstName is returning matching user`() {
        // given
        val creation = OffsetDateTime.now()
        val account = Account(12345, BigDecimal(123.0), creation)
        accountRepository.save(account)

        // when
        val actual = accountRepository.getByAccountNumber(12345)

        // then
        assertThat(actual!!.getId()).isEqualTo(1) // This can be a different value
        assertThat(actual.accountNumber).isEqualTo(12345)
        assertThat(actual.value).isEqualByComparingTo(BigDecimal(123.0))
        assertThat(actual.creation.toEpochSecond()).isEqualTo(creation.toEpochSecond())
    }
}