package com.pavelshelkovenko.ideaplatformcontest.di

import com.pavelshelkovenko.ideaplatformcontest.data.ProductRepositoryImpl
import com.pavelshelkovenko.ideaplatformcontest.data.local.AppDatabase
import com.pavelshelkovenko.ideaplatformcontest.data.local.ProductDao
import com.pavelshelkovenko.ideaplatformcontest.data.mapper.DataMapper
import com.pavelshelkovenko.ideaplatformcontest.domain.ProductRepository
import com.pavelshelkovenko.ideaplatformcontest.presentation.MainViewModel
import com.pavelshelkovenko.ideaplatformcontest.presentation.mapper.PresentationMapper
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    single<ProductRepository> { ProductRepositoryImpl(mapper = get(), productDao = get()) }
    single<AppDatabase> { AppDatabase.getDatabase(context = get()) }
    single<ProductDao> { get<AppDatabase>().productDao() }
    single<DataMapper> { DataMapper() }
    single<PresentationMapper> { PresentationMapper() }
    viewModelOf(::MainViewModel)
}