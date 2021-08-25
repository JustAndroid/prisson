package com.goodsoft.prisson.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * Created by Nikolay on 5/9/21.
 */
class Pref(private val context: Context) {

    private var sharedPreferences: SharedPreferences? = null

    init {
        sharedPreferences = context.getSharedPreferences(
            "DRAKI_PREF",
            Context.MODE_PRIVATE
        )
    }

    companion object {

    fun getInstance(context: Context): Pref? {
         var instance: Pref? = null

        if (instance == null) {
            instance = Pref(context)
        }
        return instance
    }

}
    fun saveLogin(login: String?) {
        sharedPreferences!!.edit().putString(PrefConstants.KEY_LOGIN, login).apply()
    }

    fun getLogin(): String? {
        return sharedPreferences!!.getString(PrefConstants.KEY_LOGIN, null)
    }

    fun saveLogin2(login: String?) {
        sharedPreferences!!.edit().putString(PrefConstants.KEY_LOGIN2, login).apply()
    }

    fun getLogin2(): String? {
        return sharedPreferences!!.getString(PrefConstants.KEY_LOGIN2, null)
    }

    fun removeLogin() {
        sharedPreferences!!.edit().remove(PrefConstants.KEY_LOGIN).apply()
    }

}