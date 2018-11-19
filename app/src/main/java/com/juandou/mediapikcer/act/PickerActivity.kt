package com.juandou.mediapikcer.act

import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Toast
import com.juandou.mediapikcer.R
import com.juandou.mediapikcer.adapter.GridAdapter
import com.juandou.mediapikcer.data.MediaData
import com.juandou.mediapikcer.data.MediaType
import com.juandou.mediapikcer.util.FilterChainUtil
import com.juandou.mediapikcer.util.ImageScanHelper
import com.juandou.mediapikcer.util.VideoScanHelper
import java.lang.ref.WeakReference
import java.util.*

class PickerActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var gridAdapter: GridAdapter
    private lateinit var album2MediaMap: Map<String, MutableList<MediaData>>

    private val permissionRequestStorage: Int = 1;
    private val storagePermissions: Array<String> = arrayOf("android.permission.READ_EXTERNAL_STORAGE", "android.permission.WRITE_EXTERNAL_STORAGE");

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_picker)

        initView()

        initData()

        initScanEnv()

    }

    private fun initView() {
        recyclerView = findViewById(R.id.picker_recycler_view)
    }

    private fun initData() {
        gridAdapter = GridAdapter(this)
        recyclerView.layoutManager = GridLayoutManager(this, 3)
        recyclerView.adapter = gridAdapter
        album2MediaMap = HashMap()
    }

    private fun initScanEnv() {
        if (checkPermission(storagePermissions)) {
            startScan()
        }
    }

    private fun startScan() {
        val handler = ScanHandler(this)
        VideoScanHelper.start(this, handler)
        ImageScanHelper.start(this, handler)
    }

    private fun onVideoScanDone(list: MutableList<MediaData>) {
        FilterChainUtil.getDefaultVideoFilterChain().doFilter(list)
        gridAdapter.addData(list)
    }

    private fun onImageScanDone(list: MutableList<MediaData>) {
        FilterChainUtil.getDefaultImageFilterChain().doFilter(list)
        gridAdapter.addData(list)
    }


    private fun checkPermission(permissions: Array<String>): Boolean {
        if (Build.VERSION.SDK_INT < 23) {
            return true
        }
        var allGranted = true
        for (permission: String in permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_DENIED) {
                if (ActivityCompat.shouldShowRequestPermissionRationale(this, permission)) {
                    Toast.makeText(this, "need storage...", Toast.LENGTH_SHORT).show()
                } else {
                    ActivityCompat.requestPermissions(this, arrayOf(permission), permissionRequestStorage)
                }
                allGranted = false
            }
        }

        return allGranted
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            permissionRequestStorage -> if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_DENIED) {
                startScan()
            } else {
                Toast.makeText(this, "permission denied!", Toast.LENGTH_SHORT).show()
                finish()
            }
        }
    }

    private class ScanHandler(activity: PickerActivity) : Handler() {
        val weakActivity = WeakReference<PickerActivity>(activity)

        override fun handleMessage(msg: Message?) {
            super.handleMessage(msg)

            weakActivity.get()?.run {
                when (msg?.what) {
                    MediaType.MEDIA_TYPE_VIDEO -> onVideoScanDone(msg.obj as MutableList<MediaData>)
                    MediaType.MEDIA_TYPE_IMAGE -> onImageScanDone(msg.obj as MutableList<MediaData>)
                }
            }
        }
    }
}