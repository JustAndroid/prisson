package com.goodsoft.prisson.api.response

import com.google.gson.annotations.SerializedName

data class FightersListResponse (
    @SerializedName("data") val data: List<String>?
    ){
}