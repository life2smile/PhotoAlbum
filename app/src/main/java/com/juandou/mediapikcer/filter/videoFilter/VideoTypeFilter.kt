package com.juandou.mediapikcer.filter.videoFilter

import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter

class VideoTypeFilter : IFilter<MutableList<MediaData>> {
    override fun doFilter(list: MutableList<MediaData>) {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val mediaData = iterator.next()
            mediaData.filePath?.endsWith(".mp4")?.not().let {
                when (it) {
                    true -> iterator.remove()
                }
            }
        }
    }

}