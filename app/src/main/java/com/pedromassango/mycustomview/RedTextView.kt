package com.pedromassango.mycustomview

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.support.v7.widget.AppCompatTextView
import android.util.AttributeSet

/**
 * Created by pedromassango on 1/7/18.
 */
class RedTextView : AppCompatTextView {

    constructor(context: Context) : super(context) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        initView()
    }

    constructor(context: Context, attributeSet: AttributeSet, style: Int) : super(context, attributeSet, style) {
        initView()
    }

    fun initView() {
        textSize = 24F
        typeface = Typeface.SANS_SERIF
        setTextColor(Color.RED)
    }


}