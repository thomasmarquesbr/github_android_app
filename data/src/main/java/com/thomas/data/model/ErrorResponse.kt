package com.thomas.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ErrorResponse(
    @SerializedName("code") val code: String? = null,
    @SerializedName("message") override val message: String? = null,
    @Expose private val throwable: Throwable? = null
) : Throwable(message, throwable)
