package com.juandou.mediapikcer.filter.imagefilter

import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter

class ImageFolderFilter() : IFilter<MutableList<MediaData>> {
    override fun doFilter(list: MutableList<MediaData>) {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val mediaData: MediaData = iterator.next()

            mediaData.filePath?.contains("mogujie").let {
                when (it) {
                    true -> iterator.remove()
                }
            }
        }
    }

}