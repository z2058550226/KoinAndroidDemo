package com.suika.koindemo

import android.os.Bundle
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        applicationView.parent?.let {
            (it as ViewGroup).removeView(applicationView)
        }
        root_layout.addView(applicationView)
    }
}