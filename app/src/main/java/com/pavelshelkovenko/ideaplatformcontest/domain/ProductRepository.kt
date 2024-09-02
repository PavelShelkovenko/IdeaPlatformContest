package com.pavelshelkovenko.ideaplatformcontest.domain

import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    suspend fun changeProductAmount(productId: Int, newAmount: Int)

    suspend fun deleteProductById(productId: Int)

    suspend fun getProductsFlow(query: String): Flow<List<Product>>
}