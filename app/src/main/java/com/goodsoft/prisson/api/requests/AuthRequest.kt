package com.goodsoft.prisson.api.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class AuthRequest(
    @SerializedName("login") val login: String,
    @SerializedName("password") val password: String
)