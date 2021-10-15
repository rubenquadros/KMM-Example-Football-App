package com.ruben.footiescore.android.ui

import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.ruben.footiescore.android.R
import org.koin.androidx.viewmodel.ext.android.viewModel

fun greet(): String {
    return "Hello"
}

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("Ruben", viewModel.toString())
        setContentView(R.layout.activity_main)
        val tv: TextView = findViewById(R.id.text_view)
        tv.text = greet()
    }
}
