package com.juandou.mediapikcer.fragment

import android.graphics.BitmapFactory
import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.juandou.mediapikcer.data.PreviewData
import com.juandou.mediapikcer.util.ImageResizeUtil

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

        val margin: Int = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10f, resources.displayMetrics).toInt()

        imageView.setPadding(margin, 0, margin, 0)

        mFilePath?.let {
            val options = BitmapFactory.Options()
            options.inJustDecodeBounds = true
            BitmapFactory.decodeFile(mFilePath, options)

            val screenW = resources.displayMetrics.widthPixels
            val screenH = resources.displayMetrics.heightPixels

            val resizeW = if (options.outWidth > screenW) screenW else options.outWidth
            val resizeH = if (options.outHeight > screenH) screenH else options.outHeight

            mFilePath?.let {
                imageView.setImageBitmap(ImageResizeUtil.resize(it, resizeW, resizeH))
            }
        }

        return imageView
    }
}