package com.goodsoft.prisson.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView

/**
 * Created by Nikolay on 6/23/20.
 */
abstract class BaseSurfaceView(context: Context, attrs: AttributeSet) : SurfaceView(context, attrs),
    SurfaceHolder.Callback {
    var FPS = 30L
    var startTime = 0L
    var sleepTime = 0L

    private var drawThread: DrawThread? = null
    override fun surfaceChanged(
        holder: SurfaceHolder?, format: Int, width: Int,
        height: Int
    ) {
    }

    override fun surfaceCreated(holder: SurfaceHolder?) {
        drawThread = DrawThread(this.holder)
        drawThread?.setRunning(true)
        drawThread?.start()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder?) {
        var retry = true
        drawThread!!.setRunning(false)
        while (retry) {
            try {
                drawThread?.join()
                retry = false
            } catch (e: InterruptedException) {
            }
        }
    }

    internal inner class DrawThread(surfaceHolder: SurfaceHolder) : Thread() {
        private var running = false
        private val surfaceHolder: SurfaceHolder

        private var ticksPS = 1000L / FPS

        fun setRunning(running: Boolean) {
            this.running = running
        }

        override fun run() {
            var canvas: Canvas?
            while (running) {
                canvas = null
                try {
                    canvas = surfaceHolder.lockCanvas(null)
                    if (canvas == null) continue

                    drawCanvas(canvas)

                    sleepTime = ticksPS - (System.currentTimeMillis() - startTime)


                    if (sleepTime > 0)
                        sleep(sleepTime)
                    else
                        sleep(50)


                } finally {
                    if(canvas !== null) {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                    }
                }
            }
        }

        init {
            this.surfaceHolder = surfaceHolder
        }
    }

    init {
        holder.addCallback(this)
    }

    open fun drawCanvas(canvas: Canvas) {
    }
}