package com.goodsoft.prisson.main.fight

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.goodsoft.prisson.api.ApiManager
import com.goodsoft.prisson.api.DrakiService
import com.goodsoft.prisson.api.requests.EndFightRequest
import com.goodsoft.prisson.api.requests.ResetKicksRequest
import com.goodsoft.prisson.api.requests.StartFightRequest
import com.goodsoft.prisson.api.requests.UserActionRequest
import com.goodsoft.prisson.api.response.FightResponse
import com.goodsoft.prisson.api.response.Player
import com.goodsoft.prisson.api.response.ResetKicksResponse
import com.goodsoft.prisson.utils.DataState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

/**
 * Created by Nikolay on 12/4/20.
 */
class FightViewModel : ViewModel() {

    companion object {
        const val END_WAIT_TIME_SEC = 10 * 1000L
        const val PERIOD_UPDATE_GAME_SEC = 4 * 1000L
        const val PERIOD_UPDATE_TIMER_SEC = 4 * 1000L
    }

    var isShow = false
    val state = MutableLiveData<DataState>()
    val action = MutableLiveData<Boolean>()
    val resetKicks = MutableLiveData<Int>()
    val timeLiveData = MutableLiveData<Long>()

    var userName = ""
    var userName2 = ""

    var timer: Timer? = null
    var lastTime = 0L

    var time = 0L


    fun initUsers(first: String, second: String) {
        userName = first
        userName2 = second
    }

    init {
//        update()
    }

    fun update() {
        timer?.cancel()
        timer = Timer()
        timer?.schedule(object : TimerTask() {
            override fun run() {
                Log.d("AAA", "update ")
//                getStatus()
//                time += PERIOD_UPDATE_TIMER_SEC
//                timeLiveData.postValue(time / 1000)

//                if (lastTime + PERIOD_UPDATE_GAME_SEC <= time) {

//                    if (time >= END_WAIT_TIME_SEC) {
//                    setAction()
//                    action.postValue(true)
//                        time = 0L
//                    } else {
                getStatus()
//                    }

//                    lastTime = time
//                }


            }
        }, 0, PERIOD_UPDATE_TIMER_SEC)
    }

    fun stopTimer() {
        timer?.cancel()
    }

    fun setAction(type: Player.KickType) {
        val service = ApiManager.retrofit?.create(DrakiService::class.java)?.setAction(
            UserActionRequest(userName, userName2, type.type)
        )
        service?.enqueue(object : Callback<FightResponse?> {
            override fun onFailure(call: Call<FightResponse?>, t: Throwable) {
                Log.d("AAA", t.message)

            }

            override fun onResponse(
                call: Call<FightResponse?>,
                response: Response<FightResponse?>
            ) {

                if (response.isSuccessful) {
                    Log.d("AAA", response.body()?.firstPlayer?.name ?: "Бля")
                    val data = response.body()

                    if (data?.secondPlayer?.isMove != true) {
                        update()
                    }

                    data?.isAction = true
                    state.value = DataState.Data(data)
                }
            }
        })
    }

    fun endRound() {
        val service = ApiManager.retrofit?.create(DrakiService::class.java)?.endRaund(
            EndFightRequest(userName, userName2)
        )
        service?.enqueue(object : Callback<FightResponse?> {
            override fun onFailure(call: Call<FightResponse?>, t: Throwable) {
                Log.d("AAA", t.message)

            }

            override fun onResponse(
                call: Call<FightResponse?>,
                response: Response<FightResponse?>
            ) {

                if (response.isSuccessful) {
                    Log.d("AAA", response.body()?.firstPlayer?.name ?: "Бля")
                    val data = response.body()
                    state.value = DataState.Data(data)
                }
            }
        })
    }

    fun resetKicks() {
        val service = ApiManager.retrofit?.create(DrakiService::class.java)?.resetKick(
            ResetKicksRequest(userName)
        )
        service?.enqueue(object : Callback<ResetKicksResponse?> {
            override fun onFailure(call: Call<ResetKicksResponse?>, t: Throwable) {
                Log.d("AAA", t.message)

            }

            override fun onResponse(
                call: Call<ResetKicksResponse?>,
                response: Response<ResetKicksResponse?>
            ) {

                if (response.isSuccessful) {
                    val data = response.body()

                    if (data?.isSuccess == true) {
                        resetKicks.value = data?.energy
                    }
                }
            }
        })
    }

    fun getStatus() {
        val service = ApiManager.retrofit?.create(DrakiService::class.java)?.getStatus(
            userName, userName2
        )
        service?.enqueue(object : Callback<FightResponse?> {
            override fun onFailure(call: Call<FightResponse?>, t: Throwable) {
                Log.d("AAA", t.message)

            }

            override fun onResponse(
                call: Call<FightResponse?>,
                response: Response<FightResponse?>
            ) {

                if (response.isSuccessful) {
                    state.value = DataState.Data(response.body())

                    Log.d("AAA", response.body()?.firstPlayer?.name ?: "Бля")
                }
            }
        })
    }

    fun startFight() {
        val service = ApiManager.retrofit?.create(DrakiService::class.java)?.startFight(
            StartFightRequest(
                userName, userName2
            )
        )
        service?.enqueue(object : Callback<FightResponse?> {
            override fun onFailure(call: Call<FightResponse?>, t: Throwable) {
                Log.d("AAA", t.message)

            }

            override fun onResponse(
                call: Call<FightResponse?>,
                response: Response<FightResponse?>
            ) {

                if (response.isSuccessful) {
                    Log.d("AAA", response.body()?.firstPlayer?.name ?: "Бля")
                    state.value = DataState.Data(response.body())
                    update()
                }
            }
        })
    }

    override fun onCleared() {
        super.onCleared()
        timer?.cancel()
    }
}