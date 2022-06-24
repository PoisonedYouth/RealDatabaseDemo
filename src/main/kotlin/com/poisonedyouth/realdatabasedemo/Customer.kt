package com.poisonedyouth.realdatabasedemo

import javax.persistence.Entity

@Entity
class Customer(
    var firstName: String,
    var lastName: String,
    var age: Int
): AbstractJpaPersistable<Long>()
