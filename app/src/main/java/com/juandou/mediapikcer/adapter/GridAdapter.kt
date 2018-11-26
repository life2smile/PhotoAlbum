package com.juandou.mediapikcer.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.juandou.mediapikcer.R
import com.juandou.mediapikcer.act.PreviewAct
import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.data.PreviewData
import java.io.File
import java.util.*

class GridAdapter(context: Context) : RecyclerView.Adapter<GridAdapter.GridViewHolder>() {
    private val mContext: Context = context
    private var mGridDataList = ArrayList<MediaData>()
    private var mPreviewDataList = ArrayList<PreviewData>()
    private val mItemSize = mContext.resources.displayMetrics.widthPixels / 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GridAdapter.GridViewHolder {
        return GridViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_grid_view, parent, false));
    }

    override fun onBindViewHolder(viewHolder: GridViewHolder, position: Int) {
        mGridDataList[position].let {
            val layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);
            layoutParams.width = mItemSize
            layoutParams.height = mItemSize
            viewHolder.image.layoutParams = layoutParams
            val iterator = it
            it.takeIf {
                it.isImage()
            }?.let {
                    viewHolder.duration.visibility = View.GONE
                    viewHolder.image.setImageURI(Uri.fromFile(File(it.filePath)))
                    viewHolder.image.visibility = View.VISIBLE
            }
            it.takeIf { it.isVideo() }
                    ?.let {
                            viewHolder.duration.text = iterator.formatDuration()
                            viewHolder.duration.visibility = View.VISIBLE
                            viewHolder.image.setImageURI(Uri.fromFile(File(it.thumbNailPath)))
                    }
            viewHolder.image.setOnClickListener {
                val intent = Intent(mContext, PreviewAct::class.java)
                val bundle = Bundle()
                bundle.putParcelableArrayList("previewDataList", mPreviewDataList)
                bundle.putInt("position", position)
                intent.putExtra("bundle", bundle)
                mContext.startActivity(intent)
            }
        }
    }

    override fun getItemCount(): Int {
        return mGridDataList.size
    }

    fun addData(list: List<MediaData>) {
        mGridDataList.addAll(list)
        mGridDataList = mGridDataList.sortedByDescending { it.createTime }.toMutableList() as ArrayList<MediaData>
        generatePreviewData()
        notifyDataSetChanged()
    }

    private fun generatePreviewData() {
        mPreviewDataList.clear()
        for (mediaData: MediaData in mGridDataList) {
            mPreviewDataList.add(PreviewData(mediaData.mimeType, mediaData.filePath, mediaData.thumbNailPath, mediaData.createTime))
        }
        mPreviewDataList.sortByDescending { it.createTime }
    }

    class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val image: ImageView = itemView.findViewById(R.id.item_img)
        val duration: TextView = itemView.findViewById(R.id.item_duration)
    }
}