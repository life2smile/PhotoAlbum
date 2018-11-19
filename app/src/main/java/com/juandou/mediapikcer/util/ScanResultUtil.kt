package com.juandou.mediapikcer.util

import android.database.Cursor
import java.io.File

object ScanResultUtil {
    fun getAlbumNameFromPath(path: String): String {
        val lastPos: Int = path.lastIndexOf(File.separator)
        val temp: String = path.substring(0, lastPos)
        val start: Int = temp.lastIndexOf(File.separator)
        return temp.substring(start + 1)
    }

    fun getStringResultByKey(cursor: Cursor, key: String): String {
        return cursor.getString(cursor.getColumnIndex(key))
    }

    fun getIntResultByKey(cursor: Cursor, key: String): Int {
        return cursor.getInt(cursor.getColumnIndex(key))
    }

    fun getLongResultByKey(cursor: Cursor, key: String): Long {
        return cursor.getLong(cursor.getColumnIndex(key))
    }

    fun getFloatResultByKey(cursor: Cursor, key: String): Float {
        return cursor.getFloat(cursor.getColumnIndex(key))
    }
}