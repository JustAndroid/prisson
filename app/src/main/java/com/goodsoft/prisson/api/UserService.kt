package com.goodsoft.prisson.api

import com.goodsoft.prisson.api.requests.AuthRequest
import com.goodsoft.prisson.api.requests.RegistrationRequest
import com.goodsoft.prisson.api.response.AuthResponse
import com.goodsoft.prisson.api.response.RegistrationResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

/**
 * Created by Nikolay on 5/9/21.
 */
interface UserService {

    @POST("registration")
    fun registration(@Body request: RegistrationRequest): Call<RegistrationResponse?>?

    @POST("auth")
    fun auth(@Body request: AuthRequest): Call<AuthResponse?>?

}