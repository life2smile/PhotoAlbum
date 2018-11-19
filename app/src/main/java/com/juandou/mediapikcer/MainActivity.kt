package com.juandou.mediapikcer

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import com.juandou.mediapikcer.act.PickerActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val openAlbum = findViewById<TextView>(R.id.open_album_btn)
        openAlbum.text = "open album"
        openAlbum.setBackgroundColor(resources.getColor(R.color.accent_material_light))
        openAlbum.setOnClickListener {
            val intent = Intent()
            intent.setClass(this, PickerActivity().javaClass)
            //intent.setClass(this, PickerActivity::class.java)
            startActivity(intent)
        }
    }
}
