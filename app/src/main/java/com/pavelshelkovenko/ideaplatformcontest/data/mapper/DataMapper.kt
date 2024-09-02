package com.pavelshelkovenko.ideaplatformcontest.data.mapper

import com.pavelshelkovenko.ideaplatformcontest.data.local.ProductEntity
import com.pavelshelkovenko.ideaplatformcontest.domain.Product

class DataMapper {
    fun mapProductDomainToEntity(product: Product): ProductEntity =
        ProductEntity(
            id = product.id,
            name = product.name,
            time = product.time,
            tags = product.tags.joinToString(prefix = "[", postfix = "]", separator = ", "),
            amount = product.amount
        )

    fun mapProductEntityToDomain(productEntity: ProductEntity): Product =
        Product(
            id = productEntity.id,
            name = productEntity.name,
            time = productEntity.time,
            tags = productEntity.tags
                .removeSurrounding("[", "]")
                .replace("\"", "")
                .split(",")
                .filter { it.isNotEmpty() },
            amount = productEntity.amount
        )
}