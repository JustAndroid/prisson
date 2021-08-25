package com.goodsoft.prisson.api.requests

import com.goodsoft.prisson.api.response.Player
import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class UserActionRequest(
    @SerializedName("firstPlayerId") val firstPlayerId: String,
    @SerializedName("secondPlayerId") val secondPlayerId: String,
    @SerializedName("kick_type") val kickType: String
) {
}