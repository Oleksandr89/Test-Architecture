package com.example.testarchitecture.di

import com.example.testarchitecture.HomeFragment
import com.example.testarchitecture.MainActivity
import com.example.testarchitecture.component.ComponentFragment
import com.example.testarchitecture.delegate.DelegateFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ContributesAndroidInjector
    abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector
    abstract fun bindHomeFragment(): HomeFragment

    @ContributesAndroidInjector
    abstract fun bindComponentFragment(): ComponentFragment

    @ContributesAndroidInjector
    abstract fun bindDelegateFragment(): DelegateFragment

}