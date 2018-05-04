package com.pedromassango.mycustomview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

/**
 * Created by Pedro Massango on 1/25/18.
 */
class CircleButton : View {

    private val SELECTION_COUNT = 4 // Total number of selections.
    private var mWidth = 0F
    private var mHeight = 0F
    private var mRadius = 0F
    private var activeSelection = 0
    private lateinit var mDialPaint: Paint
    private lateinit var mTextPaint: Paint
    // String buffer for dial labels and float for ComputeXY result.
    private val mTempLabel = StringBuffer(8)
    private val mTempResult = FloatArray(2)

    constructor(context: Context): super(context) { init()}

    constructor(context: Context, attrs: AttributeSet): super(context, attrs){init()}

    constructor(context: Context, attrs: AttributeSet, style: Int): super(context, attrs, style){init()}

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)

        mWidth = w.toFloat()
        mHeight = h.toFloat()
        mRadius = (Math.min(mWidth, mHeight) /2)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        // Draw the dial
        canvas!!.drawCircle(mWidth/2, mHeight/2, mRadius, mDialPaint)
// Draw the text labels.
        val labelRadius = mRadius + 10
        val label = mTempLabel
        for (i in 0 until SELECTION_COUNT) {
            val xyData = computeXYForPosition(i, labelRadius)
            val x = xyData[0]
            val y = xyData[1]
            label.setLength(0)
            label.append(i)
            canvas.drawText(label, 0, label.length, x, y, mTextPaint)
        }
        // Draw the indicator mark.
        val markerRadius = mRadius - 35
        val xyData = computeXYForPosition(activeSelection,
                markerRadius)
        val x = xyData[0]
        val y = xyData[1]
        canvas.drawCircle(x, y, 20F, mTextPaint)
    }

    private fun init() {
        mTextPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mTextPaint.color = Color.BLUE
        mTextPaint.style = Paint.Style.FILL_AND_STROKE
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.textSize = 24f
        mDialPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mDialPaint.color = Color.LTGRAY
        // Initialize current selection.
        activeSelection = 2
        // TODO: Set up onClick listener for this view.
    }

    private fun computeXYForPosition(pos: Int, radius: Float): FloatArray{
        val result = FloatArray(3)

        val startAngle = Math.PI * (9/8.0)
        val angle = startAngle + (pos* Math.PI/4)
        result[0] = (radius * Math.cos(angle) + (mWidth /2)).toFloat()
        result[1] = (radius * Math.cos(angle) + (mHeight /2)).toFloat()
        return result
    }
}