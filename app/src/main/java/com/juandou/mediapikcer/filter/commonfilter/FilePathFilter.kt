package com.juandou.mediapikcer.filter.commonfilter

import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter
import java.io.File

class FilePathFilter : IFilter<MutableList<MediaData>> {
    override fun doFilter(list: MutableList<MediaData>) {
        val iterator = list.iterator()
        while (iterator.hasNext()) {
            val mediaData = iterator.next()
            if (mediaData.filePath.isNullOrEmpty()) {
                iterator.remove()
                continue
            }

            val file = File(mediaData.filePath)
            if (!file.exists()) {
                iterator.remove()
            }
        }

    }

}