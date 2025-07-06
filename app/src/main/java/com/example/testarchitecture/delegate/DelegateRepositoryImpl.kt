package com.example.testarchitecture.delegate

import kotlinx.coroutines.delay

class DelegateRepositoryImpl : DelegateRepository {
    override suspend fun getTitle(): Result<String> {
        delay(2000)
        return Result.success("Delegate Title")
    }

    override suspend fun getText(): Result<String> {
        delay(3000)
        return Result.success("Delegate Text")
    }
}