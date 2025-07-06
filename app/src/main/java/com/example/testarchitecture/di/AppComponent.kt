package com.example.testarchitecture.di

import android.app.Application
import com.example.testarchitecture.TestArchitectureApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        ViewModelDelegateModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<TestArchitectureApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(app: Application): Builder
        fun build(): AppComponent
    }
}