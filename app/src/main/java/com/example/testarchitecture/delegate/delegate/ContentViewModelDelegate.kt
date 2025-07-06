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

interface ContentViewModelDelegate : ViewModelDelegate {
    val contentState: StateFlow<ContentState>
    fun updateContent(content: String)

    data class ContentState(val text: String)
}

class ContentViewModelDelegateImpl @AssistedInject constructor(
    @Assisted private val handle: SavedStateHandle,
    @Assisted override val delegateScope: CoroutineScope,
    private val repository: DelegateRepository
) : ContentViewModelDelegate {

    private val _contentState = MutableStateFlow(ContentViewModelDelegate.ContentState("Empty Delegate Text"))
    override val contentState: StateFlow<ContentViewModelDelegate.ContentState> = _contentState

    override fun updateContent(content: String) {
        _contentState.update { it.copy(text = content) }
    }

    init {
        fetchText()
    }

    private fun fetchText() {
        delegateScope.launch {
            repository.getText()
                .onSuccess { response ->
                    _contentState.update { it.copy(text = response) }
                }
        }
    }

    @AssistedFactory
    interface Factory : DelegateFactory<ContentViewModelDelegate> {
        override fun create(
            handle: SavedStateHandle,
            delegateScope: CoroutineScope
        ): ContentViewModelDelegateImpl
    }
}