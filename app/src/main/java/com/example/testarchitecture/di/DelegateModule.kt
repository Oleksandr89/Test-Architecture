package com.example.testarchitecture.di

import com.example.testarchitecture.DelegateFactory
import com.example.testarchitecture.delegate.CloseableCoroutineScope
import com.example.testarchitecture.delegate.delegate.ContentViewModelDelegate
import com.example.testarchitecture.delegate.delegate.ContentViewModelDelegateImpl
import com.example.testarchitecture.delegate.delegate.TopBarViewModelDelegate
import com.example.testarchitecture.delegate.delegate.TopBarViewModelDelegateImpl
import dagger.Module
import dagger.Provides

@Module
class ViewModelDelegateModule {

    @Provides
    fun provideDelegateScope(): CloseableCoroutineScope = CloseableCoroutineScope()

    @Provides
    fun provideTopBarViewModelDelegateFactory(
        factory: TopBarViewModelDelegateImpl.Factory
    ): DelegateFactory<TopBarViewModelDelegate> = factory

    @Provides
    fun provideContentViewModelDelegateFactory(
        factory: ContentViewModelDelegateImpl.Factory
    ): DelegateFactory<ContentViewModelDelegate> = factory

}