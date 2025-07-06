package com.example.testarchitecture.delegate.delegate

import kotlinx.coroutines.CoroutineScope

interface ViewModelDelegate {
    val delegateScope: CoroutineScope
}
