package com.goodsoft.prisson.api

import com.goodsoft.prisson.api.requests.EndFightRequest
import com.goodsoft.prisson.api.requests.ResetKicksRequest
import com.goodsoft.prisson.api.requests.StartFightRequest
import com.goodsoft.prisson.api.requests.UserActionRequest
import com.goodsoft.prisson.api.response.FightResponse
import com.goodsoft.prisson.api.response.FightersListResponse
import com.goodsoft.prisson.api.response.ResetKicksResponse
import retrofit2.Call
import retrofit2.http.*


/**
 * Created by Nikolay on 8/25/20.
 */
interface DrakiService {

    @GET("getStatus")
    fun getStatus(
        @Query("firstPlayerId") firstPlayerId: String,
        @Query("secondPlayerId") secondPlayerId: String
    ): Call<FightResponse?>?

    @POST("startFight")
    fun startFight(@Body request: StartFightRequest): Call<FightResponse?>?

    @POST("setAction")
    fun setAction(@Body request: UserActionRequest): Call<FightResponse?>?

    @POST("endRaund")
    fun endRaund(@Body request: EndFightRequest): Call<FightResponse?>?

    @POST("resetKicks")
    fun resetKick(@Body request: ResetKicksRequest): Call<ResetKicksResponse?>?

    @GET("fighters")
    fun fighters(): Call<FightersListResponse?>?

}