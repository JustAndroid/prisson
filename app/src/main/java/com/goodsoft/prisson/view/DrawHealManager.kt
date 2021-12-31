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

fun Draka.drawHeals(context: Context, canvas: Canvas) {

    val firstHeal = firstPlayer?.heals?.toString() ?: ""
    val secondHeal = secondPlayer?.heals?.toString() ?: ""

    val background = Rect()
    background.set(0, 0, canvas.width, 150)
    val backgroundPaint = Paint()
    backgroundPaint.color = ContextCompat.getColor(context,R.color.healsBackground)
    backgroundPaint.style = Paint.Style.FILL
    canvas.drawRect(background, backgroundPaint)

    val myRect = Rect()
    myRect.set(20, 40, canvas.width / 2 - 30, 55)
    val greenPaint = Paint()
    greenPaint.color = Color.GREEN
    greenPaint.style = Paint.Style.FILL
    canvas.drawRect(myRect, greenPaint)

    val myRect2 = Rect()
    myRect2.set(canvas.width / 2 + 30, 40, canvas.width - 20, 55)

    canvas.drawRect(myRect2, greenPaint)
    greenPaint.textSize = 50.0f;
    greenPaint.style = Paint.Style.FILL;
    greenPaint.isAntiAlias = true


    //длинна текста

    val bounds = Rect()
    greenPaint.getTextBounds(secondHeal , 0, secondHeal.length, bounds)
    val widthHeals = bounds.width()


    canvas.drawText(firstHeal, 40f, 120f, greenPaint)


    canvas.drawText(secondHeal , canvas.width - 40f - widthHeals, 120f, greenPaint)


    val boundsTime = Rect()
    greenPaint.getTextBounds(time.toString(), 0, time.toString().length, boundsTime)
    val widthTime = boundsTime.width()


    canvas.drawText(time.toString(), (canvas.width / 2 - (widthTime /2)).toFloat(), 120f, greenPaint)
}