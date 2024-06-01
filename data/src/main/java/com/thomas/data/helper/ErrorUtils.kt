package com.thomas.data.helper

import com.google.gson.Gson
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
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

internal class NetworkException(
    override val message: String = CONNECTION_ERROR_MESSAGE
) : Exception()

internal fun getDefaultThrowable() = NetworkException()

internal fun HttpException.parseError(): ErrorResponse {
    return try {
        val gson = Gson().newBuilder().registerTypeAdapter(ErrorResponse::class.java, ErrorResponseAdapter()).create()
        val converterErrorBody: ErrorResponse? = gson.fromJson(
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

internal fun Throwable.toRequestThrowable(): Throwable {
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

internal fun <T> Flow<T>.parseHttpError(): Flow<T> {
    return catch { throwable ->
        throw throwable.toRequestThrowable()
    }
}

internal class ErrorResponseAdapter : TypeAdapter<ErrorResponse>() {
    override fun write(out: JsonWriter, value: ErrorResponse) {
        out.beginObject()
        out.name("documentation_url").value(value.documentationUrl)
        out.name("message").value(value.message)
        out.endObject()
    }

    override fun read(`in`: JsonReader): ErrorResponse {
        var documentationUrl: String? = null
        var message: String? = null

        `in`.beginObject()
        while (`in`.hasNext()) {
            when (`in`.nextName()) {
                "documentation_url" -> documentationUrl =
                    if (`in`.peek() != JsonToken.NULL)
                        `in`.nextString()
                    else
                        `in`.nextNull().let { null }
                "message" -> message =
                    if (`in`.peek() != JsonToken.NULL)
                        `in`.nextString()
                    else
                        `in`.nextNull().let { null }
                else -> `in`.skipValue()
            }
        }
        `in`.endObject()
        return ErrorResponse(documentationUrl, message, null)
    }
}
