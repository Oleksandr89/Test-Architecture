package com.example.testarchitecture.delegate

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.testarchitecture.DelegateFactory
import com.example.testarchitecture.delegate.delegate.ContentViewModelDelegate
import com.example.testarchitecture.delegate.delegate.TopBarViewModelDelegate
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn

class DelegateViewModel @AssistedInject constructor(
    @Assisted
    private val handle: SavedStateHandle,
    override val delegateScope: CloseableCoroutineScope,
    private val topBarDelegateFactory: DelegateFactory<TopBarViewModelDelegate>,
    private val contentDelegateFactory: DelegateFactory<ContentViewModelDelegate>
) : ViewModel(viewModelScope = delegateScope),
    TopBarViewModelDelegate by topBarDelegateFactory.create(handle, delegateScope),
    ContentViewModelDelegate by contentDelegateFactory.create(handle, delegateScope) {

        val state = combine(
            topBarState,
            contentState
        ) { topBar, content ->
            DelegateState(topBar.title, content.text)
        }.stateIn(
            delegateScope,
            SharingStarted.WhileSubscribed(5_000),
            DelegateState("Empty Title", "Empty Content")
        )

    data class DelegateState(
        val title: String,
        val content: String
    )
}