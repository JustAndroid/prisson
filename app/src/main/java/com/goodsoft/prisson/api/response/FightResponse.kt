package com.goodsoft.prisson.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/25/20.
 */
class FightResponse(
    @SerializedName("firstPlayer") val firstPlayer: Player?,
    @SerializedName("secondPlayer") val secondPlayer: Player?,
    @SerializedName("status") val status: GameStatus?,
    var isAction: Boolean = false
){

    enum class GameStatus(val status: String) {
        @SerializedName("win")
        WIN("win"),
        @SerializedName("luse")
        LUSE("luse"),
        @SerializedName("fight")
        FIGHT("fight")
    }
}