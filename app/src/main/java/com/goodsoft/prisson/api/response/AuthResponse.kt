package com.goodsoft.prisson.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class AuthResponse(
    @SerializedName("status") val status: AuthStatus?
) {
    enum class AuthStatus(val status: String) {
        @SerializedName("invalid_password")  INVALID_PASSWORD("invalid_password"),
        @SerializedName("success") SUCCESS("success"),
        @SerializedName("baned_account") BANED_ACCOUNT("baned_account")
    }
}