package com.juandou.mediapikcer.fragment

import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.juandou.mediapikcer.data.PreviewData
import java.io.File

class ImageFragment : Fragment() {
    private var mFilePath: String? = null

    companion object {
        fun newInstance(previewData: PreviewData): ImageFragment {
            val fragment: ImageFragment = ImageFragment()
            val bundle = Bundle()
            bundle.putString("filePath", previewData.filePath)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            mFilePath = it.getString("filePath")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val imageView = ImageView(context)
        val layoutParams: ViewGroup.LayoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        imageView.layoutParams = layoutParams

        mFilePath?.let {
            imageView.setImageURI(Uri.fromFile(File(mFilePath)))
        }

        return imageView
    }
}