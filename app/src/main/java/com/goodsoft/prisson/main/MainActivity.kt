package com.goodsoft.prisson.main

import android.graphics.BlendMode
import android.graphics.BlendModeColorFilter
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.goodsoft.prisson.R
import com.goodsoft.prisson.api.response.FightResponse
import com.goodsoft.prisson.api.response.Player
import com.goodsoft.prisson.main.fight.FightViewModel
import com.goodsoft.prisson.utils.DataState
import com.goodsoft.prisson.utils.Pref
import com.goodsoft.prisson.utils.gone
import com.goodsoft.prisson.utils.show
import kotlinx.android.synthetic.main.layout_main_activity.*


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.layout_main_activity)
    }
}