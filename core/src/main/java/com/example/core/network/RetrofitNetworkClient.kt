package com.example.core.network

import com.example.core.utils.getConnected
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException

public abstract class RetrofitNetworkClient() : NetworkClient {
    public override suspend fun <T> doRequest(request: suspend () -> T): Result<T> {
        if (!getConnected()) {
            return Result.failure(NetworkError.NoInternet())
        }
        return withContext(Dispatchers.IO) {
            try {
                Result.success(request())
            } catch (e: HttpException) {
                Result.failure(NetworkError.ServerError("", e.toString()))
            }
        }
    }
}