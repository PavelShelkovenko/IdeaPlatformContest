package com.pavelshelkovenko.ideaplatformcontest.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductDao {

    @Query("DELETE FROM ${AppDatabase.PRODUCT_TABLE_NAME} WHERE id = :productId")
    suspend fun deleteProduct(productId: Int)

    @Query("SELECT * FROM ${AppDatabase.PRODUCT_TABLE_NAME} WHERE name LIKE '%' || :query || '%'")
    fun getProductsFlow(query: String): Flow<List<ProductEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProduct(productEntity: ProductEntity)

    @Query("SELECT * FROM ${AppDatabase.PRODUCT_TABLE_NAME} WHERE id = :productId")
    suspend fun getProductById(productId: Int): ProductEntity
}