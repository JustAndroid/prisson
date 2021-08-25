package com.goodsoft.prisson.view

import android.R.attr.path
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.util.Log
import android.view.SurfaceView
import java.util.*
import kotlin.collections.ArrayList


/**
 * Created by Nikolay on 6/12/20.
 */
class SnakeView(context: Context, attrs: AttributeSet) : BaseSurfaceView(context, attrs) {

    private val wavePaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.RED
        strokeWidth = 20f
        style = Paint.Style.STROKE
    }
    private val wavPaint: Paint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        strokeWidth = 20f
        color = Color.BLUE
    }
    private val waveGap: Float = 20f
    private var loong: Float = 20f
    private var oneCubic: Float = 20f
    private val period: Long = 500
    private var timer: Timer? = null
    val displayArr: ArrayList<Point> = ArrayList()


    @Volatile var lines: ArrayList<Line> = arrayListOf<Line>()


    var point: Point = Point(60f, 60f)

    var lastMoveSide = RIGHT

    var moveSide = RIGHT

    fun setSide(side: Int) {
        moveSide = side
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)

        var w = oneCubic
        var h =  oneCubic
        while (w < width - oneCubic) {

            while (h < height - oneCubic) {
                displayArr.add(Point(w.toFloat(), h.toFloat()))
                h = h + oneCubic
            }
            h = 0f
            w = w + oneCubic
        }

        point = displayArr[30]

        lines.add(Line(loong + loong, loong, loong, loong, moveSide))

        timer = Timer();
        timer?.schedule(object : TimerTask() {
            override fun run() {
                duSometing(waveGap)
            }
        }, 0, period)

    }

    fun duSometing(value: Float) {
        if (lastMoveSide != moveSide) {
            val line2 = Line(
                lines[0].startX,
                lines[0].startY,
                lines[0].startX,
                lines[0].startY,
                moveSide
            )
            lines.add(
                0,
                line2
            )

            if (lines[0].side == RIGHT) {
                lines[0].startX += loong
            } else if (lines[0].side == LEFT) {
                lines[0].startX -= loong
            } else if (lines[0].side == TOP) {
                lines[0].startY -= loong
            } else if (lines[0].side == BOTTOM) {
                lines[0].startY += loong
            }

            clearHvost(value)

            deleteLine()

            lastMoveSide = moveSide
            return
        }

        when (moveSide) {
            RIGHT -> {
                if (lines.size > 0) {
                    lines[0].startX += value
                    Log.d(
                        "AAA",
                        "Добавил 1 пиксель к RIGHT"
                    )
                    clearHvost(value)
                }
            }
            LEFT -> {
                if (lines.size > 0) {
                    lines[0].startX -= value
                    clearHvost(value)
                    Log.d(
                        "AAA",
                        "Добавил 1 пиксель к LEFT"
                    )
                }
            }
            TOP -> {
                if (lines.size > 0) {
                    lines[0].startY -= value
                    clearHvost(value)
                    Log.d(
                        "AAA",
                        "Добавил 1 пиксель к TOP"
                    )
                }
            }
            BOTTOM -> {
                if (lines.size > 0) {
                    lines[0].startY += value
                    clearHvost(value)
                    Log.d(
                        "AAA",
                        "Добавил 1 пиксель к BOTTOM"
                    )
                }

            }
        }

        deleteLine()

        Log.d(
            "AAA",
            "${lines[0].startY - point.Y}   ${lines[0].startX - point.X} )"
        )

        if (lines[0].startX > point.X) {
            if (lines[0].startX - point.X <= oneCubic / 2) {
                if (lines[0].startY > point.Y) {
                    if (lines[0].startY - point.Y <= oneCubic /2) addedCubik()
                } else {
                    if (point.Y - lines[0].startY <= oneCubic /2) addedCubik()
                }
            }
        } else {
            if (point.X - lines[0].startX <= oneCubic /2) {
                if (lines[0].startY > point.Y) {
                    if (lines[0].startY - point.Y <= oneCubic /2) addedCubik()
                } else {
                    if (point.Y - lines[0].startY <= oneCubic /2) addedCubik()
                }
            }
        }

//        postInvalidateOnAnimation()

    }


    override fun drawCanvas(canvas: Canvas) {
        super.drawCanvas(canvas)

        onDraww(canvas)
    }
    fun addedCubik() {
        lines[0].addPixel(oneCubic)

        point = displayArr[Random().nextInt(displayArr.size)]
    }

    private fun deleteLine() {
        if (lines.size == 1) return

        val lastLine = lines[lines.size - 1]

        if (lastLine.side == RIGHT && lastLine.startX <= lastLine.endX) {
            lines.remove(lastLine)
            Log.d(
                "AAA",
                "DElete last Line "
            )
        } else if (lastLine.side == LEFT && lastLine.startX >= lastLine.endX) {
            lines.remove(lastLine)
            Log.d(
                "AAA",
                "DElete last Line "
            )
        } else if (lastLine.side == BOTTOM && lastLine.startY <= lastLine.endY) {
            lines.remove(lastLine)
            Log.d(
                "AAA",
                "DElete last Line "
            )
        } else if (lastLine.side == TOP && lastLine.startY >= lastLine.endY) {
            lines.remove(lastLine)
            Log.d(
                "AAA",
                "DElete last Line "
            )
        }

    }

    fun clearHvost(value: Float) {
        val lastLine = lines[lines.size - 1]

        if (lastLine.side == RIGHT) {
            lastLine.endX += value
            Log.d(
                "AAA",
                "Очистили хвост  RIGHT"
            )
        } else if (lastLine.side == LEFT) {
            lastLine.endX -= value
            Log.d(
                "AAA",
                "Очистили хвост  LEFT"
            )
        } else if (lastLine.side == BOTTOM) {
            lastLine.endY += value
            Log.d(
                "AAA",
                "Очистили хвост  BOTTOM"
            )
        } else if (lastLine.side == TOP) {
            lastLine.endY -= value
            Log.d(
                "AAA",
                "Очистили хвост  TOP"
            )
        }
    }

    override fun onDetachedFromWindow() {
        timer?.cancel()
        super.onDetachedFromWindow()
    }

//    override fun onDraw(canvas: Canvas) {
//        super.onDraw(canvas)
     fun onDraww(canvas: Canvas) {

        canvas.drawColor(Color.WHITE)

        lines.forEachIndexed { index, line ->

            if (checkIsSnakeTouchSide(line.endX, line.endY, canvas)) {
                timer?.cancel()
                val paint = Paint()
                paint.color = Color.WHITE
                paint.style = Paint.Style.FILL
                canvas.drawPaint(paint)
                paint.textAlign = Paint.Align.CENTER;

                paint.color = Color.BLACK
                paint.textSize = 46f
                canvas.drawText(
                    "GAME OVER",
                    (canvas.width / 2).toFloat(),
                    (canvas.height / 2).toFloat(),
                    paint
                )

            }
            canvas.drawLine(
                line.startX,
                line.startY,
                line.endX,
                line.endY,
                if (index == 0) wavePaint else wavPaint
            )
        }

        canvas.drawPoint(point.X, point.Y, wavPaint)


        }
//        postInvalidateOnAnimation()

//


    private fun checkIsSnakeTouchSide(
        endX: Float,
        endY: Float,
        canvas: Canvas
    ): Boolean {
        if (endX > canvas.width) {
//                moveSide = LEFT
            return true
        }
        if (endX < 0) {
//                moveSide = RIGHT
            return true
        }
        if (endY > canvas.height) {
            return true
//                moveSide = TOP
        }
        if (endY < 0) {
            return true
//                moveSide = BOTTOM
        }
        return false
    }

    class Line(
        var startX: Float,
        var startY: Float,
        var endX: Float,
        var endY: Float,
        var side: Int
    ) {
        fun addPixel(oneCubic: Float) {
            if (side == RIGHT) {
                startX += oneCubic
            } else if (side == LEFT) {
                startX -= oneCubic
            } else if (side == TOP) {
                startY -= oneCubic
            } else if (side == BOTTOM) {
                startY += oneCubic
            }
        }
    }

    class Point(
        var X: Float,
        var Y: Float
    )

    companion object {
        const val RIGHT = 1
        const val LEFT = 2
        const val TOP = 3
        const val BOTTOM = 4
    }


}


