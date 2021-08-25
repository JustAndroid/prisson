package com.goodsoft.prisson.main.login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodsoft.prisson.api.ApiManager
import com.goodsoft.prisson.api.UserService
import com.goodsoft.prisson.api.requests.AuthRequest
import com.goodsoft.prisson.api.response.AuthResponse
import com.goodsoft.prisson.utils.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nikolay on 12/4/20.
 */
class LoginViewModel : ViewModel() {

    val state = MutableLiveData<DataState>()

    val login = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun setLogin(login: String) {
        this.login.value = login
    }

    fun setPassword(password: String) {
        this.password.value = password
    }

    fun auth() {
        val service = ApiManager.retrofit?.create(UserService::class.java)?.auth(
            AuthRequest(login.value ?: "", password.value ?: "")
        )
        service?.enqueue(object : Callback<AuthResponse?> {
            override fun onFailure(call: Call<AuthResponse?>, t: Throwable) {
                Log.d("AAA", t.message)
            }

            override fun onResponse(
                call: Call<AuthResponse?>,
                response: Response<AuthResponse?>
            ) {

                if (response.isSuccessful) {
                    state.value = DataState.Data(response.body())
                }
            }
        })
    }


}