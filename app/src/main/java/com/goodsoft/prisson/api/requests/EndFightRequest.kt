package com.goodsoft.prisson.api.requests

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 5/20/21.
 */
class EndFightRequest(
    @SerializedName("firstPlayerId") val firstPlayerId: String,
    @SerializedName("secondPlayerId") val secondPlayerId: String
)