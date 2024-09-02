package com.pavelshelkovenko.ideaplatformcontest.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = AppDatabase.PRODUCT_TABLE_NAME)
data class ProductEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val time: Int,
    val tags: String,
    val amount: Int,
)
