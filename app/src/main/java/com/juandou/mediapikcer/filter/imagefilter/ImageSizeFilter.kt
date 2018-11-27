package com.juandou.mediapikcer.filter.imagefilter

import android.graphics.BitmapFactory
import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter

class ImageSizeFilter : IFilter<MutableList<MediaData>> {

    override fun doFilter(list: MutableList<MediaData>) {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val mediaData: MediaData = iterator.next()
            val options: BitmapFactory.Options = BitmapFactory.Options()
            BitmapFactory.decodeFile(mediaData.filePath, options)
            if (options.outWidth <= 50 || options.outHeight <= 50) {
                iterator.remove()
            }
        }
    }
}