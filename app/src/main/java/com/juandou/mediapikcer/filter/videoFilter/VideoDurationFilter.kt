package com.juandou.mediapikcer.filter.videoFilter

import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter

class VideoDurationFilter : IFilter<MutableList<MediaData>> {
    override fun doFilter(t: MutableList<MediaData>) {
        val iterator = t.iterator()
        while (iterator.hasNext()) {
            val mediaData = iterator.next()
            mediaData.duration?.let {
                if (it < 2000) {
                    iterator.remove()
                }
            }
        }
    }

}