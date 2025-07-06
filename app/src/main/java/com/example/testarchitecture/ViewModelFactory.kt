package com.example.testarchitecture

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.example.testarchitecture.component.ComponentViewModel
import com.example.testarchitecture.delegate.DelegateViewModel
import com.example.testarchitecture.delegate.delegate.ViewModelDelegate
import dagger.assisted.AssistedFactory
import kotlinx.coroutines.CoroutineScope

@AssistedFactory
interface ComponentViewModelFactory : Factory {
    override fun create(handle: SavedStateHandle): ComponentViewModel
}

@AssistedFactory
interface DelegateViewModelFactory : Factory {
    override fun create(handle: SavedStateHandle): DelegateViewModel
}


interface Factory {
    fun create(handle: SavedStateHandle): ViewModel
}

@JvmSuppressWildcards
interface DelegateFactory<D : ViewModelDelegate> {
    fun create(handle: SavedStateHandle, delegateScope: CoroutineScope): D
}

fun Factory.provide(
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
): AbstractSavedStateViewModelFactory =
    object : AbstractSavedStateViewModelFactory(owner, defaultArgs) {
        override fun <T : ViewModel> create(
            key: String,
            modelClass: Class<T>,
            handle: SavedStateHandle
        ): T {
            return create(handle) as T
        }
    }