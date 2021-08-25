package com.goodsoft.prisson.api.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class StartFightRequest(
    @SerializedName("firstPlayerId") val firstPlayerId: String,
    @SerializedName("secondPlayerId") val secondPlayerId: String
) {
}