package com.pavelshelkovenko.ideaplatformcontest.presentation.mapper

import com.pavelshelkovenko.ideaplatformcontest.domain.Product
import com.pavelshelkovenko.ideaplatformcontest.presentation.models.ProductUi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class PresentationMapper {
    fun mapProductDomainToUi(product: Product): ProductUi =
        ProductUi(
            id = product.id,
            name = product.name,
            date = SimpleDateFormat(
                "dd.MM.yyyy",
                Locale.getDefault()
            ).format(Date(product.time.toLong())),
            tags = product.tags,
            amount = product.amount.toString()
        )

    fun mapProductUiToDomain(productUi: ProductUi): Product =
        Product(
            id = productUi.id,
            name = productUi.name,
            time = SimpleDateFormat(
                "dd.MM.yyyy",
                Locale.getDefault()
            ).parse(productUi.date)?.time?.toInt() ?: 0,
            tags = productUi.tags,
            amount = productUi.amount.toInt()
        )
}