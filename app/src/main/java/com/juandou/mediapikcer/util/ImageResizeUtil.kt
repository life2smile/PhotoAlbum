package com.juandou.mediapikcer.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory

class ImageResizeUtil {
    companion object {
        fun resize(path: String, w: Int, h: Int): Bitmap {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(path, options)

            //获取缩放比例
            options.inSampleSize = Math.max(1, Math.ceil(Math.max(
                    options.outWidth / w, options.outHeight / h
            ).toDouble()).toInt())

            options.inJustDecodeBounds = false
            return BitmapFactory.decodeFile(path, options)
        }
    }
}