package com.goodsoft.prisson.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/25/20.
 */
class RegistrationResponse(
    @SerializedName("status") val status: RegistrationStatus?
){
    enum class RegistrationStatus(val status: String) {
        @SerializedName("user_already_exist")  USER_ALREADY_EXIST("user_already_exist"),
        @SerializedName("success") SUCCESS("success"),
    }
}