package com.thomas.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

internal data class ErrorResponse(
    @SerializedName("documentation_url") val documentationUrl: String? = null,
    @SerializedName("message") override val message: String? = null,
    @Expose private val throwable: Throwable? = null
) : Throwable(message, throwable)
