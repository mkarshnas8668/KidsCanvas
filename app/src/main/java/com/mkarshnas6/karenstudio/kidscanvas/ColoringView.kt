package com.mkarshnas6.karenstudio.kidscanvas

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import java.util.LinkedList

class ColoringView(context: Context, attrs: AttributeSet) : View(context, attrs) {

    private var bitmap: Bitmap? = null
    private var originalBitmap: Bitmap? = null
    private var paint = Paint()
    private var selectedColor = Color.RED
    private var touchX = 0
    private var touchY = 0
    private var pendingBitmap: Bitmap? = null

    init {
        paint.isAntiAlias = true
        paint.style = Paint.Style.FILL
    }

    fun setBitmap(bitmap: Bitmap) {
        pendingBitmap = bitmap
        if (width > 0 && height > 0) {
            applyBitmap(bitmap)
        }
    }

    private fun applyBitmap(bitmap: Bitmap) {
        val scaledBitmap = scaleBitmapToFitView(bitmap)
        this.bitmap = scaledBitmap.copy(Bitmap.Config.ARGB_8888, true)
        this.originalBitmap = scaledBitmap.copy(Bitmap.Config.ARGB_8888, true)
        invalidate()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pendingBitmap?.let {
            applyBitmap(it)
            pendingBitmap = null
        }
    }

    fun getBitmapFromView(view: View): Bitmap {
        view.isDrawingCacheEnabled = true
        val bitmap = Bitmap.createBitmap(view.drawingCache)
        view.isDrawingCacheEnabled = false
        return bitmap
    }

    fun setColor(color: Int) {
        selectedColor = color
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bitmap?.let {
            canvas.drawBitmap(it, 0f, 0f, paint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (event.action == MotionEvent.ACTION_DOWN) {
            touchX = event.x.toInt()
            touchY = event.y.toInt()

            bitmap?.let {
                if (touchX in 0 until it.width && touchY in 0 until it.height) {
                    val pixelColor = it.getPixel(touchX, touchY)

                    if (!isLineColor(pixelColor)) {
                        floodFill(it, touchX, touchY, selectedColor)
                        invalidate()
                    }
                }
            }
        }
        return true
    }

    private fun isLineColor(pixelColor: Int): Boolean {
        val lineColor = Color.BLACK
        return pixelColor == lineColor
    }

    private fun floodFill(bitmap: Bitmap, x: Int, y: Int, newColor: Int) {
        val oldColor = bitmap.getPixel(x, y)
        if (oldColor == newColor) return

        val queue = LinkedList<Pair<Int, Int>>()
        queue.add(Pair(x, y))

        while (queue.isNotEmpty()) {
            val (cx, cy) = queue.poll()

            if (cx !in 0 until bitmap.width || cy !in 0 until bitmap.height) continue
            if (bitmap.getPixel(cx, cy) != oldColor) continue

            bitmap.setPixel(cx, cy, newColor)

            queue.add(Pair(cx + 1, cy))
            queue.add(Pair(cx - 1, cy))
            queue.add(Pair(cx, cy + 1))
            queue.add(Pair(cx, cy - 1))
        }
    }

    private fun scaleBitmapToFitView(bitmap: Bitmap): Bitmap {
        val viewWidth = width
        val viewHeight = height

        val scaleWidth = viewWidth.toFloat() / bitmap.width.toFloat()
        val scaleHeight = viewHeight.toFloat() / bitmap.height.toFloat()
        val scaleFactor = Math.min(scaleWidth, scaleHeight)

        val newWidth = (bitmap.width * scaleFactor).toInt()
        val newHeight = (bitmap.height * scaleFactor).toInt()
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }

    //    del colors
    fun deleteCanvas() {
        originalBitmap?.let {
            applyBitmap(it)
        }
    }


}
