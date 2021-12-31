package com.goodsoft.prisson.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import androidx.core.content.ContextCompat
import com.goodsoft.prisson.R

/**
 * Created by Nikolay on 5/8/21.
 */

fun Draka.drawEnergy(context: Context, canvas: Canvas) {

    val background = Rect()
    background.set(0, 150, canvas.width, 230)
    val backgroundPaint = Paint()
    backgroundPaint.color = ContextCompat.getColor(context,R.color.healsBackground)
    backgroundPaint.style = Paint.Style.FILL
    canvas.drawRect(background, backgroundPaint)

    val greenPaint = Paint()
    greenPaint.color = Color.GREEN
    greenPaint.style = Paint.Style.FILL
    greenPaint.textSize = 50.0f;
    greenPaint.isAntiAlias = true

    //длинна текста

    firstPlayer?.energy?.let{canvas.drawText("$it  ENERGY", 20f, 200f, greenPaint)}
}