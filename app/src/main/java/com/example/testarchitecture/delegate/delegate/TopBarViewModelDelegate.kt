package com.example.testarchitecture.delegate.delegate

import androidx.lifecycle.SavedStateHandle
import com.example.testarchitecture.DelegateFactory
import com.example.testarchitecture.delegate.DelegateRepository
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

interface TopBarViewModelDelegate : ViewModelDelegate {
    val topBarState: StateFlow<TopBarState>

    fun updateTitle(title: String)

    data class TopBarState(
        val title: String
    )
}

class TopBarViewModelDelegateImpl @AssistedInject constructor(
    @Assisted private val handle: SavedStateHandle,
    @Assisted override val delegateScope: CoroutineScope,
    private val repository: DelegateRepository
) : TopBarViewModelDelegate {

    private val _topBarState =
        MutableStateFlow(TopBarViewModelDelegate.TopBarState("Empty Delegate Title"))
    override val topBarState: StateFlow<TopBarViewModelDelegate.TopBarState> = _topBarState

    override fun updateTitle(title: String) {
        _topBarState.update { it.copy(title = title) }
    }

    init {
        fetchTitle()
    }

    private fun fetchTitle() {
        delegateScope.launch {
            repository.getTitle()
                .onSuccess { response ->
                    _topBarState.update { it.copy(title = response) }
                }
        }
    }

    @AssistedFactory
    interface Factory : DelegateFactory<TopBarViewModelDelegate> {
        override fun create(
            handle: SavedStateHandle,
            delegateScope: CoroutineScope
        ): TopBarViewModelDelegateImpl
    }
}