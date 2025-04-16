package com.example.core.network

public interface NetworkClient {
    public suspend fun <T> doRequest(request: suspend () -> T): Result<T>
}