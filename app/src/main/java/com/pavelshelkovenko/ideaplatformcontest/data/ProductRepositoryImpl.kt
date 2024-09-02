package com.pavelshelkovenko.ideaplatformcontest.data

import com.pavelshelkovenko.ideaplatformcontest.data.local.ProductDao
import com.pavelshelkovenko.ideaplatformcontest.data.mapper.DataMapper
import com.pavelshelkovenko.ideaplatformcontest.domain.Product
import com.pavelshelkovenko.ideaplatformcontest.domain.ProductRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductRepositoryImpl(
    private val mapper: DataMapper,
    private val productDao: ProductDao,
) : ProductRepository {

    override suspend fun getProductsFlow(query: String): Flow<List<Product>> {
        return productDao.getProductsFlow(query).map { productListEntity ->
            productListEntity.map { productEntity ->
                mapper.mapProductEntityToDomain(productEntity)
            }
        }
    }

    override suspend fun changeProductAmount(productId: Int, newAmount: Int) {
        val oldProduct = productDao.getProductById(productId)
        val newProduct = oldProduct.copy(amount = newAmount)
        productDao.insertProduct(newProduct)
    }

    override suspend fun deleteProductById(productId: Int) {
        productDao.deleteProduct(productId)
    }
}