package com.matrix.link.ui.recorder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class WaveFormView(context : Context, attrs: AttributeSet) : View(context, attrs) {

    private var paint = Paint()

    private fun initPaint() {
        paint.color = Color.RED
        paint.strokeWidth = 3f
        paint.isAntiAlias = true
        paint.isDither = true
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeCap = Paint.Cap.ROUND
        paint.style = Paint.Style.STROKE
    }

    init {
        initPaint()
    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)
        canvas?.drawRoundRect(RectF(20f, 30f, 20f+30,30f+60),6f,6f,paint)
        canvas?.drawRoundRect(RectF(60f, 60f, 20f+80,30f+360),6f,6f,paint)
    }
}

