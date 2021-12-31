package com.goodsoft.prisson.main.list

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodsoft.prisson.api.ApiManager
import com.goodsoft.prisson.api.DrakiService
import com.goodsoft.prisson.api.UserService
import com.goodsoft.prisson.api.requests.AuthRequest
import com.goodsoft.prisson.api.requests.RegistrationRequest
import com.goodsoft.prisson.api.response.AuthResponse
import com.goodsoft.prisson.api.response.FightersListResponse
import com.goodsoft.prisson.api.response.RegistrationResponse
import com.goodsoft.prisson.utils.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by Nikolay on 12/4/20.
 */
class FightersViewModel : ViewModel() {

    val state = MutableLiveData<DataState>()

    init {
        getFighters()
    }

    fun getFighters() {
        state.value = DataState.Loading()
        val service = ApiManager.retrofit?.create(DrakiService::class.java)?.fighters()
        service?.enqueue(object : Callback<FightersListResponse?> {
            override fun onFailure(call: Call<FightersListResponse?>, t: Throwable) {
                Log.d("AAA", t.message)
            }

            override fun onResponse(
                call: Call<FightersListResponse?>,
                response: Response<FightersListResponse?>
            ) {
                if (response.isSuccessful) {
                    state.value = DataState.Data(response.body())
                }
            }
        })
    }

}