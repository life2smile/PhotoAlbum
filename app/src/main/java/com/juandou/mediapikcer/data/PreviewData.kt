package com.juandou.mediapikcer.data

import android.os.Parcel
import android.os.Parcelable

data class PreviewData(var mimeType: String?, var filePath: String?, var thumbnailPath: String?, var createTime: Long) : Parcelable, Comparable<PreviewData> {
    override fun compareTo(other: PreviewData): Int {
        return other.createTime.compareTo(this.createTime)
    }

    constructor(parcel: Parcel) : this(
            parcel.readString(),
            parcel.readString(),
            parcel.readString(),
            parcel.readLong()
    )

    fun isVideo(): Boolean {
        mimeType?.let {
            return it.contains("video")
        }
        return false
    }

    fun isImage(): Boolean {
        return if (mimeType.isNullOrEmpty()) false else mimeType!!.contains("image")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(mimeType)
        parcel.writeString(filePath)
        parcel.writeString(thumbnailPath)
        parcel.writeLong(createTime)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<PreviewData> {
        override fun createFromParcel(parcel: Parcel): PreviewData {
            return PreviewData(parcel)
        }

        override fun newArray(size: Int): Array<PreviewData?> {
            return arrayOfNulls(size)
        }
    }

}