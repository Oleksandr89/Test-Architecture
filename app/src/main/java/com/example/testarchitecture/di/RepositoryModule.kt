package com.example.testarchitecture.di

import com.example.testarchitecture.delegate.DelegateRepository
import com.example.testarchitecture.delegate.DelegateRepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideDelegateRepository(): DelegateRepository = DelegateRepositoryImpl()

}