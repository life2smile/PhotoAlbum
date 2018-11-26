package com.juandou.mediapikcer.view

import android.content.Context
import android.util.AttributeSet
import android.widget.VideoView

class ResizeVideoView : VideoView {
    private var mVideoViewWidth: Int = 0
    private var mVideoViewHeight: Int = 0

    constructor(context: Context) : super(context)
    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSize = if (mVideoViewWidth == 0) MeasureSpec.getSize(widthMeasureSpec) else mVideoViewWidth
        val heightSize = if (mVideoViewHeight == 0) MeasureSpec.getSize(heightMeasureSpec) else mVideoViewHeight


        if (MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY
                && MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.EXACTLY) {
            setMeasuredDimension(widthSize, heightSize)
        }
    }


    fun resizeVideoView(width: Int, height: Int) {
        mVideoViewWidth = width
        mVideoViewHeight = height
        invalidate()
    }

}