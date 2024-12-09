package com.yanchelenko.coordinateplaneapp.data.remote.services

import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

abstract class BaseService {
    protected suspend fun <T : Any> apiCall(call: suspend () -> Response<T>): Result<T> {
        return try {
            val response = call.invoke()

            if (response.isSuccessful) {
                val body = response.body()
                if (body != null) {
                    Result.success(body)
                } else {
                    Result.failure(IllegalStateException("Response body is null"))
                }
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Result.failure(HttpException(response).apply {
                    initCause(IllegalStateException("Error message: $errorBody"))
                })
            }
        } catch (e: IOException) {
            Result.failure(e)
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
