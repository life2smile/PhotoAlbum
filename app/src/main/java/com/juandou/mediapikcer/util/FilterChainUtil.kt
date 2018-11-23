package com.juandou.mediapikcer.util

import com.juandou.mediapikcer.filter.chain.ImageFilterChain
import com.juandou.mediapikcer.filter.chain.VideoFilterChain
import com.juandou.mediapikcer.filter.commonfilter.FilePathFilter
import com.juandou.mediapikcer.filter.imagefilter.ImageFolderFilter
import com.juandou.mediapikcer.filter.imagefilter.ImageSizeFilter
import com.juandou.mediapikcer.filter.videoFilter.VideoDurationFilter
import com.juandou.mediapikcer.filter.videoFilter.VideoTypeFilter

object FilterChainUtil {

    fun getDefaultImageFilterChain() : ImageFilterChain{
        val filterChain = ImageFilterChain()
        filterChain.addFilter(FilePathFilter())
        filterChain.addFilter(ImageFolderFilter())
        filterChain.addFilter(ImageSizeFilter())
        return filterChain
    }

    fun getDefaultVideoFilterChain(): VideoFilterChain {
        val filterChain = VideoFilterChain()
        filterChain.addFilter(FilePathFilter())
        filterChain.addFilter(VideoTypeFilter())
        filterChain.addFilter(VideoDurationFilter())
        return filterChain
    }

}