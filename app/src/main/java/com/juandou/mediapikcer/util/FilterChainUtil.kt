package com.juandou.mediapikcer.util

import com.juandou.mediapikcer.filter.chain.ImageFilterChain
import com.juandou.mediapikcer.filter.chain.VideoFilterChain
import com.juandou.mediapikcer.filter.imagefilter.ImageFolderFilter
import com.juandou.mediapikcer.filter.imagefilter.ImageSizeFilter
import com.juandou.mediapikcer.filter.videoFilter.VideoTypeFilter

object FilterChainUtil {

    fun getDefaultImageFilterChain() : ImageFilterChain{
        val filterChain = ImageFilterChain()
        filterChain.addFilter(ImageFolderFilter())
        filterChain.addFilter(ImageSizeFilter())
        return filterChain
    }

    fun getDefaultVideoFilterChain(): VideoFilterChain {
        val filterChain = VideoFilterChain()
        filterChain.addFilter(VideoTypeFilter())
        return filterChain
    }

}