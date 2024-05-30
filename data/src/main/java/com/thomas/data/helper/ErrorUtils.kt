package com.thomas.data.helper

import com.google.gson.Gson
import com.thomas.data.model.ErrorResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import java.util.concurrent.TimeoutException

private const val UNEXPECTED_ERROR_MESSAGE = "Ocorreu um erro inesperado, tente novamente."
private const val CONNECTION_ERROR_MESSAGE = "Verifique sua conexão e tente novamente."

class NetworkException(
    override val message: String = CONNECTION_ERROR_MESSAGE
) : Exception()

fun getDefaultThrowable() = NetworkException()

fun HttpException.parseError(): ErrorResponse {
    return try {
        val converterErrorBody: ErrorResponse? = Gson().fromJson(
            response()?.errorBody()?.string(),
            ErrorResponse::class.java
        )
        converterErrorBody?.copy(throwable = this) ?: ErrorResponse(
            message = UNEXPECTED_ERROR_MESSAGE,
            throwable = null
        )
    } catch (e: Error) {
        ErrorResponse(
            message = UNEXPECTED_ERROR_MESSAGE,
            throwable = this
        )
    }
}

fun Throwable.toRequestThrowable(): Throwable {
    return when (this) {
        is HttpException -> parseError()
        is UnknownHostException,
        is TimeoutException,
        is InterruptedException,
        is SocketTimeoutException,
        is SocketException,
        is ConnectException -> getDefaultThrowable()
        else -> this
    }
}

fun <T> Flow<T>.parseHttpError(): Flow<T> {
    return catch { throwable ->
        throw throwable.toRequestThrowable()
    }
}
