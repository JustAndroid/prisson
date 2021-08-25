package com.goodsoft.prisson.view

import android.content.Context
import android.content.res.Resources
import android.graphics.*
import android.media.MediaPlayer
import android.util.AttributeSet
import com.goodsoft.prisson.R
import com.goodsoft.prisson.api.response.Player


/**
 * Created by Nikolay on 8/20/20.
 */

class Draka(context: Context, attrs: AttributeSet) : BaseSurfaceView(context, attrs) {

    var firstPlayer: Player? = null
    var secondPlayer: Player? = null

    var time: Int = 0

    private var iteration = 0
    private var users: Array<User> = arrayOf(
        User(
            resources,
            WhatDo.STAY,
            Side.LEFT,
            100
        ),
        User(
            resources,
            WhatDo.STAY,
            Side.RIGHT,
            100
        )
    )

    var hit: MediaPlayer? = null
    var music: MediaPlayer? = null


    var stayLeft: Bitmap? = null
    var stayRight: Bitmap? = null
    var kickLeft: Bitmap? = null
    var kickRight: Bitmap? = null


    init {
        FPS = 30L
        hit = MediaPlayer.create(context, R.raw.hit)
        music = MediaPlayer.create(context, R.raw.music)


        stayLeft = BitmapFactory.decodeResource(resources, R.drawable.stay)
        stayRight = BitmapFactory.decodeResource(resources, R.drawable.stay_right)

//        stayLeft = Bitmap.createScaledBitmap(stayLeft!!, stayLeft!!.width * 2, stayLeft!!.height * 2, false)


        kickLeft = BitmapFactory.decodeResource(resources, R.drawable.kick_heand)
        kickRight = BitmapFactory.decodeResource(resources, R.drawable.kick_heand_right)

//        music?.start()
//        music?.setLooping(true)
    }

    override fun drawCanvas(canvas: Canvas) {
        super.drawCanvas(canvas)

        setSceneBackground(canvas)
        drawUser(canvas)
        drawHeals(context, canvas)
        drawEnergy(context, canvas)
    }

    fun setClick() {
        users[RIGHT].whatDo = WhatDo.HEND_FIGHT
        hit?.start()
    }

    fun onResponse() {
        users[RIGHT].whatDo = WhatDo.HEND_FIGHT
        hit?.start()
    }

    private fun drawUser(canvas: Canvas) {
        users.forEach {

            when {
                iteration in 0..10 -> canvas.drawBitmap(
                    it.getBitmap()!!,
                    it.getX(width),
                    height - 300f,
                    null
                )
                iteration in 10..20 -> canvas.drawBitmap(
                    it.getBitmap()!!,
                    it.getX(width),
                    height - 298f,
                    null
                )

                iteration == 21 -> {
                    iteration = 0
                    canvas.drawBitmap(it.getBitmap()!!, it.getX(width), height - 300f, null)
                }
            }


            iteration += 1
            it.whatDo = WhatDo.STAY
        }
    }


    private fun setSceneBackground(canvas: Canvas) {
        var bgr: Bitmap? = null
        bgr = BitmapFactory.decodeResource(getResources(), R.drawable.back); //load a ball image
        var temp = bgr!!

        temp = Bitmap.createScaledBitmap(
            temp,
            width,
            height,
            true
        );

        canvas.drawBitmap(temp, 0f, 0f, null)
    }


    inner class User(
        private var resources: Resources,
        var whatDo: WhatDo,
        var side: Side,
        lives: Int
    ) {

        fun getBitmap(): Bitmap? {
            return when (whatDo) {
                WhatDo.STAY ->
                    if (side == Side.LEFT) {
                        stayLeft
                    } else {
                        stayRight
                    }
                WhatDo.HEND_FIGHT -> {
                    if (side == Side.LEFT) {
                        kickLeft
                    } else {
                        kickRight
                    }
                }
            }
        }

        fun getX(height: Int): Float {
            return if (side == Side.LEFT) {
                100f
            } else {
                (height - 100f) - getBitmap()?.width!!
            }
        }

        fun getY(): Int {
            return if (side == Side.LEFT) {
                300
            } else {
                300
            }
        }
    }

    enum class WhatDo {
        STAY,
        HEND_FIGHT
    }

    enum class Side {
        LEFT,
        RIGHT
    }


    companion object {
        const val LEFT = 0
        const val RIGHT = 1
    }


    //Взбодриться, лечить себя в размере полученного урона -4

    // удар ногой 125 -3 бодр

    // просто удар, добавляет бодряк 50 + 3 бодряка

    //контр атака уклоняешся и наносишь урон 50

    //пальцем в глаз +2 (наносишь урон и не даешь восстановить бодряки) кроме того восстанавливаешь себе

    // даеться 3 бодряка (восстановление всех ударов 3 бодряка)

    // удар по болевой/ против взбодриться 35 урона       -4 бодр

    // жизни 500

    //бодряки энергия


}