package com.example.testarchitecture.component

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject

class ComponentViewModel @AssistedInject constructor(
    @Assisted private val stateHandle: SavedStateHandle,
//    private val repository: DelegateRepository
) : ViewModel() {

}