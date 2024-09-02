package com.pavelshelkovenko.ideaplatformcontest.presentation.models

data class ProductUi(
    val id: Int,
    val name: String,
    val date: String,
    val tags: List<String>,
    val amount: String,
)
