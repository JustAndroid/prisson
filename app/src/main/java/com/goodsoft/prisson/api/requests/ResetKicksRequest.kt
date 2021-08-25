package com.goodsoft.prisson.api.requests

import com.goodsoft.prisson.api.response.Player
import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class ResetKicksRequest(
    @SerializedName("playerId") val playerId: String
) {
}