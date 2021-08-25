package com.goodsoft.prisson.api.response

import com.google.gson.annotations.SerializedName

/**
 * Created by Nikolay on 8/28/20.
 */
class ResetKicksResponse(
    @SerializedName("isSuccess") val isSuccess: Boolean?,
    @SerializedName("energy") val energy: Int?
) {
}