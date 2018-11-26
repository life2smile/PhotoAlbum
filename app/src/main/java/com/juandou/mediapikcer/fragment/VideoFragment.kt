package com.juandou.mediapikcer.fragment

import android.graphics.BitmapFactory
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import com.juandou.mediapikcer.R
import com.juandou.mediapikcer.data.PreviewData
import com.juandou.mediapikcer.view.ResizeVideoView
import java.io.File

class VideoFragment : Fragment() {
    private var mFilePath: String? = null
    private var mThumbnailPath: String? = null

    private var mVideoView: ResizeVideoView? = null
    private var mPlayImg: ImageView? = null
    private var mFirstFrameImg: ImageView? = null

    private var mPrepared: Boolean = false

    companion object {
        fun newInstance(previewData: PreviewData): Fragment {
            val fragment = VideoFragment()
            val bundle = Bundle()
            bundle.putString("filePath", previewData.filePath)
            bundle.putString("thumbNailPath", previewData.thumbnailPath)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFilePath = it.getString("filePath")
            mThumbnailPath = it.getString("thumbNailPath")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_preview_video, container, false)

        initView(view)

        resizeVideo(view)

        setListener()

        return view;
    }

    private fun setListener() {
        mPlayImg?.setOnClickListener {
            mPlayImg?.visibility = View.GONE
            if (mPrepared) {
                mFirstFrameImg?.visibility = View.GONE
            }
            mVideoView?.start()
        }

        mVideoView?.setOnPreparedListener {
            it.setOnInfoListener(MediaPlayer.OnInfoListener { mp, what, extra ->
                if (what == MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START) {
                    mPrepared = true
                    mFirstFrameImg?.visibility = View.GONE
                }
                return@OnInfoListener true
            })
        }

        mVideoView?.setOnCompletionListener {
            pause()
        }
    }

    private fun initView(view: View) {
        mVideoView = view.findViewById(R.id.fragment_preview_video)
        mFirstFrameImg = view.findViewById(R.id.fragment_preview_first_frame)
        mPlayImg = view.findViewById(R.id.fragment_preview_play_img)

        mThumbnailPath?.let {
            mFirstFrameImg?.setImageURI(Uri.fromFile(File(it)))
        }

        mFilePath?.let {
            mVideoView?.setVideoPath(mFilePath)
        }
    }

    private fun resizeVideo(view: View) {
        val sizeArr: IntArray = videoReSize()
        mVideoView?.resizeVideoView(sizeArr[0] - (view.paddingLeft + view.paddingRight),
                sizeArr[1] - (view.paddingTop + view.paddingBottom))

        mFirstFrameImg?.let {
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
            layoutParams.width = sizeArr[0]
            layoutParams.height = sizeArr[1]
            layoutParams.gravity = Gravity.CENTER
            it.layoutParams = layoutParams
        }
    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if (!isVisibleToUser) {
            pause()
        }
    }

    override fun onPause() {
        super.onPause()
        pause()
    }

    private fun videoReSize(): IntArray {
        val res = IntArray(2) { 0 }

        mThumbnailPath?.let {
            val option: BitmapFactory.Options = BitmapFactory.Options()
            option.inJustDecodeBounds = true
            BitmapFactory.decodeFile(mThumbnailPath, option)

            val screenW = resources.displayMetrics.widthPixels
            val screenH = resources.displayMetrics.heightPixels

            val originW = option.outWidth
            val originH = option.outHeight

            (originH > originW).let {
                if (it) {
                    res[1] = maxOf(originH, screenH)
                    res[0] = res[1] * originW / originH
                } else {
                    res[0] = maxOf(originW, screenW)
                    res[1] = res[0] * originH / originW
                }
            }
        }

        return res
    }

    private fun pause() {
        mVideoView?.pause()
        resetStatus()
    }

    private fun resetStatus() {
        mPlayImg?.visibility = View.VISIBLE
        mFirstFrameImg?.visibility = View.VISIBLE
    }
}