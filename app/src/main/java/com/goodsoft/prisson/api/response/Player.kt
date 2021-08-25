package com.goodsoft.prisson.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/31/20.
 */
class Player(
    @SerializedName("name") var name: String?,
    @SerializedName("password")var password: String?,
    @SerializedName("heals") var heals: Int?,
    @SerializedName("energy")var energy: Int?,
    @SerializedName("isMove")var isMove: Boolean?,
    @SerializedName("isEndRound")var isEndRound: Boolean?,
    @SerializedName("kickType") var kickType: KickType?,
    @SerializedName("enableKicks") var enableKicks: EnableKicks?)
{

    enum class KickType(val type: String) {
        @SerializedName("hend")  HEND("hend"),
        @SerializedName("leg") LEG("leg"),
        @SerializedName("finger_in_ies") FINGER_IN_IES("finger_in_ies"),
        @SerializedName("kontr_attack") KONTR_ATTACK("kontr_attack"),
        @SerializedName("pain_kick") PAIN_KICK("pain_kick"),
        @SerializedName("heals") HEALS("heals")
    }


    class EnableKicks(
        @SerializedName("is_enableLeg") var isEnableLeg: String?,
        @SerializedName("is_enable_kontr_attack") var isEnableKontrAttack: String?,
        @SerializedName("is_enable_pain_kick") var isEnablePainKick: String?,
        @SerializedName("is_enable_heals") var isEnableHeals: String?
        )

}