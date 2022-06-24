package com.poisonedyouth.realdatabasedemo

import javax.persistence.Entity
import java.math.BigDecimal
import java.time.OffsetDateTime

@Entity
class Account(
    var accountNumber: Number,
    var value: BigDecimal,
    var creation: OffsetDateTime
): AbstractJpaPersistable<Long>()
