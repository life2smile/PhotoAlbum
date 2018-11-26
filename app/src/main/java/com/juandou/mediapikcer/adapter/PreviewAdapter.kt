package com.juandou.mediapikcer.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter
import com.juandou.mediapikcer.data.PreviewData
import com.juandou.mediapikcer.fragment.ImageFragment
import com.juandou.mediapikcer.fragment.VideoFragment

class PreviewAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {
    private var mPreviewDataPathList: MutableList<PreviewData> = ArrayList()

    constructor(fragmentManager: FragmentManager, list: MutableList<PreviewData>?) : this(fragmentManager) {
        list?.let {
            mPreviewDataPathList.addAll(list)
        }
    }

    override fun getItem(position: Int): Fragment {
        val mediaData: PreviewData = mPreviewDataPathList[position]

        mediaData.takeIf {
            it.isVideo()
        }?.let {
            return VideoFragment.newInstance(it)
        }

        mediaData.takeIf {
            it.isImage()
        }?.let {
            return ImageFragment.newInstance(it)
        }
        return Fragment()
    }

    override fun getCount(): Int {
        return mPreviewDataPathList.size
    }
}