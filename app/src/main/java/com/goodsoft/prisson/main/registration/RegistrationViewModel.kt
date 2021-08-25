package com.goodsoft.prisson.main.registration

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodsoft.prisson.api.ApiManager
import com.goodsoft.prisson.api.UserService
import com.goodsoft.prisson.api.requests.AuthRequest
import com.goodsoft.prisson.api.requests.RegistrationRequest
import com.goodsoft.prisson.api.response.AuthResponse
import com.goodsoft.prisson.api.response.RegistrationResponse
import com.goodsoft.prisson.utils.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nikolay on 12/4/20.
 */
class RegistrationViewModel : ViewModel() {

    val state = MutableLiveData<DataState>()

    val userName = MutableLiveData<String>()
    val login = MutableLiveData<String>()
    val password = MutableLiveData<String>()

    fun setUserName(login: String) {
        this.login.value = login
    }

    fun setLogin(login: String) {
        this.login.value = login
    }

    fun setPassword(password: String) {
        this.password.value = password
    }

    fun signIn() {
        val service = ApiManager.retrofit?.create(UserService::class.java)?.registration(
            RegistrationRequest(userName.value ?: "", login.value ?: "", password.value ?: "")
        )
        service?.enqueue(object : Callback<RegistrationResponse?> {
            override fun onFailure(call: Call<RegistrationResponse?>, t: Throwable) {
                Log.d("AAA", t.message)
            }

            override fun onResponse(
                call: Call<RegistrationResponse?>,
                response: Response<RegistrationResponse?>
            ) {

                if (response.isSuccessful) {
                    state.value = DataState.Data(response.body())
                }
            }
        })
    }


}