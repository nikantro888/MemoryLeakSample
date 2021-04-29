package com.example.memoryleaksample

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.asynctasksample.AsyncTaskActivity
import com.example.callbacksample.CallbackLeakActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initListeners()
    }

    private fun initListeners() {
        acyncTaskBtn.setOnClickListener {
            startActivity(Intent(this, AsyncTaskActivity::class.java))
        }
        callbackBtn.setOnClickListener {
            startActivity(Intent(this, CallbackLeakActivity::class.java))
        }
    }
}