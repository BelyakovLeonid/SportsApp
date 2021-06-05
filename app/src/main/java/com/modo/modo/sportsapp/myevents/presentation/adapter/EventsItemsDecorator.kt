package com.modo.modo.sportsapp.myevents.presentation.adapter

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.graphics.Typeface
import android.util.Log
import android.view.View
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.modo.modo.sportsapp.R

class EventsItemsDecorator(
    private val offsetStandard: Int,
    private val titleSize: Int,
    private val adapter: EventsAdapter
) : RecyclerView.ItemDecoration() {

    private val viewBounds = Rect()

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        super.getItemOffsets(outRect, view, parent, state)

        val position = parent.getChildAdapterPosition(view)

        if (position == 0) {
            outRect.top = offsetStandard + titleSize + offsetStandard
        } else {
            val prev = adapter.currentList.getOrNull(position - 1)
            val current = adapter.currentList.getOrNull(position)

            if (prev != null && current != null && prev.isNearest && !current.isNearest) {
                outRect.top = offsetStandard + titleSize + offsetStandard
            } else {
                outRect.top = offsetStandard
            }
        }
    }

    private val paint = Paint().apply {
        color = Color.BLACK
        textSize = titleSize.toFloat()
        typeface = Typeface.create(Typeface.DEFAULT, Typeface.BOLD);
        style = Paint.Style.FILL_AND_STROKE
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        val firstText = parent.resources.getString(R.string.my_events_nearest)
        val secondText = parent.resources.getString(R.string.my_events_next)

        parent.children.forEach { child ->
            val position = parent.getChildAdapterPosition(child)
            parent.getDecoratedBoundsWithMargins(child, viewBounds)

            if (position == 0) {
                canvas.drawText(
                    firstText,
                    0,
                    firstText.length,
                    0F,
                    viewBounds.top + offsetStandard + titleSize.toFloat(),
                    paint
                )
            } else {
                val prev = adapter.currentList.getOrNull(position - 1)
                val current = adapter.currentList.getOrNull(position)

                if (prev != null && current != null && prev.isNearest && !current.isNearest) {

                    canvas.drawText(
                        secondText,
                        0,
                        secondText.length,
                        0F,
                        viewBounds.top + offsetStandard + titleSize.toFloat(),
                        paint
                    )
                }
            }
        }
    }
}