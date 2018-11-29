package com.juandou.mediapikcer.filter.videoFilter

import android.graphics.BitmapFactory
import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter

class ThumbnailFilter : IFilter<MutableList<MediaData>> {

    override fun doFilter(list: MutableList<MediaData>) {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val mediaData: MediaData = iterator.next()
            val options: BitmapFactory.Options = BitmapFactory.Options()
            BitmapFactory.decodeFile(mediaData.thumbNailPath, options)
            if (options.outWidth <= 0 || options.outHeight <= 0) {
                iterator.remove()
            }
        }
    }
}