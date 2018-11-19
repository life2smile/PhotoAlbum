package com.juandou.mediapikcer.filter.chain

import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.filter.IFilter

class VideoFilterChain {
    private val filters = ArrayList<IFilter<MutableList<MediaData>>>()

    fun addFilter(filter: IFilter<MutableList<MediaData>>) {
        filters.add(filter)
    }

    fun doFilter(list: MutableList<MediaData>) {
        for (filter: IFilter<MutableList<MediaData>> in filters) {
            filter.doFilter(list)
        }
    }
}