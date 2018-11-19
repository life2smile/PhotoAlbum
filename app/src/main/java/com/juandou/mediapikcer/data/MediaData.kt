package com.juandou.mediapikcer.data

import java.text.SimpleDateFormat
import java.util.*

data class MediaData(
        var id: Long?,
        var createTime: Long = 0,
        var duration: Long?,
        var albumName: String?,
        var filePath: String?,
        var thumbNailPath: String?,
        var mimeType: String?,
        var latitude: Float?,
        var longitude: Float?
) {
    fun isVideo(): Boolean {
        return if (mimeType.isNullOrEmpty()) false else mimeType!!.contains("video")
    }

    fun isImage(): Boolean {
        return if (mimeType.isNullOrEmpty()) false else mimeType!!.contains("image")
    }

    fun formatDate(): String? {
        val format = SimpleDateFormat("yyyy-MM-dd HH:mm:ss")
        return format.format(Date(createTime * 1000))
    }

    fun formatDuration(): String? {
        return this.duration?.let {
            val totalSeconds = it / 1000
            val hour = totalSeconds / 3600

            val totalMinutes = totalSeconds % 3600
            val minute = totalMinutes / 60
            val second = totalMinutes % 60

            hour.toString().run {
                return@run if (this.length < 2) "0".plus(this) else this
            }.plus(":").plus(
                    minute.toString().run {
                        return@run if (this.length < 2) "0".plus(this) else this
                    }
            ).plus(":").plus(
                    second.toString().run {
                        return@run if (this.length < 2) "0".plus(this) else this
                    }
            )
        }
    }
}