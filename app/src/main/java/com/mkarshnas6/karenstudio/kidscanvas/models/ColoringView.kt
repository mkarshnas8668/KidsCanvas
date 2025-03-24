package com.mkarshnas6.karenstudio.kidscanvas.models

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

    // تنظیم تصویر بدون مقیاس‌بندی
    fun setBitmap(bitmap: Bitmap) {
        pendingBitmap = bitmap
        if (width > 0 && height > 0) {
            applyBitmap(bitmap)  // بدون مقیاس‌بندی
        }
    }

    // حذف مقیاس‌بندی و استفاده از تصویر اصلی
    private fun applyBitmap(bitmap: Bitmap) {
        val scaledBitmap = scaleBitmapToFitView(bitmap) // مقیاس‌بندی تصویر با حفظ نسبت
        this.bitmap = scaledBitmap.copy(Bitmap.Config.ARGB_8888, true)
        this.originalBitmap = scaledBitmap.copy(Bitmap.Config.ARGB_8888, true)
        invalidate()
    }

    // این متد زمانی که اندازه ویو تغییر می‌کند اجرا می‌شود
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        pendingBitmap?.let {
            applyBitmap(it)  // تنظیم تصویر با مقیاس‌بندی
            pendingBitmap = null  // بعد از بارگذاری تصویر دیگر نیازی به این عکس نیست
        }
    }

    // تابعی برای تغییر رنگ انتخاب شده
    fun setColor(color: Int) {
        selectedColor = color
    }

    // کشیدن تصویر در اندازه واقعی
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        bitmap?.let {
            // کشیدن تصویر با حفظ تناسب
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

    // تابعی برای تشخیص رنگ خطوط
    private fun isLineColor(pixelColor: Int): Boolean {
        val lineColor = Color.BLACK
        return pixelColor == lineColor
    }

    // الگوریتم Flood Fill برای پر کردن ناحیه
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

    // تابعی برای مقیاس‌بندی تصویر با حفظ تناسب
    private fun scaleBitmapToFitView(bitmap: Bitmap): Bitmap {
        val viewWidth = width
        val viewHeight = height

        // محاسبه نسبت مقیاس برای تغییر اندازه تصویر
        val scaleWidth = viewWidth.toFloat() / bitmap.width.toFloat()
        val scaleHeight = viewHeight.toFloat() / bitmap.height.toFloat()
        val scaleFactor = Math.min(scaleWidth, scaleHeight) // انتخاب مقیاس بهینه

        // محاسبه اندازه جدید تصویر
        val newWidth = (bitmap.width * scaleFactor).toInt()
        val newHeight = (bitmap.height * scaleFactor).toInt()

        // تغییر اندازه تصویر با حفظ نسبت
        return Bitmap.createScaledBitmap(bitmap, newWidth, newHeight, true)
    }
}
