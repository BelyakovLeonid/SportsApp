package com.modo.modo.sportsapp.camera.presentation.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import androidx.core.content.ContextCompat
import com.modo.modo.sportsapp.R
import kotlin.math.min

class ScannerOverlayView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attributeSet, defStyleAttr) {

    private val boxPaint: Paint = Paint().apply {
        color = ContextCompat.getColor(context, R.color.barcode_reticle_stroke)
        style = Paint.Style.STROKE
        strokeWidth = context.resources.getDimensionPixelOffset(R.dimen.barcode_box_stroke_width).toFloat()
    }

    private val backgroundColor = ContextCompat.getColor(context, R.color.barcode_reticle_background)

    private val eraserPaint: Paint = Paint().apply {
        strokeWidth = boxPaint.strokeWidth
        xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
    }

    private val boxCornerRadius: Float =
        resources.getDimensionPixelOffset(R.dimen.barcode_box_corner_radius).toFloat()

    private var boxRect = RectF()

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        val rectSize = min(w, h) * 0.8F
        val horizontalOffset = (w - rectSize) / 2F
        val verticalOffset = (h - rectSize) / 2F

        boxRect.set(
            horizontalOffset,
            verticalOffset,
            w - horizontalOffset,
            h - verticalOffset
        )

        invalidate()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        canvas.drawColor(backgroundColor)
        eraserPaint.style = Paint.Style.FILL
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, eraserPaint)
        eraserPaint.style = Paint.Style.STROKE
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, eraserPaint)
        canvas.drawRoundRect(boxRect, boxCornerRadius, boxCornerRadius, boxPaint)
    }
}