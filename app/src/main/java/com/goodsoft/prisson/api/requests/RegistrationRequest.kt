package com.goodsoft.prisson.api.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class RegistrationRequest(
    @SerializedName("login") val login: String,
    @SerializedName("user_name") val userName: String,
    @SerializedName("password") val password: String
)