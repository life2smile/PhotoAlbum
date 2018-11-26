package com.juandou.mediapikcer.act

import android.os.Bundle
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import com.juandou.mediapikcer.R
import com.juandou.mediapikcer.adapter.PreviewAdapter
import com.juandou.mediapikcer.data.PreviewData

class PreviewAct : AppCompatActivity() {
    private lateinit var mAdapter: PreviewAdapter
    private lateinit var mViewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preview)

        initView()

        initData()
    }

    private fun initView() {
        mViewPager = findViewById(R.id.preview_view_pager)
    }

    private fun initData() {
        intent?.let {
            val bundle = it.getBundleExtra("bundle")
            val previewDataList: MutableList<PreviewData>? = bundle.getParcelableArrayList<PreviewData>("previewDataList")
            val position = bundle.getInt("position")
            mAdapter = PreviewAdapter(supportFragmentManager, previewDataList)
            mViewPager.adapter = mAdapter
            mViewPager.currentItem = position
        }
    }
}