package com.example.testarchitecture.delegate

interface DelegateRepository {
    suspend fun getTitle(): Result<String>
    suspend fun getText(): Result<String>
}